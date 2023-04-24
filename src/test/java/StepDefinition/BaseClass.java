package StepDefinition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.plexus.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import PageObjects.AddNewCustomerPage;
import PageObjects.LoginPage;
import PageObjects.SearchCustomerPage;
import PageObjects.SearchVendorsPage;
import Utilities.EncodingAndDecodingStrings;
import Utilities.ReadConfig;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AddNewCustomerPage addcust;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	public ReadConfig readconfig;
	public SearchVendorsPage svp;
	public EncodingAndDecodingStrings ED;

	@Parameters("browser")
	@BeforeClass
	public void setUP(String br) {
		logger = Logger.getLogger("StepDef");
		PropertyConfigurator.configure("Log4j2.properties");
		switch (br.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
			logger.info("******Chrome driver started********");
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			throw new NullPointerException();
		}
	}

	/*
	 * @AfterClass public void teardown(Scenario sc) {
	 * System.out.println("Tear down method executed.."); if (sc.isFailed() == true)
	 * { String filePath =
	 * "C:\\Users\\91845\\Desktop\\EclipseWorkspace\\CucumberFramework\\Screenshot\\FailedSC.png";
	 * TakesScreenshot ts = ((TakesScreenshot) driver); File src =
	 * ts.getScreenshotAs(OutputType.FILE); File Dest = new File(filePath); try {
	 * FileUtils.copyFile(src, Dest); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } } driver.quit(); }
	 */

	// ***Created for generating random string for unique email ***//
	public static String GenerateRandomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

	public static String decodedString(byte[] password) {
		byte[] decodedString = Base64.encodeBase64(password);
		return (new String(decodedString));
	}
}
