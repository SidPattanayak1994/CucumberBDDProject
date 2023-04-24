package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitHelper;

public class SearchCustomerPage {
	public WebDriver ldriver;
	WaitHelper waithelper;

	public SearchCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper = new WaitHelper(ldriver);
	}

	// PageObjects(locators)
	@FindBy(id = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(id = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;

	@FindBy(id = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(id = "search-customers")
	@CacheLookup
	WebElement searchBtn;

	@FindBy(id = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobMonth;

	@FindBy(id = "SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;

	@FindBy(id = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;

	@FindBy(xpath = "//div[contains(@class,'k-widget')]")
	@CacheLookup
	WebElement txtcustomerRoles;

	@FindBy(xpath = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;

	// Action Methods
	public void EnterEmailId(String email) {
		waithelper.WaitForElement(txtEmail, 20);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setFirstName(String fname) {
		waithelper.WaitForElement(txtFirstName, 20);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String Lname) {
		waithelper.WaitForElement(txtLastName, 20);
		txtLastName.clear();
		txtLastName.sendKeys(Lname);
	}

	public void ClickOnSearchButton() {
		searchBtn.click();
	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}

	public boolean SearchCustomerByEmail(String email) {
		boolean found = false;
		int RowsCount = getNoOfRows();
		// int ColumnCounts = getNoOfColumns();

		for (int i = 1; i <= RowsCount; i++) // To iterate all the rows excluding header
		{
			WebElement EmailElemnt = ldriver
					.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[2]"));
			String ActualEmailText = EmailElemnt.getText().trim();

			if (ActualEmailText.equals(email)) {
				found = true;
			}
		}
		return found;
	}

	public boolean SearchCustomerByName(String name) {
		
		String[] username = name.split(" ");
		boolean found = false;
		int RowsCount = getNoOfRows();
		for (int i = 1; i <= RowsCount; i++) // To iterate all the rows excluding header
		{
			WebElement Name = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i + "]/td[3]"));
			String[] ActualName = Name.getText().split(" ");//separating firstname and lastname

			if (ActualName[0].equals(username[0]) && ActualName[1].equals(username[1])) {
				found = true;
			}
		}
		return found;
	}

}
