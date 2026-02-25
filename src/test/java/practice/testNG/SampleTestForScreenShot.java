package practice.testNG;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class SampleTestForScreenShot 
{
	@Test
	public void amazonTest() throws IOException
	{
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get("https://demowebshop.tricentis.com/");
			
			
			//Type cast to get the capabilities of takescreenshot interface
			//to the driver instance
			
			
			//typecasting
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			//Temprory Location
			File srcfile=ts.getScreenshotAs(OutputType.FILE);
			
			//Destination File
			File destination=new File("./Screenshots/Homepage.png");
			
		
			FileHandler.copy(srcfile, destination);
			System.out.println("Home page Launched Successfully");
			
			
			//Screenshot for search page
			driver.findElement(By.id("small-searchterms")).sendKeys("mobile");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			//Take screenshot
			File src=ts.getScreenshotAs(OutputType.FILE);
			File dest=new File("./Screenshots/Searchpage.png");
			FileHandler.copy(src, dest);
			System.out.println("Home page Launched Successfully");
			
			driver.close();
	}
}
