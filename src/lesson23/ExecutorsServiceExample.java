package lesson23;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class MyTask implements Callable<String> {

    private static int nextId = 0;
    private int jobId = nextId++;
    @Override
    public String call() throws Exception {
        System.out.println("Job " + jobId + " starting.");
        // System.out.println("Thread "+ Thread.currentThread().getName());
        try {
            Thread.sleep((int)Math.random()*2000 + 1000); // 1 <= sleep <3s
        } catch (InterruptedException ex) {
            System.out.println("Job "+jobId + " received shutdown request");
            return "Job " + jobId + "early shutdown result.";
        }
        if(Math.random() > 0.7) {
            System.out.println("Job with "+ jobId+ " throwing exception");
            throw new SQLException("Job "+ jobId + " broke database!");
        }
        System.out.println("Job with "+jobId + " completed normally");
        return "Job "+ jobId + " normal result.";
    }
}

public class ExecutorsServiceExample {
    public static void main(String[] args) {
        final int JOB_COUNT = 5;
        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<String>> handles = new ArrayList<>();

        for(int i=0;i<JOB_COUNT;i++) {
            handles.add(es.submit(new MyTask()));
        }

        //6. cancel a job that is mid-run - 2,6.5 or 3,6.5
        try {
            Thread.sleep(800); // 800 < 1000 inside job
            handles.get(1).cancel(true);
        } catch (InterruptedException ex) {
            System.out.println("main thread interrupted??");
        }


        es.shutdown(); // 2 shut down the pool normally - also message below

        // es.shutdownNow(); // 3 shut down forcibly, empty the queue

        // 5 receive the results - 5,4
        System.out.println("All jobs submitted to the pool");
        while (handles.size() > 0) {
            Iterator<Future<String>>  ifs = handles.iterator();
            while (ifs.hasNext()) {
                Future<String> fs = ifs.next();
                if(fs.isDone()) {
                    ifs.remove();
                    try {
                        String jobResult = fs.get();
                        System.out.println("Got a job result: "+jobResult);
                    } catch (InterruptedException ex) {
                        // should never happen
                        System.out.println("main thread interrupted??");
                    } catch (ExecutionException ex) {
                        System.out.println("Job threw an exception: "+ ex.getCause());
                    } catch (CancellationException ex) {
                        System.out.println("job was cancelled!");
                    }
                }
            }
        }



        // // 4. wait for things to finish 2,3 or 2,4
        // try {
        //     es.awaitTermination(10, TimeUnit.MINUTES);
        // } catch (InterruptedException e) {
        //     System.out.println("main thread interrupted??");
        // }
        // System.out.println("main exiting");


    }
}
