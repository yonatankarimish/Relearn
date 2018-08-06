package threading.Customers;

public class Customer {
    private int id;
    private String name;

    public Customer(String name) {
        super();
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " : " + name;
    }
}
