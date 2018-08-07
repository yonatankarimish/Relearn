package threading.Numbers;

import java.util.concurrent.Callable;

public class NumbersTask implements Callable<Double> {
    private int someVariable;

    @Override
    public Double call() throws Exception{
        Double result = 0D;
        System.out.println("Task " + Thread.currentThread().getName() + " has started");
        try {
            result = longRunningMethod();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); //Since the call() method is the top of the call stack,
        }

        System.out.println("Task " + Thread.currentThread().getName() + " has finished");
        return result;
    }

    public Double longRunningMethod() throws InterruptedException{
        Double j = 1.01;
        for(long l = 1; l < 1000000000L; l++){
            j += 1.01;
            if(Thread.interrupted()){
                throw new InterruptedException(); //Bubble the InterruptedException up the call stack
            }
        }
        return j;
    }
}
