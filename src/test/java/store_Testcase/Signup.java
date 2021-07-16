package store_Testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.POManager;
import resources.Base;

public class Signup extends Base{

	SoftAssert s_assert;
	WebDriver wd;
	@Test(dataProvider="validtestdata")
	public void signup(HashMap<String,String> td) throws IOException, InterruptedException {
		String username=td.get("Username");
		String password=td.get("Password");
		log=new ArrayList<String>();
		wd=initializeDriver("Chrome");
		s_assert=new SoftAssert();
		Navigatetosite(wd);
		POManager pom=new POManager(wd);
		//	wd.get(prop.getProperty("url"));
		//test=extent.createTest(td.get("Test Id"));
		log.add("STORE website loaded with the given URL");
		pom.CreateTestCase("test1");
		pom.performImplicitWait(wd);
		pom.getHomePage();
	//HomePage hp=new HomePage(wd);
		pom.Navigatetosignup();
		pom.SignupCredentials(username, password);
		pom.clickSingnup();
	
	if(!username.contains("@")) {
		s_assert.assertEquals(wd.switchTo().alert().getText(),"Invalid username.","Invalid email Error");
	}
	if(username.contains("@")&&password.length()<8)
	{
		s_assert.assertEquals(wd.switchTo().alert().getText(),"passsword is not strong.","password is not strong but new user creted");
	}
	if(username.contains("@")) {
	s_assert.assertEquals(pom.alertGettext(wd),"Sign up successful.");
	pom.alertaccept(wd);
	log.add("User was registered Successfully");
	}
	wd.close();
	s_assert.assertAll();
	}
	
	
	
		
	
	@DataProvider
	public Object[][] validtestdata() throws IOException
	{

		ArrayList<HashMap<String,String>>  td=tcdata("Signup");
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
