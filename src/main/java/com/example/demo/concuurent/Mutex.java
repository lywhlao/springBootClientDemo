package com.example.demo.concuurent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: laojiaqi
 * @Date: 2019/6/26 08:13
 * @Description:
 */
public class Mutex {

    public sync sync = new sync();

    static class sync extends AbstractQueuedSynchronizer {

        public sync() {
            setState(100);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(100, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(100);
            return true;
        }
    }


    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }
}
