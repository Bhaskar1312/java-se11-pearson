package lesson24;

import java.util.Map;
import java.util.concurrent.Semaphore;

public class QuizSemaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);
        Thread t = new Thread(() ->{
            for (int i = 0; i < 3; i++) {
                try {
                    sem.acquire();
                } catch (InterruptedException ex) {
                }
                System.out.println("Boo!");
            }
        });
        t.start();

        for (int i = 0; i < 3; i++) {
            sem.release();
            Thread.sleep(1000 + (int)(3000 * Math.random()));
        }
    }
    // permit-1, so immediate acquire boo
    // in main, immediate release, main might sleep and permit-1 immediate boo second time too
    // for 2nd release, main thread has sleep [1,3) sec, so third boo after [1,3) sec
    // for 3rd release after [1,3) after which program ends. so total [2, 6) sec after second boo
}
