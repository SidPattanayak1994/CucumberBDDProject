package PageObjects;

import java.util.List;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitHelper;

public class SearchVendorsPage {
	WebDriver ldriver;
	WaitHelper waithelper;

	public SearchVendorsPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper = new WaitHelper(ldriver);
	}

	By Vendors_menu = By.xpath("//p[contains(.,'Vendors')]");
	By Vendor_name = By.id("SearchName");
	By Search_btn = By.id("search-vendors");
	
	By table_rows = By.xpath("//table[@id='vendors-grid']/tbody//tr");
	By table_Cols = By.xpath("//table[@id='vendors-grid']/tbody//tr/td");

	// Action Methods
	public String getPageTitle() {
		return ldriver.getTitle();
	}

	public void click_Vendors_Menu() {
		ldriver.findElement(Vendors_menu).click();;
	}

	public void Enter_VendorsName(String vendor) {
		ldriver.findElement(Vendor_name).clear();
		ldriver.findElement(Vendor_name).sendKeys(vendor);
	}

	public void Click_Search() {
		//waithelper.WaitForElement(Search_btn, 20);
		ldriver.findElement(Search_btn).click();
		
	}

	public boolean SearchVendorByName() {
        String EmailList = "vendor1email@gmail.com , vendor2email@gmail.com";
        String[] EmailTxt = EmailList.split(",");
		boolean flag = false;
		int TotalRows = ldriver.findElements(table_rows).size();
		List<WebElement> TotalColumns = ldriver.findElements(table_Cols);
		int Col_Count= TotalColumns.size();
		for(int i=1;i<=TotalRows;i++) {
			WebElement email = ldriver.findElement(By.xpath("//table[@id='vendors-grid']/tbody//tr["+i+"]/td[2]"));
			String emailTxt= email.getText().trim();
			if(emailTxt.contains(EmailTxt[0].trim()) || emailTxt.contains(EmailTxt[1].trim())) {
				flag = true;
			}
		}
		
		return flag;
	}

}
