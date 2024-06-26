package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyExclusiveLock implements Lock {
     private final Sync sync;

    public MyExclusiveLock() {
        sync = new Sync();
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
           sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
          sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }



    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
             if(compareAndSetState(0,1)){
                 setExclusiveOwnerThread(Thread.currentThread());
                 return true;
             }
             return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(!isHeldExclusively()){
               throw new IllegalMonitorStateException();
            }
            setState(0);
            setExclusiveOwnerThread(null);
            return true;

        }

        protected boolean isLocked(){
            return getState()!=0;
        }

        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread()==getExclusiveOwnerThread();
        }
        protected Condition newCondition(){
            return new ConditionObject();
        }
    }
}
