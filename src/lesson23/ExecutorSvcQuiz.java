package lesson23;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorSvcQuiz {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(()-> System.out.println("Job running"));
        var lfs = new ArrayList<Future<String>>();
        for(int i=0;i<3;i++) {
            var id = i;
            lfs.add(es.submit(()->{
                Thread.sleep(1000);
                return "Job with "+ id + " finished";
            }));
        }
        es.shutdownNow(); // 1 // will throw ex at line 31

        // es.shutdown(); // 2

        // es.shutdown(); //3
        // es.awaitTermination(1, TimeUnit.SECONDS); //3

        for(var fs: lfs) {
            System.out.println("> "+ fs.get());
        }
    }
}
