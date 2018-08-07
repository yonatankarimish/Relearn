package threading.Customers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomersList {
	private List<Customer> customers = new ArrayList<Customer>();
	private Lock customersLock = new ReentrantLock();
	private Condition customersAvailable = customersLock.newCondition();
	private Condition listHasSpace = customersLock.newCondition();

	public void addToList(Customer customer) {
		customersLock.lock();
        while (customers.size() >= 100) {
            try {
                System.out.println(Thread.currentThread().getName() + " Waiting for customer list to clear before adding");
                //Thread.sleep(500); //Sleeps for 500 milliseconds, while retaining the lock defined by the synchronized(this) block
                //wait(); //Waits indefinitely, and releases the lock defined by the synchronized(this) block
                listHasSpace.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        customers.add(customer);
        //notifyAll(); //Causes all threads waiting on this object to attempt resuming execution, after this thread releases the lock defined by the synchronized(this) block
        //notify(); //Causes a random thread waiting on this object to attempt resuming execution, after this thread releases the lock defined by the synchronized(this) block
        customersAvailable.signalAll();
        customersLock.unlock();
	}

	public Customer getFromList() {
		customersLock.lock();
		try {
            while (customers.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Waiting for customer list to fill before removing");
                    //Thread.sleep(500); //Sleeps for 500 milliseconds, while retaining the lock defined by the synchronized(this) block
                    //wait(); //Waits indefinitely, and releases the lock defined by the synchronized(this) block
                    customersAvailable.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //notifyAll(); //Causes all threads waiting on this object to attempt resuming execution, after this thread releases the lock defined by the synchronized(this) block
            listHasSpace.signalAll();
            return customers.remove(0);
        } finally {
            customersLock.unlock();
        }
	}
}