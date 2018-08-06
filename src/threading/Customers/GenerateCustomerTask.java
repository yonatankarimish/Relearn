package threading.Customers;

public class GenerateCustomerTask implements Runnable {
    private CustomerManager customerManager;
    private int totalCustomersGenerated = 0;

    public GenerateCustomerTask(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep((int)(Math.random()*500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String customerName = "Test customer";
            Customer customer = new Customer(customerName);
            customerManager.addCustomer(customer);
            totalCustomersGenerated++;
        }
    }
}
