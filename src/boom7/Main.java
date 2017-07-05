package boom7;

import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/07/16
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    private static boolean isBoom(int num){
        if(num % 7 == 0 || String.valueOf(num).indexOf("7") != -1){
            return true;
        }
        return false;
    }
    public static String printBoom(int num){
        if(isBoom(num))
            return "Boom!";
        return String.valueOf(num);
    }
    public static void main(String[] args){
        int counter=1;
        Scanner input = new Scanner(System.in);
        String text;

        if(new Random(System.currentTimeMillis()).nextLong()%2==0){
            System.out.println("1");
            counter++;
        }
        while(input.hasNextLine()){
            try{
                text = input.nextLine();
                if(text.toLowerCase().equals("boom")) {
                    if(isBoom(counter)) {
                        System.out.println(printBoom(counter+1));
                        counter+=2;
                    }
                    else{
                        System.out.println("You Lose");
                        break;
                    }
                }

                else{
                    if(isBoom(counter) || Integer.parseInt(text)!=counter){
                        System.out.println("You Lose");
                        break;
                    }
                    else{
                        System.out.println(printBoom(counter+1));
                        counter+=2;
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
                break;
            }
        }

    }
}
