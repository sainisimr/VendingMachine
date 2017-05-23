package vending;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest {

	private static VendingMachine vm;
	@BeforeClass
	public static void setup(){
		vm = VendingMachineFactory.createVendingMachine();
	}
	
	@AfterClass
	public static void tearDown(){
		vm = null;
	}
	
	private long getTotal(List<Coin> change){
		long total = 0;
		for(Coin c : change ){
			total = total + c.getDenomination();
		}
		//System.out.println(total);
		return total;
	}
	
	@Test
	public void test() {
		long price = vm.selectItemAndGetPrice(Item.COKE);
		assertEquals(Item.COKE.getPrice(), price);
		vm.insertCoin(Coin.QUARTER);
		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();
		assertEquals(Item.COKE, item);
		assertTrue(change.isEmpty());
	}
	
	@Test
	public void test1() {
		long price = vm.selectItemAndGetPrice(Item.PEPSI);
		assertEquals(Item.PEPSI.getPrice(), price);
		vm.insertCoin(Coin.QUARTER);
		vm.insertCoin(Coin.QUARTER);
		Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
		Item item = bucket.getFirst();
		List<Coin> change = bucket.getSecond();
		assertEquals(Item.PEPSI, item);
		assertTrue(!change.isEmpty());
		assertEquals(50-Item.PEPSI.getPrice(), getTotal(change));
	}
	
	@Test
	public void test2() {
		long price = vm.selectItemAndGetPrice(Item.SODA);
		assertEquals(Item.SODA.getPrice(), price);
		vm.insertCoin(Coin.QUARTER);
		vm.insertCoin(Coin.QUARTER);
		vm.insertCoin(Coin.DIME);
		vm.insertCoin(Coin.NICKLE);
		vm.insertCoin(Coin.PENNY);
		assertEquals(66, getTotal(vm.refund()));
		
	}

	@Test(expected=SoldOutException.class) 
	public void testSoldOut(){ 
		for (int i = 0; i < 5; i++) 
		{
			vm.selectItemAndGetPrice(Item.COKE);
			vm.insertCoin(Coin.QUARTER);
			vm.collectItemAndChange();
			}
		}
	
	@Test(expected=SoldOutException.class)
	public void testReset(){
		VendingMachine vmachine = VendingMachineFactory.createVendingMachine();
		vmachine.reset();
		vmachine.selectItemAndGetPrice(Item.COKE);
		}

}
