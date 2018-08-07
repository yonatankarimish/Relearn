package threading.Numbers;

public class NumbersTask implements Runnable{
    private int someVariable;

    @Override
    public void run() {
        for(int i=0; i<1000; i++){
            System.out.println("Task " + Thread.currentThread().getName() + " is outputting number: " + i);
            try {
                longRunningMethod();
            } catch (InterruptedException e) {
                break; //Since the run() method is the top of the call stack,
            }
        }

        System.out.println("Task " + Thread.currentThread().getName() + " has finished");
    }

    public void longRunningMethod() throws InterruptedException{
        System.out.println("Started LongLongMethod");
        Double j = 1.01;
        for(long l = 1; l < 100000L; l++){
            j += 1.01;
            if(Thread.interrupted()){
                throw new InterruptedException(); //Bubble the InterruptedException up the call stack
            }
        }
        System.out.println("Finished LongLongMethod");
    }
}
