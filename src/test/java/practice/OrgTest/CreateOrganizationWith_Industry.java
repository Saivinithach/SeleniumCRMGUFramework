package practice.OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWith_Industry {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String Browser=prop.getProperty("Browser");
		String Url=prop.getProperty("Url");
		String UserName=prop.getProperty("UserName");
		String password=prop.getProperty("Password");
		
//		CREATE OBJECT TO RANDOM CLASS
		Random random=new Random();
//		SET UPPERLIMIT
		int randomInt=random.nextInt(1000);
		
		FileInputStream fis1=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sheet=wb.getSheet("Sheet1");
		Row row=sheet.getRow(4);
		String orgnames=row.getCell(2).toString() + randomInt;
		String industry=row.getCell(3).toString(); 
		String type=row.getCell(4).toString();
		wb.close();
		
		WebDriver driver=null;
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(Browser.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		
//		LOGIN TO APPLICATION
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(UserName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
//		NAVIGATE TO ORGANIZATION MODULE
		driver.findElement(By.linkText("Organizations")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgnames);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("hyderabad");
		WebElement ind = driver.findElement(By.name("industry"));
		Select sel=new Select(ind);
		sel.selectByVisibleText(industry);
		
		WebElement ty = driver.findElement(By.name("accounttype"));
		Select sel1=new Select(ty);
		sel1.selectByVisibleText(type);		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		
//		VERIFY THE INDUSTRY AND TYPE INFO
		String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.contains(industry)) {
			System.out.println(industry +" is created === PASS");
		}else {
			System.out.println(industry + "is not created === FAIL");
		}
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.contains(type)) {
			System.out.println(type +" is created === PASS");
		}else {
			System.out.println(type + "is not created === FAIL");
		}
		
//		LOGOUT
		driver.quit();
	}

}
