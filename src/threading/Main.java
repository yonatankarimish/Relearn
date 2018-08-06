package threading;

import threading.Customers.CustomerManager;
import threading.Customers.GenerateCustomerTask;
import threading.EvenNumbers.EvenNumberHolder;
import threading.EvenNumbers.EvenTask;

public class Main {
    public static void main(String[] args) {
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


        /*EvenNumberHolder evenNumberHolder = new EvenNumberHolder();
        EvenTask task = new EvenTask(evenNumberHolder);

        for (int i = 0; i < 15; i++) {
            new Thread(task).start();
        }*/
    }
}
