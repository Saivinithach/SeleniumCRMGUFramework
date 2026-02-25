package practice.hometest;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class HomePageSaampleTest 
{
	@Test
	public void homePageTest(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start");
		Reporter.log("step-1");
		Reporter.log("step-2");
		Reporter.log("step-3");
		Reporter.log("step-4");
		Reporter.log(mtd.getName()+" Test End");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start");
		Reporter.log("step-1");
		Reporter.log("step-2");
		Reporter.log("step-3");
		Reporter.log("step-4");
		Reporter.log(mtd.getName()+" Test End");
	}
}
