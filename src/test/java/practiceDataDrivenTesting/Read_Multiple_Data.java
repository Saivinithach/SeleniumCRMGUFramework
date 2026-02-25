package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Read_Multiple_Data {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s=wb.getSheet("Sheet2");
		int rCount=s.getLastRowNum();
		
		for(int i=1;i<rCount;i++)
		{
			Row r=s.getRow(i);
			String c1Data=r.getCell(0).toString(); 
			String c2Data=r.getCell(1).toString();
			  System.out.println(c1Data +"\t"+c2Data); 
			  
		}
		
	wb.close();
	}

}
