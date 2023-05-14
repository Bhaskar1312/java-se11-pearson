package lesson24;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReEntrantLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
        var wlk = rrwl.writeLock();
        var cond = wlk.newCondition();

        new Thread(()-> {
            System.out.println("tl - Started, taking lock");
            wlk.lock();
            try {
                System.out.println("t1 - sleeping");
                Thread.sleep(1000); // consider some db/api action, sleeping inside lock is not good
                System.out.println("t1 - starting await");
                cond.await(); // temporarily release lock, will not continue until get signal from other thread,
                System.out.println("t1 - restarted from await");
            } catch (InterruptedException ex) {
                System.out.println("t1 - That shouldn't have happened");
            } finally {
                System.out.println("t1 - releasing lock");
                wlk.unlock();
            }
        }).start();

        new Thread(() -> {
            System.out.println("t2 - starting about to sleep");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("t2 - That shouldn't have happened");
            }
            System.out.println("t2 - attempting to get lock");
            wlk.lock();
            try {
                System.out.println("t2 - sleeping");
                Thread.sleep(1000);
                cond.signal(); // doesn't release the lock, until releases the lock, await will not continue, even though signal is sent
                System.out.println("t2 - condition has been signalled");
            } catch (InterruptedException ex) {
                System.out.println("t2 - That shouldn;t have happened..");
            } finally {
                System.out.println("t2 - releasing the lock");
                wlk.unlock();
            }
        }).start();
    }
    // The lock must be LOCKED in order to be calling signal() method
}
