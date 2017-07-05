package fibonacci;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 */
public class SimpleFibonacciCalculator implements IFibonacciCalculator {
    @Override
    public Boolean resultsFor(long number) {
        StringBuilder builder = new StringBuilder();
        long counter1 = 1;
        long counter2 = 1;
        long interim;
        while(counter2<=number){
            if(counter2==number)
                return true;
            else{
                interim = counter1;
                counter1 = counter2;
                counter2 += interim;
            }
        }
        return false;
    }
}
