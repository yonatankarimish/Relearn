package memory.escaping_references;

public class Customer implements CustomerReadOnly {
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public Customer(Customer copyCustomer){
	    this.name = copyCustomer.name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
