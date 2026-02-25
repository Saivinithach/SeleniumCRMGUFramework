package practice.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws IOException 
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
		Sheet sheet=wb.getSheet("Sheet3");
		Row row=sheet.getRow(4);
		String lastname=row.getCell(2).toString() + randomInt;
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
		driver.findElement(By.linkText("Contacts")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		
		Date dateObj=new Date();
		SimpleDateFormat sif=new SimpleDateFormat("yyyy-MM-dd");
		String startDate=sif.format(dateObj);
		
		Calendar cal=sif.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate=sif.format(cal.getTime());		
		
		
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY AND VALIDATE
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.contains(startDate)) {
			System.out.println(startDate +" information is verified === PASS");
		}else {
			System.out.println(startDate + "information is not verified === FAIL");
		}
		
//		Verify Date
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.contains(endDate)) {
			System.out.println(endDate +" information is verified === PASS");
		}else {
			System.out.println(endDate + "information is not verified === FAIL");
		}
		
		
//		LOGOUT
		driver.quit();
	}

}
