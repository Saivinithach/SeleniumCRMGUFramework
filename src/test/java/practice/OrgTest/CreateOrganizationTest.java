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
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationTest {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
//		Reading data from property file
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String Browser=prop.getProperty("Browser");
		String Url=prop.getProperty("URL");
		String UserName=prop.getProperty("UserName");
		String password=prop.getProperty("Password");
		
//		CREATE OBJECT TO RANDOM CLASS
		Random random=new Random();
//		SET UPPERLIMIT
		int randomInt=random.nextInt(1000);
		
		FileInputStream fis1=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sheet=wb.getSheet("Sheet1");
		Row row=sheet.getRow(1);
		String orgnames=row.getCell(2).toString() + randomInt;
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
//LOGIN TO APPLICATION
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
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		
//		VERIFY THAT HEADER MSG EXCEPTED RESULT
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgnames)) {
			System.out.println(orgnames +" is created === PASS");
		}else {
			System.out.println(orgnames + "is not created === FAIL");
		}
		
//		VERIFY HEADER ORGNAME INFO EXCEPTED RESULT
		String actOrgname=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgname.contains(orgnames)) {
			System.out.println(orgnames +" is created === PASS");
		}else {
			System.out.println(orgnames + "is not created === FAIL");
		}
		
//		LOGOUT
		Actions action=new Actions(driver);
		WebElement element=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		action.moveToElement(element).click().perform();
		WebElement elements=driver.findElement(By.linkText("Sign Out"));
		action.click(elements).perform();
		
		driver.quit();
	}

}
