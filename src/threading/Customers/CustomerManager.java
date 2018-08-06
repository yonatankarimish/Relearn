package threading.Customers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomerManager {
    private List<Customer> customers = Collections.synchronizedList(new ArrayList<>()); //new CopyOnWriteArrayList<>();
    private int nextId = 0;

    public void addCustomer(Customer customer) {
        synchronized (this) { //The same lock is requested for both this block and the howManyCustomers synchronized block
            customer.setId(nextId);
            nextId++;
        }

        customers.add(customer); //Because the collection is a CopyOnWriteArrayList, we do not need to synchronize calls to it.
    }

    public void howManyCustomers() {
        int size = 0;
        //synchronized (this) { //The same lock is requested for both this block and the addCustomer synchronized block
            size = customers.size();
        //}
        System.out.println(new Date() + " : " + size + " customers created");
    }

    public void displayCustomers() {
        //synchronized (customers) {
        synchronized (this){
            for (Customer c : customers) {
                System.out.println(c.toString());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
