package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class AddNewCustomerPage {
	public WebDriver ldriver;

	public AddNewCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}

	// pageObjects(locators)
	By lnkCutomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCutomers_menuItem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By btnAddnew = By.xpath("//a[@href='/Admin/Customer/Create']/i");
	By btnCustomerInfo = By.xpath("//div[@id='customer-info']//div/button/i");
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.id("Password");
	By txtCustomerRoles = By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");

	By lstitemAdministrator = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	By lstitemForummoderator = By.xpath("//li[contains(text(),'Forum Moderators')]");

	By drpmgrOfVendor = By.xpath("//select[@id='VendorId']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");

	By txtFirstName = By.id("FirstName");
	By txtTNI = By.xpath("//input[contains(@id,'customer_attribute')]");
	By txtLastName = By.xpath("//input[@name='LastName']");
	By txtDob = By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName = By.xpath("//input[@id='Company']");
	By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
	By btnSave = By.xpath("//button[@name='save']");

	// Action Methods
	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCutomers_menu).click();
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCutomers_menuItem).click();
	}

	public void clickOnAddnewButton() {
		ldriver.findElement(btnAddnew).click();
	}

	/*
	 * public void clickOnCustomerInfo() {
	 * ldriver.findElement(btnCustomerInfo).click(); }
	 */

	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}

	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}

	public void setCustomerRoles(String role) throws InterruptedException {
		WebElement listitem;

		WebElement elem = ldriver.findElement(By.xpath("//li[contains(@class,'k-button')]"));
		String CustomerRole_text = elem.getText();

		if (CustomerRole_text.equalsIgnoreCase(role)) {
			ldriver.findElement(txtCustomerRoles).click();
			listitem = ldriver.findElement(By.xpath("//li[contains(text(),'" + role + "')]"));
			listitem.click();
			Thread.sleep(3000);
			ldriver.findElement(txtCustomerRoles).click();
			Thread.sleep(3000);

			if (role.equalsIgnoreCase("Administrators")) {
				listitem = ldriver.findElement(lstitemAdministrator);
			} else if (role.equalsIgnoreCase("Guests")) {
				listitem = ldriver.findElement(lstitemGuests);
			} else if (role.equalsIgnoreCase("Registered")) {
				listitem = ldriver.findElement(lstitemRegistered);
			} else if (role.equalsIgnoreCase("Vendors")) {
				listitem = ldriver.findElement(lstitemVendors);
			} else {
				listitem = ldriver.findElement(lstitemForummoderator);
			}
			Thread.sleep(2000);
			listitem.click();
		} else {
			ldriver.findElement(txtCustomerRoles).click();
			Thread.sleep(3000);

			if (role.equalsIgnoreCase("Administrators")) {
				listitem = ldriver.findElement(lstitemAdministrator);
			} else if (role.equalsIgnoreCase("Guests")) {
				listitem = ldriver.findElement(lstitemGuests);
			} else if (role.equalsIgnoreCase("Registered")) {
				listitem = ldriver.findElement(lstitemRegistered);
			} else if (role.equalsIgnoreCase("Vendors")) {
				listitem = ldriver.findElement(lstitemVendors);
			} else {
				listitem = ldriver.findElement(lstitemForummoderator);
			}
			Thread.sleep(2000);
			listitem.click();
			/*
			 * JavascriptExecutor js = (JavascriptExecutor) ldriver;
			 * js.executeScript("arguements[0].click();", listitem);
			 */

		}

	}

	public void setManagerOfVendor(String value) throws InterruptedException {
		Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
		List<WebElement> options = drp.getOptions();
		for (WebElement option : options) {
			String valueTxt = option.getText();
			if (valueTxt.equalsIgnoreCase(value)) {
				option.click();
				break;
			}
		}

	}

	public void setGender(String gender) {
		if (gender.equals("Male")) {
			ldriver.findElement(rdMaleGender).click();
		} else if (gender.equals("Female")) {
			ldriver.findElement(rdFeMaleGender).click();
		} else {
			ldriver.findElement(rdMaleGender).click();
		}
	}

	public void setFirstName(String fname) {
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	public void setTNI(String tni) {
		ldriver.findElement(txtTNI).sendKeys(tni);
	}
	public void setLastName(String Lname) {
		ldriver.findElement(txtLastName).sendKeys(Lname);
	}

	public void setDOB(String dob) {
		ldriver.findElement(txtDob).sendKeys(dob);
	}

	public void setCompanyName(String CName) {
		ldriver.findElement(txtCompanyName).sendKeys(CName);
	}

	public void setAdminContent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}

	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}

}
