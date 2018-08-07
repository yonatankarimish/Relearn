package memory.book_catalog;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Price {
	private Map<String,Double> rates;
	private Double value;
	
	public Price(Double value) {
		this.value = value;
		rates = new HashMap<String,Double>();
		rates.put("USD", 1d);
		rates.put("GBP", 0.6);
		rates.put("EUR", 0.8);
	}

	public Double convert(String toCurrency) {
        double result = value;
		if (!toCurrency.equals("USD")) {
			Double conversion = rates.get("USD") / rates.get(toCurrency);
            result = conversion * value;
		}
        return result;
	}

	public String toString() {
		return this.value.toString();
	}
	
	public Map<String,Double> getRates() {
		return Collections.unmodifiableMap(rates);
	}

	public double getRate(String currency){
	    return rates.get(currency);
    }
}