package Com.AKP.InitialSetUP;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Com.AKP.Utilities.Constants;

import com.beust.jcommander.Parameters;

public class Driver_Setup {
	
	public WebDriver driver;
	public String IEdriverPath = Constants.IE_driverPath;
    public String TC_ID=null;
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(String browserType, String appURL) {
		System.out.println("browser type --->"+browserType);  
		System.out.println(" URL --->"+appURL);  
		switch (browserType) {
		case "IE":
			driver = initIEDriver(appURL);
			break;
		case "Chrome":
			driver = initChromeDriver(appURL);
			break;
		case "FireFox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as default for execution...");
			driver = initFirefoxDriver(appURL);
		}
	}
	public WebDriver initIEDriver(String appURL) {
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		//Log.info(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		cap.setCapability("nativeEvents", false);    
		cap.setCapability("unexpectedAlertBehaviour", "accept");
		cap.setCapability("ignoreProtectedModeSettings", true);
		cap.setCapability("disable-popup-blocking", true);
		cap.setCapability("enablePersistentHover", true);
		cap.setCapability("ignoreZoomSetting", true);
		cap.setJavascriptEnabled(true);	
		driver = new InternetExplorerDriver(cap);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	private WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");		
		System.setProperty("webdriver.chrome.driver", Constants.Chrome_driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL,String tcID) {
		try {
			
			setDriver(browserType.toUpperCase(), appURL);
			TC_ID=tcID;

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	@AfterClass
	public void tearDown() {
			driver.quit();		
	}
}