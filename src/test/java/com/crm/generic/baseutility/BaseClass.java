package com.crm.generic.baseutility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass 
{
	@BeforeSuite
	public void configBS()
	{
		System.out.println("====connect to DB,Report config====");
	}
	
	@BeforeClass
	public void configBC()
	{
		System.out.println("====Launch the BROWSER====");
	}
	
	@BeforeMethod
	public void configBM()
	{
		System.out.println("====login====");
	}
	
	@AfterMethod
	public void configAm()
	{
		System.out.println("====logout====");
	}
	
	@AfterClass
	public void configAC()
	{
		System.out.println("====Close the BROWSER====");
	}
	
	@AfterSuite
	public void configAS()
	{
		System.out.println("====Close to DB,Report backup====");
	}
}
