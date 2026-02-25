package practice.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DatProvider_Test 
{
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName,String lastName,long phoneNumber)
	{
		System.out.println("firstname :"+firstName+",lastname :"+lastName);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][3];
		objArr[0][0]="deepak";
		objArr[0][1]="hr";
		objArr[0][2]=32141524;
		
		objArr[1][0]="sai";
		objArr[1][1]="developer";
		objArr[1][2]=8764381;
		
		objArr[2][0]="jhon";
		objArr[2][1]="smith";
		objArr[2][2]=64532976;
		
		return objArr;
	}
}
