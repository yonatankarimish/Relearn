package threading.Customers;

import java.util.ArrayList;
import java.util.List;

public class CustomersList {
	private List<Customer> customers = new ArrayList<Customer>();

	public void addToList(Customer customer) {
		synchronized (this) {
            while (customers.size() >= 100) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Waiting for customer list to clear before adding");
                    //Thread.sleep(500); //Sleeps for 500 milliseconds, while retaining the lock defined by the synchronized(this) block
                    wait(); //Waits indefinitely, and releases the lock defined by the synchronized(this) block
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
			customers.add(customer);
			notifyAll(); //Causes all threads waiting on this object to attempt resuming execution, after this thread releases the lock defined by the synchronized(this) block
            //notify(); //Causes a random thread waiting on this object to attempt resuming execution, after this thread releases the lock defined by the synchronized(this) block
		}
	}

	public Customer getFromList() {
		synchronized (this) {
			while (customers.size() == 0) {
				try {
                    System.out.println(Thread.currentThread().getName() + " Waiting for customer list to fill before removing");
					//Thread.sleep(500); //Sleeps for 500 milliseconds, while retaining the lock defined by the synchronized(this) block
					wait(); //Waits indefinitely, and releases the lock defined by the synchronized(this) block
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
            notifyAll(); //Causes all threads waiting on this object to attempt resuming execution, after this thread releases the lock defined by the synchronized(this) block
			return customers.remove(0);
		}
	}
}