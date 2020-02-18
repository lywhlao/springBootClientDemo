package com.example.demo.service.concurrent.custom.casQueue;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-18 22:07
 * @Description:
 */
public class CustomCasQueue {

    public volatile Node head;

    public volatile Node tail;

    public CustomCasQueue() {
        this.head = this.tail = new Node();
    }

    private static final Unsafe unsafe = UnsafeUtils.getUnsafe();

    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            tailOffset = unsafe.objectFieldOffset
                    (CustomCasQueue.class.getDeclaredField("tail"));
            headOffset = unsafe.objectFieldOffset
                    (CustomCasQueue.class.getDeclaredField("head"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * CAS head field. Used only by enq.
     */
    private final boolean compareAndSetHead(Node expect,Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, expect, update);
    }

    /**
     * CAS tail field. Used only by enq.
     */
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    /**
     * cas执行插入语句
     * @param value
     */
    public void put(Object value) {
        Node node = new Node(value);
        Node temp = tail;
        if (temp.next != null) {
            //说明有人正在做插入操作,tail的next域不应该有值，让它过
            compareAndSetTail(tail, temp.next);
        } else {
            //更新temp的next操作域
            if (temp.compareSetNext(node)) {
                compareAndSetTail(tail, node);
            }
        }
    }


    /**
     * cas 获取数据
     * @return
     */
    public Object take(){
        while (true) {
            if (head == tail) {
                return null;
            }
            Node headTemp=head;
            Node next = head.next;
            if (next != null) {
                if (compareAndSetHead(head,next)) {
                    Object result = head.data;
                    head.data = null;
                    headTemp.next=null;
                    return result;
                }
            }else{
                System.out.println("head next already null");
            }
        }
    }






    public static void main(String[] args) throws InterruptedException {
        CustomCasQueue casQueue = new CustomCasQueue();
        casQueue.put("aa");
        casQueue.put("bbb");
        CountDownLatch countDownLatch=new CountDownLatch(2);
        List<Thread> threads=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 2;
                    while (i-- > 0) {
                        casQueue.put(Thread.currentThread().getName()+"-"+RandomUtils.nextInt(1, 100));
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }));
        }

        threads.parallelStream().forEach(item->{
            item.start();;
        });


        casQueue.put("hello");

        countDownLatch.await();
        int num=0;
        Node head = casQueue.head;
        while(head.next!=null){
            num++;
            head=head.next;
        }
        System.out.println("hello");
        while (true) {
            Object take = casQueue.take();
            if (take != null) {
                System.out.println(take);
                continue;
            }
            break;
        }

        System.out.println(num);
    }
}
