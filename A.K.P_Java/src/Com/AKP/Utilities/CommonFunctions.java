package Com.AKP.Utilities;

import java.io.File;
import java.nio.file.Files;

public class CommonFunctions 
{
		public static File[] GetTestCases(){
		File f=new File(Constants.TestcasesName);   
		File[] files=f.listFiles();
		return files;      
	
		}
		 
}
