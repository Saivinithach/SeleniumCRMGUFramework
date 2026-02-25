package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleData_Based_ExpectedCondition {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		String expectedTestId="TC_02";
		String data1="";
		String data2="";
		String data3="";
		
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s=wb.getSheet("Sheet1");
		int rCount=s.getLastRowNum();
		
		for(int i=0;i<=rCount;i++)
		{
			String data="";
			try {
				 data=s.getRow(i).getCell(0).toString();
				 if(data.equals(expectedTestId))
				 {
					 data1=s.getRow(i).getCell(1).toString();
					 data2=s.getRow(i).getCell(2).toString();
					 data3=s.getRow(i).getCell(3).toString();
				 }
			}
			catch (Exception e) {}
			System.out.println(data1);
			System.out.println(data2);
			System.out.println(data3);
		}
		
	wb.close();
	}

}
