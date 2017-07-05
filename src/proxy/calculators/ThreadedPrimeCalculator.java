package proxy.calculators;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Yonatan on 31/10/2016.
 */
public class ThreadedPrimeCalculator implements IPrimeCalculator {

    private int poolSize = 20;
    private int finishedWorkers = 0;
    private Set<Future<Boolean>> results = new HashSet<Future<Boolean>>();

    public Boolean isPrime(long number) {
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        for(int i=0; i<this.poolSize; i++){
            RootDigger worker = new RootDigger(number,i);
            Future<Boolean> future = pool.submit(worker);
            results.add(future);
        }
        while(finishedWorkers<poolSize){
            for(Future<Boolean> part : results){
                if(part.isDone()){
                    try {
                        if(part.get()){
                            finishedWorkers++;
                        }
                        else{
                            pool.shutdown();
                            return false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        pool.shutdown();
        return true;
    }

    public int getPoolSize() {
        return this.poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    private class RootDigger implements Callable<Boolean> {
        private Boolean isPrime = false;
        private volatile Boolean isDone = false;

        private long number;
        private int part;

        public RootDigger(long number, int part) {
            this.number = number;
            this.part = part;
        }

        @Override
        public Boolean call() throws Exception {
            long root = (long)Math.ceil(Math.sqrt(number));
            long start = this.part==0? 2: root/ThreadedPrimeCalculator.this.poolSize*this.part;
            long end = start+root/ThreadedPrimeCalculator.this.poolSize;

            try {
                for (long i = start; i < end; i++)
                    if (number % i == 0)
                        return false;
                return true;
            }finally {
                this.isDone = true;
            }
        }
    }
}

