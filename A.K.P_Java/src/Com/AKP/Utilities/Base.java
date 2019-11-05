package Com.AKP.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Com.AKP.InitialSetUP.Driver_Setup;
import Com.AKP.XLHandling.ExcelHandling;

public class Base extends Driver_Setup
{
	private String xmlLocatorsPath = Constants.xmlLocatorsPath;
	private String TE_ID = Constants.TestcasesName;;
	
	 FileInputStream fin;
	 XSSFWorkbook workbook;
	 XSSFSheet sheet_testcase;
	 XSSFSheet sheet_data;
	 XSSFCell cell;
	 XSSFRow rows;
	 FileOutputStream fout ;

	
	ArrayList<String> testcases;
	ArrayList<String> Rows;
	ArrayList<String> Cells;
	ArrayList<Integer> MainMethodData_key;  
	ArrayList<String> MainMethodData_value;
	HashMap< Integer,ArrayList<String> > MainMethod;
	HashMap< Integer,ArrayList<String> > MainMethod1;
	File[] TC_ID=CommonFunctions.GetTestCases(); 
	
	public void Testcases(){
		testcases=new ArrayList<String>();
		for(int i=0;i<TC_ID.length;i++){
			File f=TC_ID[i];
	       String fileName=f.toString();
	       String s[]=fileName.split("TestCases");
	       String s1[]=s[1].split(".xlsx"); 
	       testcases.add(s1[0]);  
		}
	}
	ExcelHandling xl=new ExcelHandling();
	public void ActionItems() throws IOException{
		MainMethod=xl.GetData();    
		for (Map.Entry<Integer, ArrayList<String>> entry1 : MainMethod.entrySet()) {
            List<String> values = entry1.getValue();
            if(values.get(1).equalsIgnoreCase("Y"))
            {
            	   String browser= values.get(2);
            	    String textcase= values.get(0);
                    String testcasePath=Constants.TestcasesName+textcase; 
                 
                 fin=new FileInputStream(testcasePath);      
				 workbook=new XSSFWorkbook(fin);   
				  sheet_testcase=workbook.getSheet(Constants.XLSheetName_TestCases);
				  sheet_data=workbook.getSheet(Constants.XLSheetName_TestData);
				  int rows=sheet_testcase.getLastRowNum();
				  int rows_sheet_data=sheet_data.getLastRowNum();
				  Rows=new  ArrayList<String> ();
				 Cells =new ArrayList<String> ();
                    for(int r=0;r<rows;r++){
                        int NumberOfcells=sheet_testcase.getRow(r).getLastCellNum();
                        for(int j=0;j<NumberOfcells;j++){
                        	String ActionKeywords=sheet_testcase.getRow(0).getCell(j).getStringCellValue();
                        	String PageObject=sheet_testcase.getRow(0).getCell(j).getStringCellValue();
                        	String DataSheetColumnName=sheet_testcase.getRow(0).getCell(j).getStringCellValue();
                          
                        	if(ActionKeywords.equals("Action"))
                        	{
                        	 	String actionKeyword=sheet_testcase.getRow(r+1).getCell(j).getStringCellValue();
                        	 	String ExpectedResult=sheet_testcase.getRow(r+1).getCell(4).getStringCellValue();
                        	 	System.out.println("ExpectedResultExpectedResultExpectedResultExpectedResult"+ExpectedResult);
                        	 	String PageObjects=sheet_testcase.getRow(r+1).getCell(5).getStringCellValue();
                        	 	String ColumnHeader=sheet_testcase.getRow(r+1).getCell(6).getStringCellValue();
                        	 	String data=xl.GetData(testcasePath, Constants.XLSheetName_TestData, ColumnHeader);
                        switch(actionKeyword)	 
                        {
                            case "ApplicationLaunch":
                            	setDriver(browser, data);  
                                break; 
                                
                            case "EnterText":
                            	ElementActions.EnterText(driver, GetProperties_WebElement.GetXMLTagValue(xmlLocatorsPath, PageObjects), data, ExpectedResult); 
                                break; 
                                
                            case "Click":
                            	ElementActions.Click(driver, GetProperties_WebElement.GetXMLTagValue(xmlLocatorsPath, PageObjects), ExpectedResult);
                                break; 
                                
                        }
                        
                        	}
                        }
                          
                    }
                    }  
        }
        }
	public static void main(String[] args) throws IOException {
		Base b=new Base();	
		b.ActionItems();
	}
}

/*

package Com.AKP.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Com.AKP.InitialSetUP.Driver_Setup;
import Com.AKP.XLHandling.ExcelHandling;

public class Base extends Driver_Setup
{
	private String xmlLocatorsPath = Constants.xmlLocatorsPath;
	private String TE_ID = Constants.TestcasesName;;
	
	ArrayList<String> testcases;
	ArrayList<Integer> MainMethodData_key;  
	ArrayList<String> MainMethodData_value;
	HashMap< Integer,ArrayList<String> > MainMethod;
	HashMap< Integer,ArrayList<String> > MainMethod1;
	File[] TC_ID=CommonFunctions.GetTestCases(); 
	
	public void Testcases(){
		testcases=new ArrayList<String>();
		for(int i=0;i<TC_ID.length;i++){
			File f=TC_ID[i];
	       String fileName=f.toString();
	       String s[]=fileName.split("TestCases");
	       String s1[]=s[1].split(".xlsx"); 
	       testcases.add(s1[0]);  
		}
	}
	ExcelHandling xl=new ExcelHandling();
	public void ActionItems(String action) throws IOException{
		Testcases();
		MainMethod=xl.GetData();    
		//HashMap.Entry<Integer,ArrayList<String>> entry = MainMethod.entrySet().iterator().next();  
		for (Map.Entry<Integer, ArrayList<String>> entry1 : MainMethod.entrySet()) {
            Integer key = entry1.getKey();  
            List<String> values = entry1.getValue();
            System.out.println("Key = " + key);
            System.out.println("Values = " + values ); 
            if(values.get(1).equalsIgnoreCase("Y"))
            {
                    String textcase= values.get(0);
                    String testcasePath=Constants.TestcasesName+textcase; 
                    System.out.println("asdfjkllsdf "+testcasePath);  
                    String browser= values.get(2);
                    String type= values.get(3); 
                    for(int i=0;i<testcases.size();i++){
                       	System.out.println("++++++++++++++++  "+testcases.get(i));  
                       	String s1=testcases.get(i);
                       	String s2=s1.replaceAll("\\\\", "");  
                    	if(textcase.equals(s2)){    
                    		MainMethod1=xl.GetData1(Constants.TestcasesName+textcase+".xlsx");   
                    		
                    		for (Map.Entry<Integer, ArrayList<String>> entry2 : MainMethod1.entrySet()) {
                    	            Integer key1 = entry2.getKey();  
                    	            List<String> values1 = entry2.getValue();
                    	            System.out.println("Key :: = " + key1);
                    	            System.out.println("Values:: = " + values1 ); 
                    		   }
                    	}
   
                    }
                    
                    }  
            }
        }
    

		
								
					
		switch (action) {   
		case "EnterText":
			ElementActions.EnterText(driver, GetProperties_WebElement.GetXMLTagValue(xmlLocatorsPath, tagval will come from xl file), value, funcationality);
			
			break;

		default:
			break;
		}
	
	
	public static void main(String[] args) throws IOException {
		Base b=new Base();	
		//b.Testcases();
		b.ActionItems("");
	}
}

*/

