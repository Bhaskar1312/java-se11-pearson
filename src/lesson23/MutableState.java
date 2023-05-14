package lesson23;

public class MutableState {
    private static int i; // bad behavior
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for(i=0;i<100;i++) {
                System.out.println(Thread.currentThread().getName() + " i is "+ i);
            }
        };
        r.run();// The current thread runs, no multithreading involved, so synchronised
        Thread t1 = new Thread(r, "Worker thread");
        Thread t2 = new Thread(r, "Another thread");
        t1.start();
        t2.start();
        t1.join(); // wait for this thread to die
        t2.join(); // wait for this thread to die
        System.out.println("Main finished");
    }
}
