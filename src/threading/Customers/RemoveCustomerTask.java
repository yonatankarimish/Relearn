package threading.Customers;
public class RemoveCustomerTask implements Runnable{
	private CustomersList customersList;

	@Override
	public void run() {
		while (true) {
			Customer nextCustomer = customersList.getFromList();
			System.out.println(Thread.currentThread().getName() + " removed customer " + nextCustomer.getId());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public RemoveCustomerTask(CustomersList customersList) {
		this.customersList = customersList;
	}
}