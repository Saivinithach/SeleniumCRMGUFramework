package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadData_BackTo_Excel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet s=wb.getSheet("Sheet1");
		Row r=s.getRow(1);
		Cell c=r.createCell(4);
		c.setCellType(CellType.STRING);
		c.setCellValue("Pass");
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\hp\\Desktop\\TutorialRecording\\SeleniumCRMGUFramework\\TestScriptData.xlsx");
		wb.write(fos);
		
		wb.close();
		System.out.println("executed");
		
	}

}
