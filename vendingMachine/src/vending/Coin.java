package vending;

public enum Coin {
	PENNY(1), DIME(10), NICKLE(5), QUARTER(25);
	int denomination;
	
	private Coin(int denomination){
		this.denomination = denomination;
	}

	public int getDenomination() {
		return denomination;
	}
	
	
}
