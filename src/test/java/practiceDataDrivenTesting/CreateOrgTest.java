package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest { 

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		
		//read common data from property file
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\CommonData.properties");
		
		Properties pro=new Properties();
		pro.load(fis);
		
		String browser=pro.getProperty("Browser");
		String url=pro.getProperty("Url");
		String userName=pro.getProperty("UserName");
		String password=pro.getProperty("Password");
		
		
		//generate the random number
		Random r=new Random();
		int rNum=r.nextInt(1000);
		
		//reda test script data from xcel file
		FileInputStream fis1=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet s=wb.getSheet("Sheet1");
		Row row=s.getRow(1);
		String orgName=row.getCell(2).toString()+rNum;
		
		
		WebDriver driver=null;
		
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		
		//Step1:Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//step2:navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		Thread.sleep(2000);
		
		//step3:click on "crete organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//step4:enter the organization name and click on save
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("guntur");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step5:logout from the application
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@style='padding: 0px;padding-left:5px']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
