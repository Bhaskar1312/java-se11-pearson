package lesson24;

import java.util.concurrent.Phaser;

public class UsePhaser {
    public static void main(String[] args) throws InterruptedException {
        Phaser p = new Phaser(1); // one registered, this main thread
        System.out.println("registered is: "+ p.getRegisteredParties());
        new Thread(() -> {
            p.register(); // this thread is now a participant
            for(int i=0;i<3;i++) {
                System.out.println("Worker about to arriveAndAwaitAdvance");
                p.arriveAndAwaitAdvance();
            }
            System.out.println("Worker waiting for the last time");
            p.arriveAndDeregister(); // not interested anymore
            System.out.println("Worker ends");
        }).start();

        while (p.getRegisteredParties() < 2) { // main thread to wait for the worker to register
            Thread.sleep(1); // without this, flow goes to p.getRegisteredParties() > 1
        }

        System.out.println("worker launched and registered, registered is "+p.getRegisteredParties());

        while (p.getRegisteredParties() > 1) {
            Thread.sleep(1000);
            System.out.println("main tick");
            p.arriveAndAwaitAdvance();
        }
        p.arriveAndDeregister();
        System.out.println("All Done");
    }
}
