package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	WebDriver wd;
	String cat=null;
	int index;
	By home=By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a");
	By contact=By.xpath("//*[@id=\"navbarExample\"]/ul/li[2]/a");
	By aboutus=By.xpath("//*[@id=\"navbarExample\"]/ul/li[8]");
	By cart=By.xpath("//*[@id=\"cartur\"]");
	By login=By.xpath("//*[@id=\"login2\"]");
	public By logout=By.xpath("//*[@id='logout2']");
	By signup=By.xpath("//*[@id=\"signin2\"]");
	By Lusername=By.xpath("//*[@id=\"loginusername\"]");
	By Lpassword=By.xpath("//*[@id=\"loginpassword\"]");
	By Lclose=By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[1]");
	By xclose=By.xpath("//*[@id='logInModal']/div/div/div[1]/button/span");
	By Llogin=By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
	By Welcomeuser=By.xpath("//*[@id=\"nameofuser\"]");
	By Category=By.xpath("//*[@id='itemc']");
	By PLP=By.xpath("//*[@id='tbodyid']/child::*/child::div/div/h4/a");
	By Susername=By.xpath("//*[@id=\"sign-username\"]");
	By Spassword=By.xpath("//*[@id=\"sign-password\"]");
	By Sclose=By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[1]");
	By Ssignup=By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]");
	By CEmail=By.xpath("//*[@id='recipient-email']");
	By CContact=By.xpath("//*[@id='recipient-name']");
	By CMessage=By.xpath("//*[@id='message-text']");
	By CSM=By.xpath("//*[@id='exampleModal']/div/div/div[3]/button[2]");

	
	public HomePage(WebDriver wd) {
	
		this.wd = wd;
	}
	public WebElement home()
	{
 return wd.findElement(home);		
	}
	public WebElement contact()
	{
 return wd.findElement(contact);		
	}
	public WebElement aboutus()
	{
 return wd.findElement(aboutus);		
	}
	public WebElement cart()
	{
 return wd.findElement(cart);		
	}
	public WebElement login()
	{
 return wd.findElement(login);		
	}
	public WebElement signup()
	{
 return wd.findElement(signup);		
	}
	public WebElement Lusername()
	{
 return wd.findElement(Lusername);		
	}
	public WebElement Lpassword()
	{
 return wd.findElement(Lpassword);		
	}
	public WebElement Lclose()
	{
 return wd.findElement(Lclose);		
	}
	public WebElement Llogin()
	{
 return wd.findElement(Llogin);		
	}
	public WebElement Welcomeuser()
	{
 return wd.findElement(Welcomeuser);		
	}
	public List<WebElement> Category()
	{
 return wd.findElements(Category);		
	}
	
	public List<WebElement> plp()
	{
	
 return wd.findElements(PLP);		
	}
	
	public WebElement Susername()
	{
 return wd.findElement(Susername);		
	}
	public WebElement Spassword()
	{
 return wd.findElement(Spassword);		
	}
	public WebElement Sclose()
	{
 return wd.findElement(Sclose);		
	}
	public WebElement Ssignup()
	{
 return wd.findElement(Ssignup);		
	}
	
	public WebElement cEmail()
	{
 return wd.findElement(CEmail);		
	}
	public WebElement cContact()
	{
 return wd.findElement(CContact);		
	}
	public WebElement CMessage()
	{
 return wd.findElement(CMessage);		
	}
	public WebElement cSendMessage()
	{
 return wd.findElement(CSM);		
	}
	public WebElement logOut()
	{
 return wd.findElement(logout);		
	}
	public WebElement xclose()
	{
 return wd.findElement(xclose);		
	}
	
	
}
