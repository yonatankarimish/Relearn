package proxy.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Created by Yonatan on 31/10/2016.
 */
public class PrimeCalculatorProxy implements InvocationHandler {
    private Object original;

    public static Object newInstance(Object original){
        return  Proxy.newProxyInstance(
                original.getClass().getClassLoader(),
                original.getClass().getInterfaces(),
                new PrimeCalculatorProxy(original));
    }

    private PrimeCalculatorProxy(Object original){
        this.original = original;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Date start = new Date();
        try{
            return method.invoke(this.original, args);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            long time = new Date().getTime()-start.getTime();
            System.out.println("Calculating the prime took: "+time+" ms");
        }
        return null;
    }
}
