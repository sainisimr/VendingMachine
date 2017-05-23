package vending;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineImpl implements VendingMachine{
	
	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	private Inventory<Item> itemInventory = new Inventory<Item>();
	private Item currentItem;
	private long currentBalance;
	private long totalSales;
	
 	public VendingMachineImpl(){
		initialize();
	}
	
	private void initialize(){
		for(Coin c : Coin.values()){
			cashInventory.put(c, 5);
		}
		
		for(Item m : Item.values()){
			itemInventory.put(m, 5);
		}
	}
	
	@Override
	public long selectItemAndGetPrice(Item item) {
		// TODO Auto-generated method stub
		if(itemInventory.hasItem(item)){
			currentItem = item;
			return currentItem.getPrice();
		}
		throw new SoldOutException("Sold out, buy another");
	}

	@Override
	public void insertCoin(Coin coin) {
		// TODO Auto-generated method stub
		currentBalance = currentBalance + coin.getDenomination();
		cashInventory.add(coin);
	}

	@Override
	public List<Coin> refund() {
		// TODO Auto-generated method stub
		List<Coin> refund = getChange(currentBalance);
		updateCashInventory(refund);
		currentBalance = 0;
		currentItem = null;
		return refund;
	}

	private void updateCashInventory(List<Coin> refund) {
		// TODO Auto-generated method stub
		
	}

	private List<Coin> getChange(long currentBalance2) throws NotSufficientChangeException {
		// TODO Auto-generated method stub
		List<Coin> changes = Collections.EMPTY_LIST;
		if(currentBalance2 > 0){
			changes = new ArrayList<Coin>();
			long balance = currentBalance2;
			while(balance>0){
				if(balance>=Coin.QUARTER.getDenomination()&&cashInventory.hasItem(Coin.QUARTER)){
					changes.add(Coin.QUARTER);
					balance = balance-Coin.QUARTER.getDenomination();
					continue;
				}
				else if(balance>=Coin.DIME.getDenomination()&&cashInventory.hasItem(Coin.DIME)){
					changes.add(Coin.DIME);
					balance = balance - Coin.DIME.getDenomination();
					continue;
				}
				else if(balance>=Coin.NICKLE.getDenomination()&&cashInventory.hasItem(Coin.NICKLE)){
					changes.add(Coin.NICKLE);
					balance = balance - Coin.NICKLE.getDenomination();
					continue;
				}
				else if(balance>=Coin.PENNY.getDenomination()&&cashInventory.hasItem(Coin.PENNY)){
					changes.add(Coin.PENNY);
					balance = balance - Coin.PENNY.getDenomination();
					continue;
				}
				else{
					throw new NotSufficientChangeException("Not sufficient change");
				}
			}
		}
		return changes;
	}

	@Override
	public Bucket<Item, List<Coin>> collectItemAndChange() {
		// TODO Auto-generated method stub
		Item item = collectItem();
		totalSales = totalSales + item.getPrice();
		List<Coin> change = collectChange();
		return new Bucket<Item, List<Coin>>(item, change);
	}

	private List<Coin> collectChange() {
		// TODO Auto-generated method stub
		long changeAmount = currentBalance - currentItem.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null; return change;
	}

	private Item collectItem() throws NotSufficientChangeException,
    NotFullPaidException{
		if(isFullPaid()){
			if(hasSufficientChange()){
                itemInventory.deduct(currentItem);
                return currentItem;
            
		}
		// TODO Auto-generated method stub
			throw new NotSufficientChangeException("Not Sufficient change");
	}
		long remainingBalance = currentItem.getPrice() - currentBalance;
		throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);

			}

	private boolean isFullPaid() { 
		if(currentBalance >= currentItem.getPrice())
		{ 
			return true; 
			} 
		return false; 
		}


	private boolean hasSufficientChange() {
		// TODO Auto-generated method stub
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}

	private boolean hasSufficientChangeForAmount(long l) {
		// TODO Auto-generated method stub
		boolean hasChange = true;
		try{
			getChange(l);
			}
		catch(NotSufficientChangeException nsce)
		{ 
			return hasChange = false;
			}
		return hasChange;

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		cashInventory.Clear();
		itemInventory.Clear();
		totalSales = 0;
		currentItem = null;
		currentBalance = 0;
	}


	
}
