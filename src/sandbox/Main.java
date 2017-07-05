package sandbox;

import trees.Tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yonatan on 21/11/2016.
 */
public class Main {
    public static void main(String[] args) {
        String[] collection = {
                "arirang",
                "arithmetics",
                "ariagorn",
                "aristothnes",
                "aristotle"
        };
        System.out.println(longestCommonPrefix(collection));
    }

    private static boolean areIsomorphic(String a, String b){
        if(a.length()!=b.length())
            return false;
        Map<Character, Character> table = new HashMap<Character, Character>();
        for(int i=0; i<b.length(); i++){
            char charOfA = a.charAt(i);
            char charOfB = b.charAt(i);
            if(table.containsKey(charOfA)) {
                if (!table.get(charOfA).equals(charOfB)) {
                    return false;
                }
            }
            else if(table.containsValue(charOfB))
                return false;
            table.put(charOfA,charOfB);
        }
        return true;
    }

    private static String longestCommonPrefix(String[] array){
        String control = array[0];
        for(;control.length()>0; control = control.substring(0,control.length()-1)){
            for(String current : array){
                if(current.indexOf(control)!=0)
                    break;
                if(array[array.length-1].equals(current))
                    return control;
            }
        }
        return "{no common prefix}";
    }

    private static Tree<Integer> invertBinaryTree(Tree<Integer> tree){
        return tree;
    }
}
