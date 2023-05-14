package lesson24;

import java.util.concurrent.atomic.AtomicInteger;

public class QuizAtomicInt {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                System.out.println(ai.get() + ", "+ai.incrementAndGet());
            }).start();
        }
        // The first values could be [0-3]. The same number might appear more than once
        // The second values will have each number [1-4] exactly once, as atomicInt doesn't allow for reading while writing
        // so the increments will happen in a serializable manner
    }
}
