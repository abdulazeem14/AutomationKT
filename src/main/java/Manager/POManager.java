package Manager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import common.Base;
import pageObjects.Cart;
import pageObjects.HomePage;
import pageObjects.PDP;

public class POManager extends Base{
	private WebDriver wd;
	private HomePage hp;
	private PDP pdp;
	private Cart cart;

	public POManager(WebDriver wd)
	{
		this.wd=wd;
	}
	
	public HomePage getHomePage()
	{
		return (hp == null) ? hp = new HomePage(wd) : hp;
	}
	public PDP getPDP()
	{
		return (pdp == null) ? pdp = new PDP(wd) : pdp;
	}
	public Cart getCart()
	{
		return (cart == null) ? cart = new Cart(wd) : cart;
	}
 
	public void navigatetoLogin()
	{
		hp.login().click();	
	}
public void login(String username,String pass) {
	
	hp.Lusername().sendKeys(username);
	hp.Lpassword().sendKeys(pass);
	
	hp.Llogin().click();
	
}
public void alertaccept(WebDriver wd)
{
	wd.switchTo().alert().accept();
}
public String alertGettext(WebDriver wd) {
	return wd.switchTo().alert().getText();
}
public void logout() {
	hp.logOut().click();
}
public String getUserName() {
	return hp.Welcomeuser().getText();
	
}
public void CreateTestCase(String TestID)
{
	test=extent.createTest(TestID);
}
public void performImplicitWait(WebDriver wd) {
	wd.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
	
}

public void performExplictwait(WebDriver WD,By xpath)
{
	WebDriverWait wait = new WebDriverWait(WD,1000);

	wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
}

	
public By getlogoutxpath() {
	return hp.logout;
}
///////////////////////Contact methods////////////
public void NavigatetoContact() {
	hp.contact().click();
}
public void EnterContactDetails(String ContactEmail,String ContactName,String Message)
{
	hp.cEmail().sendKeys(ContactEmail);
	
	hp.cContact().sendKeys(ContactName);
	
	hp.CMessage().sendKeys(Message);
	
}
public void SendMessage() {
	hp.cSendMessage().click();
	
}
///////////////////////////Category methods////////////////////////
public List<WebElement> getCatergory() {
	return hp.Category();
}

public List<WebElement> getPLP(){
	return hp.plp();
}

/////////////////SingnUp////////////////////

public void Navigatetosignup() {
	hp.signup().click();
}

public void SignupCredentials(String username,String password) {
	hp.Susername().sendKeys(username);
	hp.Spassword().sendKeys(password);
}
public void clickSingnup() {
	hp.Ssignup().click();
}


}
