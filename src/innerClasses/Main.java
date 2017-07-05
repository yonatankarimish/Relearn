package innerClasses;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n1, n2, n3;

        System.out.println("Enter three numbers");
        System.out.print("First:");
        n1 = scanner.nextInt();
        System.out.print("Second:");
        n2 = scanner.nextInt();
        System.out.print("Third:");
        n3 = scanner.nextInt();

        Outer outer = new Outer(n1, n2, n3);
        boolean result = outer.calcMethod();
        System.out.println(result);
    }
}
