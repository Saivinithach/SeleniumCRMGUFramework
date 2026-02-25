package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadTestDataFrom_Excel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 	{
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet s=wb.getSheet("Sheet1");
		
		Row row=s.getRow(1);
		
		Cell c=row.getCell(3);
		double data=c.getNumericCellValue();
		System.out.println(data);
		
		wb.close();
	}

}