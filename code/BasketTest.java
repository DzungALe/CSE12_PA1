package cse12pa1student;

import java.util.Collection;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BasketTest {

	public static Collection<Object[]> BAGNUMS =
			Arrays.asList(new Object[][] { {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12} });
	private int bagType;

	public BasketTest(int bagType) {
		super();
		this.bagType = bagType;
	}

	@Parameterized.Parameters(name = "Basket{index}")
	public static Collection<Object[]> bags() {
		return BAGNUMS;
	}
	
	private Basket makeBasket() {
		switch(this.bagType) {
			case 0: return new Basket0();
			case 1: return new Basket1();
			case 2: return new Basket2();
			case 3: return new Basket3();
			case 4: return new Basket4();
			case 5: return new Basket5();
			case 6: return new Basket6();
			case 7: return new Basket7();
			case 8: return new Basket8();
			case 9: return new Basket9();
			case 10: return new Basket10();
			case 11: return new Basket11();
			case 12: return new Basket12();
		}
		return null;
	}
	
	//Add null
	@Test 
	public void addedNull()
	{
		Basket basketToTest = makeBasket();
		Item item1 = null;
		basketToTest.addToBasket(item1);
		assewrEquals(false, basketToTest.removeAllFromBasket(item1));
	}
	
	//Check for empty basket
	@Test
	public void addedNothing()
	{
		Basket basketToTest = makeBasket();
		
		assertEquals(0, basketToTest.count());
		assertEquals(0, basketToTest.totalCost());
	}
	
	//Sample test
	@Test
	public void addedHasCount1() {
		Basket basketToTest = makeBasket();

		Item i = new Item("Shampoo", 5);
		basketToTest.addToBasket(i);
		assertEquals(1, basketToTest.count());
	}

	//Check duplicate items still count as separate items
	@Test
	public void addedDuplicate()
	{
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Body wash", 10);

		//Add 3 items
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item2);

		assertEquals(3, basketToTest.count()); 			//Check 3 items added
		assertEquals(2, basketToTest.countItem(item1));	//Check 2 of item 1

		basketToTest.addToBasket(item2);

		assertEquals(4, basketToTest.count());			//Check 4 items added
		assertEquals(2, basketToTest.countItem(item2));
	}

	//Check if different variable names affect count
	@Test
	public void addedHasCount2()
	{
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Body wash", 10);
		Item item3 = new Item("Shampoo", 5);

		//Add 3 items
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item2);
		basketToTest.addToBasket(item3);

		assertEquals(2, basketToTest.countItem(item1));	//Check 2 of item 1, as item1 = item3
		assertEquals(1, basketToTest.countItem(item2)); //CHeck 2 of item 2, as item2 is unique
	}
	
	//Remove nothing
	@Test
	public void removedNothing()
	{
		Item item1 = new Item("Shampoo", 5);
		
		Basket basketToTest = makeBasket();
		basketToTest.removeFromBasket(item1);
		
		assertEquals(0, basketToTest.count()); 			//Either way, should still be only 0 items
		assertEquals(0, basketToTest.totalCost());		//Cost should be 0 as well
	}

	//Proper removal tests
	/**
	 * 
	 */
	@Test
	public void removedCount1()
	{
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);

		//Add and remove 1 items
		basketToTest.addToBasket(item1);
		basketToTest.removeFromBasket(item1);

		//Cost and price should be 0
		assertEquals(0, basketToTest.count());	
		assertEquals(0, basketToTest.totalCost());
	}
	
	//Check for removal of more than 1
	@Test
	public void removedCount2()
	{
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Body wash", 10);

		//Add and remove 2 items
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item2);
		basketToTest.removeFromBasket(item1);
		basketToTest.removeFromBasket(item2);
	

		assertEquals(2, basketToTest.countItem(item1));	//Check 2 of item 1, as item1 = item2
	}
	
	//Test for removing all items from the test
	@Test
	public void removedAllFromBasketTest()
	{
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Body wash", 10);

		//Add items and remove 3 items
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item2);

		basketToTest.removeAllFromBasket(item1);

		//Should still be one item for item 2
		assertEquals(1, basketToTest.count());
	}

	//Test of removing items not in basket
	@Test
	public void removedNotInBasket()
	{
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Body wash", 10);

		//Add items and remove 2 items
		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item1);

		assertEquals(false, basketToTest.removeFromBasket(item2));
		assertEquals(true, basketToTest.removeFromBasket(item1));

		//Remove all from basket, basket should be empty
		basketToTest.removeAllFromBasket(item1);

		assertEquals(false, basketToTest.removeFromBasket(item1));
	}

	//Test cost
	@Test
	public void costCheck1()
	{
		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Body wash", 10);
		Item item3 = new Item("Towel", 50);

		basketToTest.addToBasket(item1);
		basketToTest.addToBasket(item2);
		basketToTest.addToBasket(item3);

		assertEquals(65, basketToTest.totalCost());
	}
}
