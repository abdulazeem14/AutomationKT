package store_Testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Manager.POManager;
import common.Base;



public class Login extends Base{
		public static SoftAssert s_assert;
		WebDriver wd;
		@Test(dataProvider="validtestdata")
		public void loginCheckEdge(HashMap<String,String> testdata) throws IOException, InterruptedException {
			log=new ArrayList<String>();
			s_assert=new SoftAssert();
			wd=initializeDriver("Edge");
			String Username=testdata.get("UserName");
			String Password=testdata.get("Password");
			String Function=testdata.get("Function");
			String TestId=testdata.get("Test Id");
			String AlertMessage=testdata.get("AlertMessage");
			Navigatetosite(wd);
			log.add("Store Website is Loaded with given URL:"+prop.getProperty("url"));
			POManager pom=new POManager(wd);
			pom.getwaitOpertion().performImplicitWait(wd);
			pom.getReport().CreateTestCase(TestId);
			pom.getHomePage().navigatetoLogin();
			if(Function.equals("logout")) 
		{
				pom.getHomePage().login(Username, Password);
				log.add("Login is Success with valid credentials");
				pom.getwaitOpertion().performExplictwait(wd, pom.getHomePage().getlogoutxpath());
				pom.getHomePage().logout();
				log.add("User was able to logOut as Expected");
		}
		
	if(Function.equals(""))
		{
					pom.getHomePage().login(Username, Password);
					log.add("Username is entered:"+Username);
					log.add("Password is entered:"+Password);
		
			WebDriverWait w = new WebDriverWait(wd,5);
	try {
		
	if(w.until(ExpectedConditions.alertIsPresent())!=null)
		{
			s_assert.assertEquals(wd.switchTo().alert().getText(), AlertMessage);
			log.add(testdata.get("AlertMessage")+": Alert message is displayed as expected");
			pom.getalert().alertaccept(wd);
		}	
		}
	catch(Exception E)
		{
		s_assert.assertEquals(pom.getHomePage().getUserName(), "Welcome "+Username);
			log.add("Login success with valid credentials");
		}
		}
					wd.close();
					s_assert.assertAll();
		}
		
		@Test(dataProvider="validtestdata")
		public void loginCheckChrome(HashMap<String,String> testdata) throws IOException, InterruptedException {
			log=new ArrayList<String>();
			s_assert=new SoftAssert();
			wd=initializeDriver("Chrome");
			String Username=testdata.get("UserName");
			String Password=testdata.get("Password");
			String Function=testdata.get("Function");
			String TestId=testdata.get("Test Id");
			String AlertMessage=testdata.get("AlertMessage");
			Navigatetosite(wd);
			log.add("Store Website is Loaded with given URL:"+prop.getProperty("url"));
			POManager pom=new POManager(wd);
			pom.getwaitOpertion().performImplicitWait(wd);
			pom.getReport().CreateTestCase(TestId);
			pom.getHomePage().navigatetoLogin();
			if(Function.equals("logout")) 
		{
				pom.getHomePage().login(Username, Password);
				log.add("Login is Success with valid credentials");
				pom.getwaitOpertion().performExplictwait(wd, pom.getHomePage().getlogoutxpath());
				pom.getHomePage().logout();
				log.add("User was able to logOut as Expected");
		}
		
	if(Function.equals(""))
		{
					pom.getHomePage().login(Username, Password);
					log.add("Username is entered:"+Username);
					log.add("Password is entered:"+Password);
		
			WebDriverWait w = new WebDriverWait(wd,5);
	try {
		
	if(w.until(ExpectedConditions.alertIsPresent())!=null)
		{
			s_assert.assertEquals(wd.switchTo().alert().getText(), AlertMessage);
			log.add(testdata.get("AlertMessage")+": Alert message is displayed as expected");
			pom.getalert().alertaccept(wd);
		}	
		}
	catch(Exception E)
		{
		s_assert.assertEquals(pom.getHomePage().getUserName(), "Welcome "+Username);
			log.add("Login success with valid credentials");
		}
		}
					wd.close();
					s_assert.assertAll();
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

