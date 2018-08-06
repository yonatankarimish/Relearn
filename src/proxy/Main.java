package proxy;

import proxy.calculators.*;
import proxy.proxies.PrimeCalculatorProxy;

/**
 * Created by Yonatan on 31/10/2016.
 */
public class Main {
    private static final long prime_number = 8512677386048191063L;
    private static final long composite_number = 8650415921358664919L;

    public static void main(String[] args){
        IPrimeCalculator calculator = new ThreadedPrimeCalculator();
        IPrimeCalculator proxyCalculator = (IPrimeCalculator)PrimeCalculatorProxy.newInstance(calculator);
        System.out.println("Using "+calculator.getClass().getName());
        System.out.println(prime_number + " is "+(proxyCalculator.isPrime(prime_number)? "prime" : "composite"));
    }
}
