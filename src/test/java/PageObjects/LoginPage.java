package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

WebDriver ldriver;
public LoginPage(WebDriver rdriver) {
	ldriver=rdriver;
	PageFactory.initElements(rdriver, this);
}

@FindBy(id="Email")
@CacheLookup
WebElement email;

@FindBy(id="Password")
@CacheLookup
WebElement Password;

@FindBy(xpath ="//button[@type='submit']")
@CacheLookup
WebElement LoginBtn;

@FindBy(xpath = "//a[contains(.,'Logout')]")
@CacheLookup
WebElement LogoutBtn;

//Actions
public void enterEmail(String emailAddress) {
	email.clear();
	email.sendKeys(emailAddress);
}
public void enterPassword(String pwd) {
	Password.clear();
	Password.sendKeys(pwd);
}
public void clickOnLoginBtn() {
	LoginBtn.click();
}
public void clickOnLogoutBtn() {
	LogoutBtn.click();
}	
}
