package threading.EvenNumbers;

public class EvenNumberHolder {
    private int currentEven;

    public int getNextEven(){
        //currentEven += 2;
        synchronized (this) {
            currentEven++;
            currentEven++;
        }
        return currentEven;
    }
}
