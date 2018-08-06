package threading.EvenNumbers;

public class EvenTask implements Runnable{
    private EvenNumberHolder evenNumberHolder;

    public EvenTask(EvenNumberHolder evenNumberHolder) {
        this.evenNumberHolder = evenNumberHolder;
    }

    public void run(){
        //for(int i=0; i<10; i++){
        while(true){
            int value = evenNumberHolder.getNextEven();
            System.out.println("Thread " + Thread.currentThread().getName() + " has got the number " + value);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
