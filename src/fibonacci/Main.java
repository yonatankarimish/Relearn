package fibonacci;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */
public class Main {
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the  number to check:");
            long number = in.nextLong();

            System.out.println("Enter the Printer type for the calculation");
            IFibonacciCalculator calculator;
            String calcType = in.next().toLowerCase();
            if (calcType.equals("simple")) {
                calculator = new SimpleFibonacciCalculator();

            } else {
                calculator = new SimpleFibonacciCalculator();
            }
            System.out.println("The result for "+number+"  is:" + calculator.resultsFor(number));
        }
}
