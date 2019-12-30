package com.example.demo.service.leetcode;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-21 22:35
 * @Description:
 */
public class L622 {


    public static void main(String[] args) {
//        MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
//        System.out.println(circularQueue.enQueue(1));// return true
//        System.out.println(circularQueue.enQueue(2));  // return true
//        System.out.println(circularQueue.enQueue(3));  // return true
//        System.out.println(circularQueue.enQueue(4));  // return false, the queue is full
//        System.out.println(circularQueue.Rear());  // return 3
//        System.out.println(circularQueue.isFull());  // return true
//        System.out.println(circularQueue.deQueue());  // return true
//        System.out.println(circularQueue.enQueue(4));  // return true
//        System.out.println(circularQueue.Rear());  // return 4

        MyCircularQueue circularQueue = new MyCircularQueue(2); // set the size to be 3
        System.out.println(circularQueue.enQueue(1));// return true
        System.out.println(circularQueue.enQueue(2));  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.Rear());  // return 3
        System.out.println(circularQueue.isFull());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.enQueue(4));  // return true
        System.out.println(circularQueue.Rear());  // return 4
    }

   static class MyCircularQueue {

        int [] array=null;
       /**
        * 指向当前打一个元素
        */
        int head=0;
       /**
        * 指向当前等待插入的元素的位置（还未插入）
        */
        int tail=0;
       /**
        * 队列大小，其中有一个是空位
        */
        int size=0;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.array=new int[k+1];
            this.size=k+1;
            this.head=0;
            this.tail=0;

        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(isFull()){
               return false;
            }
            array[tail]=value;
            tail=(++tail)%size;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(isEmpty()){
                return false;
            }
            head=(++head)%size;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if(isEmpty()){
                return -1;
            }
            return array[head];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(isEmpty()){
                return -1;
            }
            if(tail-1>=0){
                return array[tail-1];
            }
            return array[tail-1+size];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return head==tail;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return head==(tail+1)%size;
        }
    }
}
