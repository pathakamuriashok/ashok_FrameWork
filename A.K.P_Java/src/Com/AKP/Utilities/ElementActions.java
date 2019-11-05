package Com.AKP.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions 
{
		public static void ApplicationLaunch(WebDriver driver,String Url)
		{
			try{
				driver.get(Url);	 				
			}catch(Exception e){
				driver.navigate().to(Url);  
			}

		}
     public static void EnterText(WebDriver driver,String locater,String value,String funcationality)
     {
    	 	WebElement block;
                  try{
                	  block=driver.findElement(By.xpath(locater));
                	  HighlightElement(driver,block);
                	  block.sendKeys(value);  
                  }catch(Exception e1){
                	  try{
                     	  block=driver.findElement(By.id(locater));
                     	  HighlightElement(driver,block);
                    	  block.sendKeys(value);  
                      }catch(Exception e2){
                    	  try{
                         	  block=driver.findElement(By.name(locater));
                         	  HighlightElement(driver,block);
                        	  block.sendKeys(value);  
                          }catch(Exception e3){
                        	  try{
                             	  block=driver.findElement(By.className(locater));
                             	  HighlightElement(driver,block);
                            	  block.sendKeys(value);  
                              }catch(Exception e4){
                            	  try{
                                 	  block=driver.findElement(By.linkText(locater));
                                 	  HighlightElement(driver,block);
                                	  block.sendKeys(value);  
                                  }catch(Exception e5){
                                	  block=driver.findElement(By.partialLinkText(locater));
                                	  HighlightElement(driver,block);
                                	  block.sendKeys(value);  
                                  }
                              }
                          }
                      }
                	String errorMsg=e1.getMessage();
                	System.out.println("Eerror Message From ' EnterText ' Method   ::::::------)) "+errorMsg);
                	e1.printStackTrace();
                  }
     }
     
     public static void HighlightElement(WebDriver driver,WebElement ele){
    	 
    	 JavascriptExecutor js = (JavascriptExecutor)driver;
    	 for(int i=0;i<30;i++){
    	 js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: red; border: 2px solid red;");
    	   js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "");
    	 }
     }
     public static void Click(WebDriver driver,String locater,String funcationality ){
    	 
    	 WebElement block;
    	 try {
    		 block=driver.findElement(By.id(locater));
    		  HighlightElement(driver,block);
    		 block.click();
		} catch (Exception e1) {
	 		try {
		 		 block=driver.findElement(By.xpath(locater));
		 		  HighlightElement(driver,block);
		 		 block.click();			
			} catch (Exception e2) {
				try {
			 		 block=driver.findElement(By.name(locater));
			 		  HighlightElement(driver,block);
			 		 block.click();
				} catch (Exception e3) {
					try {
				 		 block=driver.findElement(By.linkText(locater));
				 		  HighlightElement(driver,block);
				 		 block.click();
					} catch (Exception e4) {
						try {
					 		 block=driver.findElement(By.partialLinkText(locater));
					 		  HighlightElement(driver,block);
					 		 block.click();
						} catch (Exception e5) {
						 		 block=driver.findElement(By.className(locater));
						 		  HighlightElement(driver,block);
						 		 block.click();
							}
						}
				}
			}
			String errorMsg=e1.getMessage();
        	System.out.println("Eerror Message From ' Click ' Method   ::::::------)) "+errorMsg);
        	e1.printStackTrace();
		}
     }
}
