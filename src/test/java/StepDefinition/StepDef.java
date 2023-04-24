package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.plexus.util.Base64;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObjects.AddNewCustomerPage;
import PageObjects.LoginPage;
import PageObjects.SearchCustomerPage;
import PageObjects.SearchVendorsPage;
import Utilities.EncodingAndDecodingStrings;
import Utilities.ReadConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class StepDef extends BaseClass {

	@Before
	public void SetUp() throws IOException {

		logger = Logger.getLogger("StepDef");
		PropertyConfigurator.configure("Log4j2.properties"); // reading properties
		readconfig = new ReadConfig();
		String browser = readconfig.getBrowser();
		switch (browser.toLowerCase()) {
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
			break;
		}
	}

	@Given("User launch Chrome browser")
	public void user_launch_chrome_browser() {

		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {

		logger.info("******Launching URL********");
		readconfig = new ReadConfig();
		String Appurl = readconfig.getApplicationURL();
		if (Appurl.equals(url)) {
			driver.get(url);
		} else {
			throw new RuntimeException("Appurl not equal to url");
		}

	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {

		logger.info("******Providing Login credentials*******");

		lp.enterEmail(email);
		lp.enterPassword(password);
	}

	@When("click on Login button")
	public void click_on_login_button() {
		logger.info("******Login Started*******");
		lp.clickOnLoginBtn();

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedtitle) {

		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedtitle)) {
			logger.info("******Login passed*******");
			Assert.assertTrue(true);
		} else {
			logger.info("******Login failed*******");
			Assert.assertTrue(false);
		}

		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(expectedtitle, driver.getTitle());
		}
	}

	@When("click on logout link")
	public void click_on_logout_link() throws InterruptedException {
		logger.info("******Click on logout*******");
		Thread.sleep(3000);
		lp.clickOnLogoutBtn();
	}

	@Then("close browser")
	public void close_browser() throws InterruptedException {
		logger.info("******closing browser*******");
		Thread.sleep(3000);
		driver.close();

	}
	/////////// Add New Customer////////////////////////////////

	@Then("User can view dashboard")
	public void user_can_view_dashboard() {
		addcust = new AddNewCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());

	}

	@When("User clicks on Customers menu")
	public void user_clicks_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addcust.clickOnCustomersMenu();
		Thread.sleep(3000);
	}

	@When("click on customers menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addcust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		Thread.sleep(3000);
		addcust.clickOnAddnewButton();
		Thread.sleep(4000);
	}

	@Then("user can view Add new customer page")
	public void user_can_view_add_new_customer_page() {

		Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());
	}

	@When("user enter cutomer info")
	public void user_enter_cutomer_info() throws InterruptedException {
		logger.info("******Adding new customer details*******");
		String email = GenerateRandomString() + "@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("test123");
		addcust.setCustomerRoles("Registered");
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setFirstName("Sid");
		addcust.setLastName("Pattanayak");
		// addcust.setTNI("Hello");
		addcust.setDOB("8/08/1994");
		addcust.setCompanyName("MyCompany");
		addcust.setAdminContent("This is for Testing---");
	}

	@When("click on save button")
	public void click_on_save_button() throws InterruptedException {
		addcust.clickOnSave();
		Thread.sleep(3000);
	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msg));
	}

	/// Search customer by Email Id?////

	@When("Enter customer Email")
	public void enter_customer_email() {
		logger.info("******Searching a customer by mail id*******");
		searchCust = new SearchCustomerPage(driver);
		searchCust.EnterEmailId("victoria_victoria@nopCommerce.com");
	}

	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.ClickOnSearchButton();
		Thread.sleep(3000);
	}

	@Then("user should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status = searchCust.SearchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

	// Search Customer by name///

	@When("Enter cutomer firstName")
	public void enter_cutomer_first_name() {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter cutomer lastName")
	public void enter_cutomer_last_name() {
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in search table")
	public void user_should_found_name_in_search_table() {
		boolean status = searchCust.SearchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

	// Search Vendor by its name//

	@When("User opens URL")
	public void user_opens_url() {
		String Appurl = readconfig.getApplicationURL();
		driver.get(Appurl);
	}

	@When("User enters Credentials")
	public void user_enters_credentials() {
		String username = readconfig.getUserName();
		String password = readconfig.getPassword();
		// ED = new EncodingAndDecodingStrings();
		// String pwd=ED.decodePassword();
		lp.enterEmail(username);
		lp.enterPassword(password);
	}

	@When("click on Vendors menu Item")
	public void click_on_vendors_menu_item() throws InterruptedException {
		svp = new SearchVendorsPage(driver);
		svp.click_Vendors_Menu();
		Thread.sleep(5000);
	}

	@Then("user can view Add vendors page")
	public void user_can_view_add_vendors_page() {
		String title = svp.getPageTitle();
		Assert.assertEquals(title, "Vendors / nopCommerce administration");
	}

	@When("user enter Vendor name")
	public void user_enter_vendor_name(DataTable dataTable) throws InterruptedException {

		for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {
			svp.Enter_VendorsName(data.get("Vendor name"));
			Thread.sleep(4000);
			svp.Click_Search();
			Thread.sleep(4000);
			svp.Enter_VendorsName(data.get("Vendor name"));
			Thread.sleep(4000);
		}
	}

	@When("click on Vendors page search button")
	public void click_on_vendors_page_search_button() throws InterruptedException {
		svp.Click_Search();
		Thread.sleep(3000);
	}

	@Then("user should found Email in the search table in vendors page")
	public void user_should_found_email_in_the_search_table_in_vendors_page() {

		boolean status = svp.SearchVendorByName();
		Assert.assertEquals(status, true);
	}

	//@After 
	public void teardown(Scenario sc) {
		System.out.println("Tear down method executed..");
		if (sc.isFailed() == true) {
			String filePath = "C:\\Users\\91845\\Desktop\\EclipseWorkspace\\CucumberFramework\\Screenshot\\FailedSC.png";
			TakesScreenshot ts = ((TakesScreenshot) driver);
			File src = ts.getScreenshotAs(OutputType.FILE);
			File Dest = new File(filePath);
			try {
				FileUtils.copyFile(src, Dest);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		driver.quit();  
	 }
	@AfterStep
	public void addScreenshot(Scenario scenario) {
		if(scenario.isFailed()) {
		TakesScreenshot ts = ((TakesScreenshot) driver);
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
}
	 

	/*
	 * @BeforeStep //before each step of TC this method will execute public void
	 * beforeStepMethod() { System.out.println("This is after step..."); }
	 * 
	 * @AfterStep //after each step of TC this method will execute public void
	 * afterStepMethod() { System.out.println("This is after step..."); }
	 */


