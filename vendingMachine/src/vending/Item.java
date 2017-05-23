package vending;

public enum Item {

	COKE("coke", 25), PEPSI("pepsi", 35), SODA("soda", 45);
	private String name;
	private int price;
	private Item(String name, int price){
		this.name = name;
		this.price = price;	
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
}
