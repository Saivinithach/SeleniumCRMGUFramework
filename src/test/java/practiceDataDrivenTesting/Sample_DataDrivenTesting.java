package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Sample_DataDrivenTesting 
{
	public static void main(String args[]) throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\CommonData.properties");
		
		Properties pro=new Properties();
		pro.load(fis);
		
		System.out.println(pro.getProperty("Browser"));
	}
}
