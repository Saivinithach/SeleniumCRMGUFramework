package practice.testNG;

import org.testng.annotations.Test;

public class OrderTest 
{
	@Test(invocationCount = 10)
	public void createOrderTest()
	{
		System.out.println("execute create order test");
	}
	
	@Test(enabled = false)
	public void billingAnOrder()
	{
		System.out.println("Execute createOrderTest==>123");
	}
}
