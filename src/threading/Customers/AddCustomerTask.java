package threading.Customers;
public class AddCustomerTask implements Runnable{
	private CustomersList customersList;
	private int startNumber;
	
	public AddCustomerTask(CustomersList customersList, int startNumber) {
		this.customersList = customersList;
		this.startNumber = startNumber;
	}

	@Override
	public void run() {
		for (int i = startNumber; i < startNumber + 100; i++) {
			Customer customer = new Customer(i,"Customer " + i);
			customersList.addToList(customer);
			System.out.println(Thread.currentThread().getName() + " added customer " + i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}