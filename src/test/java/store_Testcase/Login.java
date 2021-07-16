
	package store_Testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.POManager;
import resources.Base;


public class Login extends Base{
		public static SoftAssert s_assert;
		WebDriver wd;
		@Test(dataProvider="validtestdata")
		public void loginCheckEdge(HashMap<String,String> td) throws IOException, InterruptedException {
			log=new ArrayList<String>();
			s_assert=new SoftAssert();
			wd=initializeDriver("Edge");
			String Username=td.get("UserName");
			String Password=td.get("Password");
			String Function=td.get("Function");
			String TestId=td.get("Test Id");
			String AlertMessage=td.get("AlertMessage");
				Navigatetosite(wd);
					log.add("Store Website is Loaded with given URL:"+prop.getProperty("url"));
			POManager pom=new POManager(wd);
				pom.performImplicitWait(wd);
				pom.CreateTestCase(TestId);
				pom.getHomePage();
				pom.navigatetoLogin();
	if(Function.equals("logout")) 
		{
				pom.login(Username, Password);
					log.add("Login is Success with valid credentials");
				pom.performExplictwait(wd, pom.getlogoutxpath());
				pom.logout();
					log.add("User was able to logOut as Expected");
		}
		
	if(Function.equals(""))
		{
			    pom.login(Username, Password);
					log.add("Username is entered:"+Username);
					log.add("Password is entered:"+Password);
		
			WebDriverWait w = new WebDriverWait(wd,5);
	try {
		
	if(w.until(ExpectedConditions.alertIsPresent())!=null)
		{
			s_assert.assertEquals(wd.switchTo().alert().getText(), AlertMessage);
			log.add(td.get("AlertMessage")+": Alert message is displayed as expected");
			pom.alertaccept(wd);
		}	
		}
	catch(Exception E)
		{
		s_assert.assertEquals(pom.getUserName(), "Welcome "+Username);
			log.add("Login success with valid credentials");
		}
		}
					wd.close();
					s_assert.assertAll();
		}
		
		
		@Test(dataProvider="validtestdata")
		public void loginCheckChrome(HashMap<String,String> td) throws IOException, InterruptedException {
			log=new ArrayList<String>();
			s_assert=new SoftAssert();
			wd=initializeDriver("Chrome");
			String Username=td.get("UserName");
			String Password=td.get("Password");
			String Function=td.get("Function");
			String TestId=td.get("Test Id");
			String AlertMessage=td.get("AlertMessage");
				Navigatetosite(wd);
					log.add("Store Website is Loaded with given URL:"+prop.getProperty("url"));
			POManager pom=new POManager(wd);
				pom.performImplicitWait(wd);
				pom.CreateTestCase(TestId);
				pom.getHomePage();
				pom.navigatetoLogin();
	if(Function.equals("logout")) 
		{
				pom.login(Username, Password);
					log.add("Login is Success with valid credentials");
				pom.performExplictwait(wd, pom.getlogoutxpath());
				pom.logout();
					log.add("User was able to logOut as Expected");
		}
		
	if(Function.equals(""))
		{
			    pom.login(Username, Password);
					log.add("Username is entered:"+Username);
					log.add("Password is entered:"+Password);
		
			WebDriverWait w = new WebDriverWait(wd,5);
	try {
		
	if(w.until(ExpectedConditions.alertIsPresent())!=null)
		{
			s_assert.assertEquals(wd.switchTo().alert().getText(), AlertMessage);
			log.add(td.get("AlertMessage")+": Alert message is displayed as expected");
			pom.alertaccept(wd);
		}	
		}
	catch(Exception E)
		{
		s_assert.assertEquals(pom.getUserName(), "Welcome "+Username);
			log.add("Login success with valid credentials");
		}
		}
					wd.close();
					s_assert.assertAll();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
//		@Test(dataProvider="validtestdata")
//		public void loginCheckChrome(HashMap<String,String> td) throws IOException, InterruptedException {
//			log=new ArrayList<String>();
//		 wd=initializeDriver("Chrome");
//			s_assert=new SoftAssert();
//			wd.get(prop.getProperty("url"));
//			log.add("Store Website is Loaded with given URL:"+prop.getProperty("url"));
//			wd.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
//			test=extent.createTest(td.get("Test Id"));
//		HomePage hp=new HomePage(wd);
//		hp.login().click();
//		if(td.get("Function").equals("logout")) {hp.Lusername().sendKeys(td.get("UserName"));
//		hp.Lpassword().sendKeys(td.get("Password"));
//		hp.Llogin().click();
//		log.add("Login is Success with valid credentials");
//		WebDriverWait wait = new WebDriverWait(wd,1000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='logout2']")));
//		hp.logOut().click();
//		log.add("User was able to logOut as Expected");
//		}
//		if(td.get("Function").equals("closeButton")) {hp.Lclose().click();
//		log.add("Close Button in Login Window is working as Expected");}
//		if(td.get("Function").equals("xclose")) {
//			WebDriverWait wait = new WebDriverWait(wd,1000);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='logInModal']/div/div/div[1]/button/span")));
//		hp.xclose().click();
//		log.add("Close Icon in Login Window as working as Expected");}
//		if(td.get("Function").equals(""))
//		{
//		hp.Lusername().sendKeys(td.get("UserName"));
//		log.add("Username is entered:"+td.get("UserName"));
//		hp.Lpassword().sendKeys(td.get("Password"));
//		log.add("Password is entered:"+td.get("Password"));
//		hp.Llogin().click();
//		 WebDriverWait w = new WebDriverWait(wd,5);
//		try {
//		if(w.until(ExpectedConditions.alertIsPresent())!=null)
//		{
//			s_assert.assertEquals(wd.switchTo().alert().getText(), td.get("AlertMessage"));
//			log.add(td.get("AlertMessage")+": Alert message is displayed as expected");
//			wd.switchTo().alert().accept();
//		}
//		
//		}
//		catch(Exception E)
//		{
//			s_assert.assertEquals(hp.Welcomeuser().getText(), "Welcome "+td.get("UserName"));
//			log.add("Login success with valid credentials");
//		}
//	
//		System.out.println(hp.Welcomeuser().getText());
//		}
//		
//	s_assert.assertAll();
//	
//	
//		
//		}
//		
		
		@AfterTest
		public void closebrowser() {
			wd.quit();
		}
		
		
		
		
		@DataProvider
		public Object[][] validtestdata() throws IOException
		{

			ArrayList<HashMap<String,String>>  td=tcdata("Login");
			Iterator<HashMap<String, String>> itr=td.iterator();
			Object[][] obj=new Object[td.size()][1];
			int i=0;
			while(itr.hasNext())
			{
			
				HashMap<String, String> a=itr.next();
				
				
			obj[i++][0]=a;
			
			
			}
			    return obj;
		
		}
		
		
}

