package proxy.calculators;

/**
 * Created by Yonatan on 31/10/2016.
 */
public class SimplePrimeCalculator implements IPrimeCalculator {
    @Override
    public Boolean isPrime(long number) {
        for(long i=2; i<number; i++)
            if(number%i==0)
                return false;
        return true;
    }
}
