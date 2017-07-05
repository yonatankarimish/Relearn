package trees;

/**
 * Created by Yonatan on 19/11/2016.
 */
public class Main {
    public static void main(String[] args){
        Tree<Integer> tree = buildTree();
        System.out.println(" ======== LEFT PRINT ========");
        tree.leftPrint();
        System.out.println(" ======== RIGHT PRINT ========");
        tree.rightPrint();

        System.out.println(" ======== BREADTH PRINT ========");
        tree.breadthPrint();
    }

    private static Tree<Integer> buildTree(){
        Tree<Integer> root = new Tree<Integer>(5);

        Tree<Integer> aChild1 = new Tree<Integer>(9);
        Tree<Integer> aChild2 = new Tree<Integer>(2);
        Tree<Integer> aChild3 = new Tree<Integer>(3);

        Tree<Integer> aChild1_1 = new Tree<Integer>(1);
        Tree<Integer> aChild1_2 = new Tree<Integer>(4);
        Tree<Integer> aChild1_3 = new Tree<Integer>(5);
        Tree<Integer> aChild1_4 = new Tree<Integer>(3);

        Tree<Integer> aChild2_1 = new Tree<Integer>(1);
        Tree<Integer> aChild2_2 = new Tree<Integer>(8);

        Tree<Integer> aChild3_1 = new Tree<Integer>(4);
        Tree<Integer> aChild3_2 = new Tree<Integer>(6);
        Tree<Integer> aChild3_3 = new Tree<Integer>(7);

        Tree<Integer> aChild1_1_1 = new Tree<Integer>(2);

        Tree<Integer> aChild1_2_1 = new Tree<Integer>(1);
        Tree<Integer> aChild1_2_2 = new Tree<Integer>(7);

        Tree<Integer> aChild2_1_1 = new Tree<Integer>(8);

        Tree<Integer> aChild2_2_1 = new Tree<Integer>(9);

        Tree<Integer> aChild3_3_1 = new Tree<Integer>(3);
        Tree<Integer> aChild3_3_2 = new Tree<Integer>(8);

        aChild1_1.addChild(aChild1_1_1);
        aChild1_2.addChild(aChild1_2_1);
        aChild1_2.addChild(aChild1_2_2);
        aChild2_1.addChild(aChild2_1_1);
        aChild2_2.addChild(aChild2_2_1);
        aChild3_3.addChild(aChild3_3_1);
        aChild3_3.addChild(aChild3_3_2);

        aChild1.addChild(aChild1_1);
        aChild1.addChild(aChild1_2);
        aChild1.addChild(aChild1_3);
        aChild1.addChild(aChild1_4);

        aChild2.addChild(aChild2_1);
        aChild2.addChild(aChild2_2);

        aChild3.addChild(aChild3_1);
        aChild3.addChild(aChild3_2);
        aChild3.addChild(aChild3_3);

        root.addChild(aChild1);
        root.addChild(aChild2);
        root.addChild(aChild3);

        return root;
    }
}
