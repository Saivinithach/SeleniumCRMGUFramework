package practice.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class Create_Contact_WithOrg {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		Row row=sheet.getRow(7);
		String orgnames=row.getCell(2).toString() + randomInt;
		String contactLastName=row.getCell(3).getStringCellValue();
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
		
		
//		NAVIGATE TO ORGANIZATION MODULE
	driver.findElement(By.linkText("Contacts")).click();
		
//		CLICK ON CREATE ORGANIZATION BUTTON
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		ENTER ALL ORGANIZATION DETAILS AND CREATE NEW ORGANIZATION
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		
//		SWITCH TO CHILD WINDOW
		Set<String> cw = driver.getWindowHandles();
		Iterator<String> it = cw.iterator();
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actualurl = driver.getCurrentUrl();
			if(actualurl.contains("module=Accounts")) {
				break;
			}
		}
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgnames);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='"+orgnames+"']")).click();
		
//		SWITCH TO PARENT WINDOW
		Set<String> pw = driver.getWindowHandles();
		Iterator<String> it1 = cw.iterator();
		while(it1.hasNext()) {
			String windowID = it1.next();
			driver.switchTo().window(windowID);
			String actualurl = driver.getCurrentUrl();
			if(actualurl.contains("Contacts&action")) {
				break;
			}
		}
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
//		VERIFY HEADER PHONENUMBER INFO EXCEPTED RESULT
//		String actphone=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(actphone.trim().equals(orgnames)) {
//			System.out.println(orgnames +" is created === PASS");
//		}else {
//			System.out.println(orgnames + "is not created === FAIL");
//		}
		
//		VERIFY HEADER ORGANIZATION INFO EXCEPTED RESULT
		String actlastname=driver.findElement(By.id("mouseArea_Organization Name")).getText();
//		String actlastname=driver.findElement(By.xpath("//input[@name='lastname']")).getText();
		System.out.println(actlastname);
//		System.out.println(contactLastName);
		if(actlastname.contains(contactLastName)) {
			System.out.println(contactLastName +" is created === PASS");
		}else {
			System.out.println(contactLastName + "is not created === FAIL");
		}
		
		
//		LOGOUT
		driver.quit();
		
	}

}