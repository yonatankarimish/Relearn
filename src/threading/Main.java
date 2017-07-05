package threading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yonatan
 * Date: 27/08/16
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HashMap<String,String> heaven = new HashMap<String, String>();
        heaven.put("אראלי מעלה","אדוני אדוננו");
        heaven.put("בחירי סגולה","אדוני הוא האלוהים");
        heaven.put("יחידי סגולה","אדוני אלוהינו");


        ExecutorService pool = Executors.newCachedThreadPool();
        for(String chanter : heaven.keySet()){
            Prayer prayer = new Prayer(heaven.get(chanter));
            FutureTask future = new FutureTask<String>(prayer);
            pool.execute(future);
        }

    }
}
