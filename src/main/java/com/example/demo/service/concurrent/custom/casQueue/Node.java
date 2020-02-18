package com.example.demo.service.concurrent.custom.casQueue;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-18 22:05
 * @Description:
 */
public class Node {

    volatile Object data;
    volatile Node next;


    public Node(Object data) {
        this.data = data;
    }


    public Node() {
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    private static final Unsafe unsafe = UnsafeUtils.getUnsafe();
    private static final long nextOffset;

    static {
        try {
            nextOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("next"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * CAS head field. Used only by enq.
     */
    public final boolean compareSetNext(Node update) {
        return unsafe.compareAndSwapObject(this, nextOffset, null, update);
    }


}
