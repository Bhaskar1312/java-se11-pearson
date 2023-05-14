package lesson24;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Thing {
    public String text;
    public long count;
    public double value;

    public Thing(String text, long count, double value) {
        this.text = text;
        this.count = count;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Thing{" +
            "text='" + text + '\'' +
            ", count=" + count +
            ", value=" + value +
            '}';
    }
}

public class QuizBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Thing> bqt = new ArrayBlockingQueue<>(10) ;
        new Thread(() -> {
            Thing t = new Thing("Hello", 99, 2.7);
            try {
                bqt.put(t);
                t.count = 200;
            } catch (InterruptedException ex) {

            }
        }).start();
        System.out.println(bqt.take());

        // The thing will be transferred from second thread to foreground thread
        // HB(put, take) -> all changes made during init will be successfully published
        // t.count = 200 is a race condition -> so value could be 99 or 200
    }
}
