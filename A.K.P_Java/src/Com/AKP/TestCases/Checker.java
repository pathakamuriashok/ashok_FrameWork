package Com.AKP.TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Com.AKP.Utilities.Constants;

public class Checker {

	public void XL_Read() throws IOException{
		FileInputStream fin=new FileInputStream("D:\\HomeDirectory\\A.K.P\\src\\Com\\AKP\\TestCases\\TC_01.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fin);  
		XSSFSheet ws=wb.getSheet("Sheet2");
		int rows=ws.getLastRowNum();
		Row r;
		Cell c;
		System.out.println("========> "+rows);
		for(int i=0;i<rows;i++){
		  c=ws.getRow(i).getCell(0);
		  String s=c.getStringCellValue();
		  System.out.println("===========================>>>>>>> "+s);  
		}
		
	}
	

	public void GetTcis(){
	//	File f=new File("D:\\HomeDirectory\\A.K.P\\src\\Com\\AKP\\TestCases\\");  
		File f=new File(Constants.TestcasesName);   
		File[] files=f.listFiles();  
		for(int i=0;i<files.length;i++){
			System.out.println("---------------------------------- "+files[i]);   
		}
		 
	}
	public static void main(String[] args) throws IOException {
		Checker c=new Checker();
		String directory=System.getProperty("user.dir");    
		System.out.println("lkjfsdddddddd "+directory);
	//	c.XL_Read();
		c.GetTcis();
	}

}
