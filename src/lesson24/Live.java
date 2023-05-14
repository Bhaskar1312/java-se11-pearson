package lesson24;

import java.util.concurrent.Semaphore;

public class Live {
    private static Semaphore resources = new Semaphore(20);

    private static int obtainResources(int count) {
        if(resources.tryAcquire(count)) return count;
        else return 0;
    }

    private static void returnResources(int count) {
        resources.release(count);
    }

    private static void delay(int d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Runnable r = () -> {
          while (true) {
              // delay((int)Math.random()*50);
              // obtain resource batch one
              if(obtainResources(4) == 4) {
                  delay(100);
                  if(obtainResources(6) == 6) {
                      System.out.println("Success");
                      System.exit(0);
                      delay(200); // doing more work
                      returnResources(6);
                  } else {
                      System.out.println("failed");
                  }
                  returnResources(4);
              }
          }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
    // with 10 resources, 1 thread - success
    // 9 resources, 1 thread - fails
    // 10 resources, 2 threads - fails most of the time
    // 14 resources, 2 threads - 1 thread succeeds
    // 20 both success?
}
