package lesson24;

import java.util.concurrent.Exchanger;

public class QuizExchanger {
    public static void main(String[] args) {
        Exchanger<String> e = new Exchanger<>();
        Exchanger<String> f = new Exchanger<>();

        new Thread(()->{
            try {
                System.out.println("T1 e "+e.exchange(""));
                System.out.println("T1 f "+f.exchange(""));
            } catch (InterruptedException ex) {
            }
        }).start();

        new Thread(()->{
            try {
                // System.out.println("T2 e "+e.exchange(""));
                System.out.println("T2 f "+f.exchange(""));
                System.out.println("T2 e "+e.exchange(""));
            } catch (InterruptedException ex) {
            }
        }).start();
    }
    // deadlock - T1 locks until e.exchange has completed and T2 blocks until f is exchanged
    // Therefore each thread is waiting for other to complete
    // ordering is important, if you do everything in same order, this kind of deadlock doesn't appear
}
