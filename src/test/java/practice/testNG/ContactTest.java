package practice.testNG;

import org.testng.annotations.Test;

public class ContactTest 
{
	@Test
	public void createContactTest()
	{
		System.out.println("excute createContact");
	}
	
	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest()
	{
		System.out.println("excute modifyContactTest");
	}
	
	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteContactTest()
	{
		System.out.println("excute deleteContactTest" );
	}
}
