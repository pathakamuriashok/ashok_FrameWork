package Com.AKP.XLHandling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Com.AKP.Utilities.Constants;


public class ExcelHandling 
{
				 FileInputStream fin;
				 XSSFWorkbook workbook;
				 XSSFSheet sheet;
				 XSSFCell cell;
				 XSSFRow row;
				 FileOutputStream fout ;
				ArrayList<String> al1;
				ArrayList<String> al2;
				HashMap< Integer,ArrayList<String> > hm1;
				HashMap< Integer,ArrayList<String> > hm2;	
				
				
				public String GetData(String path,String  sheetName,String column_Name) throws IOException{
					String celldata = ""; 
					  fin=new FileInputStream(path);      
						 workbook=new XSSFWorkbook(fin);   
						 sheet=workbook.getSheet(sheetName);
						  int rows=sheet.getLastRowNum();  
						  for(int i=0;i<rows;i++){
							  int cells=sheet.getRow(i).getLastCellNum();
							  for(int j=0;j<cells;j++){
								String celldata_Headers= sheet.getRow(0).getCell(j).getStringCellValue(); 	
								System.out.println("AKSLJFDFFF "+celldata_Headers);
								System.out.println("column_Name::::::::::: "+column_Name);
								if(celldata_Headers.equalsIgnoreCase(column_Name)){
									 celldata= sheet.getRow(i+1).getCell(j).getStringCellValue(); 				
									 System.out.println("celldata :::::: "+celldata); 
									 break;
								}
							  }			  
						  }
						return celldata;
								  
								  
						  
				}
				
				public int GetLastRowNumber(String XlfileNameWithSheetName) throws IOException{
					 hm1=new HashMap< Integer,ArrayList<String> > ();
					 fin=new FileInputStream(XlfileNameWithSheetName);      
					 workbook=new XSSFWorkbook(fin);   
					  sheet=workbook.getSheet("MainMethod");
					  int rows=sheet.getLastRowNum();
					return rows;  
					}
				
				public int GetLastCellNumber(int rownumber,String XlfileNameWithSheetName) throws IOException{
					int rows=GetLastRowNumber(XlfileNameWithSheetName);  
				    	 int cells=sheet.getRow(rownumber).getLastCellNum();
						return cells;    
				}
				
				 public HashMap< Integer,ArrayList<String> > GetData() throws IOException{
					   hm1=new HashMap< Integer,ArrayList<String> > ();
					   int rows=GetLastRowNumber(Constants.MainMethodXLFile);  
					  for(int i=0;i<rows;i++){
						  int cells=sheet.getRow(i).getLastCellNum();  
						  al1=new ArrayList<String>();
						  for(int j=0;j<cells;j++){
							  cell =sheet.getRow(i).getCell(j);
							  String colum_val=cell.toString();
							  al1.add(colum_val);   
						  }
						 hm1.put(i, al1);
					  }
					return hm1;  
				 }
				
				 
				 public HashMap< Integer,ArrayList<String> > GetData1(String testcasePath) throws IOException{
					   hm2=new HashMap< Integer,ArrayList<String> > ();
					   int rows=GetLastRowNumber(Constants.TestcasesName);  
					  for(int i=0;i<rows;i++){
						  int cells=sheet.getRow(i).getLastCellNum();  
						  al2=new ArrayList<String>();
						  for(int j=0;j<cells;j++){
							  cell =sheet.getRow(i).getCell(j);
							  String colum_val=cell.toString();
							  al2.add(colum_val);   
						  }
						 hm2.put(i, al2);
					  }
					return hm2;  
				 }
				 
				 HashMap<String, String> testdata;
				 
				 public List<HashMap<String, String>> getExcelData() throws IOException {
					 fin=new FileInputStream(Constants.MainMethodXLFile);      
					 workbook=new XSSFWorkbook(fin);     
					  sheet=workbook.getSheet("MainMethod");
					  int rows=sheet.getLastRowNum();
						System.out.println(rows);
						List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>(rows);
						for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				             testdata = new HashMap<String, String>();
							for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++)
							{	
								try
								{
							     System.out.println("i:"+i+" "+"j:"+j);
						//	     System.out.println("<<@@>>  "+sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
								testdata.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
								}
								catch(Throwable e){
								}
							}	
							result.add(testdata);
						}
						return result;
					}
				 
				 /*public void a1() throws IOException{
					   hm1=new HashMap< Integer,ArrayList<String> > ();
					 fin=new FileInputStream(Constants.MainMethodXLFile);  
					 workbook=new XSSFWorkbook(fin);  
					  sheet=workbook.getSheet("MainMethod");
					  int rows=sheet.getLastRowNum();
					  for(int i=0;i<rows;i++){
						  int cells=sheet.getRow(i).getLastCellNum();  
						  al1=new ArrayList<String>();
						  for(int j=0;j<cells;j++){
							  cell =sheet.getRow(i).getCell(j);
							  String colum_val=cell.toString();
							  al1.add(colum_val);   
						  }
						 hm1.put(i, al1);
					  }
				 }*/
				 
				 public void a2(){
					 /*for(int i=0;i<hm1.size();i++){
						 System.out.println(":::: "+hm1.get(i));  result
					 }*/
					
					 /* 
					 for(int i=0;i<result.size();i++){
					 System.out.println(":::: "+hm1.get(i));  
				    }*/
					 
					 
					 
				
					 
				 }
					 public static void main(String[] args) throws IOException {
					 ExcelHandling xl=new ExcelHandling();
					 xl.getExcelData();
				    xl.GetData();
				     xl.a2();  
					 
				 }
}
