package lesson24;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UseAtomicInt {
    // private static int ai = 0;
    // private static volatile int ai = 0;
    private static final AtomicInteger ai = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        final int WORKERS = 4;
        List<Thread> list = new ArrayList<>();
        for(int i=0;i<WORKERS;i++) {
            Thread t1 = new Thread(()-> {
                for (int j = 0; j < 1_000_000; j++) {

                    // ai++;
                    ai.incrementAndGet();
                } 
            });
            list.add(t1);
            t1.start();
        }
        for(Thread t: list) {
            t.join(); // wait for all threads
        }

        // System.out.println(ai);
        System.out.println(ai.get());
        // < 4_000_000 due to race condition for int, caching from cpu
        // < 4_000_000 for volatile, only race condition, read-modify-write cycle

    }
    // HB volatile variable rule: A write to volatile field happens-before every subsequent read of the same field
}
