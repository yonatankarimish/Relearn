package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main (String[] args){
        System.out.println("Enter the full name of a class to display its methods and properties:");
        Scanner scanner = new Scanner(System.in);
        String name =  scanner.next();
        Class obj;
        try{
            obj = Class.forName(name);
            Field[] properties = obj.getDeclaredFields();
            Method[] functions = obj.getMethods();
            System.out.println("Properties:");
            for(Field field : properties){
                System.out.println(field.toString());
            }
            System.out.println("Methods:");
            for(Method method : functions){
                System.out.println(method.toString());
            }
        }
        catch (ClassNotFoundException e){
            System.out.println("There is no such class!");
        }
    }
}
