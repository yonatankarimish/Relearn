package memory.escaping_references;
import java.util.*;
import java.util.function.Consumer;

public class CustomerRecords implements Iterable<Customer>{
	private Map<String, Customer> records;
	
	public CustomerRecords() {
		this.records = new HashMap<String, Customer>();
	}
	
	public void addCustomer(Customer c) {
		this.records.put(c.getName(), c);
	}
		
	public Map<String, Customer> getCustomers() {
		return Collections.unmodifiableMap(this.records);
	}

	public CustomerReadOnly getCustomerByName(String name){
		return this.records.get(name);
	}

	@Override
	public Iterator<Customer> iterator() {
		return records.values().iterator();
	}

	@Override
	public void forEach(Consumer<? super Customer> action) {
		records.values().forEach(action);
	}

	@Override
	public Spliterator<Customer> spliterator() {
		return records.values().spliterator();
	}
}
