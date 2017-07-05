package trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Yonatan on 19/11/2016.
 */
public class Tree<T> {
    private T value;
    private List<Tree<T>> children;

    public Tree(T value) {
        this.value = value;
        this.children = new LinkedList<Tree<T>>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    public Tree<T> addChild(Tree<T> child){
        this.children.add(child);
        return child;
    }

    public void leftPrint(){
        System.out.println(this.value);
        for(Tree<T> child : children)
            child.leftPrint();
    }

    public void rightPrint(){
        for(Tree<T> child : children)
            child.leftPrint();
        System.out.println(this.value);
    }

    public void breadthPrint(){
        List<Tree<T>> queue = new LinkedList<Tree<T>>();
        queue.add(this);

        while(!queue.isEmpty()){
            Tree<T> node = queue.remove(0);
            System.out.println(node.getValue());
            for(Tree<T> child: node.getChildren())
                queue.add(child);
        }
    }

}
