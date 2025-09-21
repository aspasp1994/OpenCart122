package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups = { "Regression", "Master", "DataDrivenTest", "sanity" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {

		logger = LogManager.getLogger(this.getClass());

		FileReader file = new FileReader("./src/test/resources/config.properties");
		prop = new Properties();
		prop.load(file);

		String execution_env=prop.getProperty("execution_env");
		
		if(execution_env.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities dc = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				dc.setPlatform(Platform.WINDOWS);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				dc.setPlatform(Platform.MAC);
			}
			
			else if(os.equalsIgnoreCase("linux"))
			{
				dc.setPlatform(Platform.LINUX);
			}
			
			else {
				System.out.println("No matching OS");
				return;
			}
			
			//browser
			switch(browser.toLowerCase())
			{
			case "chrome": {
				dc.setBrowserName("chrome");
				break;
			}
			
			case "edge": {
				dc.setBrowserName("MicrosoftEdge");
				break;
			}
			default : {
				System.out.println("No matching browser");
				return;
			}
			
			
			}		
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
		}
		
		if(execution_env.equalsIgnoreCase("local")) {
		switch (browser.toLowerCase()) {

		case "chrome": {
			driver = new ChromeDriver();
			break;

		}
		case "edge": {
			driver = new EdgeDriver();
			break;
		}

		case "firefox": {
			driver = new FirefoxDriver();
			break;
		}
		default: {
			System.out.println("invalid browser name..");
			return;
		}
		}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Regression", "Master", "DataDrivenTest", "sanity" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {

		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;

	}

	public String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}

	public String randomAlphaNumber() {

		String randomNumber = RandomStringUtils.randomNumeric(4);
		String randomString = RandomStringUtils.randomAlphabetic(5);

		return (randomNumber + "@" + randomString);
	}

	public String captureSceenshot(String tName) throws IOException {
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddhhmmss");
		String timeStamp = sm.format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tName + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
