package vending;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {
	private Map<T, Integer> inventory = new HashMap<T, Integer>();

	public void put(T item, int quantity) {
		// TODO Auto-generated method stub
		inventory.put(item, quantity);
	} 
	
	public boolean hasItem(T item){
		return getQuantity(item)>0;
	}
	
	private int getQuantity(T item) {
		// TODO Auto-generated method stub
		Integer value = inventory.get(item);
		return value == null ? 0 : value;
	}

	public void add(T item) {
		// TODO Auto-generated method stub
		int count = inventory.get(item);
		inventory.put(item, count+1);
	}

	public void Clear() {
		// TODO Auto-generated method stub
		inventory.clear();
		
	}

	public void deduct(T item) {
		if (hasItem(item)) {
		int count = inventory.get(item);
		inventory.put(item, count - 1); 
		}

		
	}
	
	
}
