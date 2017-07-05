package innerClasses;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class Outer {
    protected int start;
    protected int end;
    protected int number;
    protected boolean isDone = false;


    public Outer(int start, int end, int number){
        this.start = start;
        this.end = end;
        this.number = number;
    }

    public boolean calcMethod(){
        class Inner  {
            public boolean Calculate(){
                int inStart = Outer.this.start;
                int inEnd  = Outer.this.end;
                int inNumber =  Outer.this.number;
                return inStart <= inNumber && inNumber <= inEnd;
            }
        }
       Inner inner = new Inner();
        this.isDone = true;
        return inner.Calculate();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isDone() {
        return isDone;
    }

}
