package threading;

import threading.Customers.AddCustomerTask;
import threading.Customers.CustomersList;
import threading.Customers.RemoveCustomerTask;
import threading.Numbers.NumbersTask;

import java.util.concurrent.*;

public class Main {
    //Customer deadlock main
    public static void main(String[] args) {
        CustomersList customersList = new CustomersList();
        Thread removeCustomers = new Thread(new RemoveCustomerTask(customersList));
        removeCustomers.start();
        System.out.println("Started thread to remove customers");

        for(int i=0; i<2; i++){
            Thread addCustomers = new Thread(new AddCustomerTask(customersList, i*100));
            addCustomers.start();
            System.out.println("Started thread to add customers");
        }
    }


    //Customer threading main
    /*public static void main(String[] args) {
        CustomerManager customerManager = new CustomerManager();
        GenerateCustomerTask task = new GenerateCustomerTask(customerManager);
        for(int user = 0; user < 10; user++){
            new Thread(task).start();
        }

        while(true){
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            customerManager.howManyCustomers();
            customerManager.displayCustomers();
        }
    }*/

    //EvenNumber main
    /*public static void main(String[] args) {
        EvenNumberHolder evenNumberHolder = new EvenNumberHolder();
        EvenTask task = new EvenTask(evenNumberHolder);

        for (int i = 0; i < 15; i++) {
            new Thread(task).start();
        }
    }*/

    //Numbers main
    /*public static void main(String[] args) {
        System.out.println("Starting main method");
        NumbersTask task = new NumbersTask();

        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Double> result =  pool.submit(task);

        while(!result.isDone()){
            System.out.println("Still waiting...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Finished main method");
    }*/
}
