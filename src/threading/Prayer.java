package threading;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
public class Prayer<String> implements Callable<String> {
    private String phrase;
    private volatile Boolean isDone = false;
    public Prayer(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String call() throws Exception {
        Random rest = new Random();
        while(!this.isDone){
            Thread.sleep(rest.nextInt(1000));
            System.out.println(this.phrase);
        }
        return this.phrase;
    }

    public void Stop(){
        this.isDone = false;
    }
}
