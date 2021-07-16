package store_Testcase;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Manager.POManager;
import common.Base;
import pageObjects.HomePage;
import pageObjects.PDP;


public class CategoryTC extends Base{
	SoftAssert s_assert;
	WebDriver wd;
	
@Test(dataProvider="category")
	public void categoryInChrome(HashMap<String,String> td) throws IOException, InterruptedException {
	log=new ArrayList<String>();
	String Category=td.get("Category");
		boolean flag=false;
		int i=1;
		s_assert=new SoftAssert();
		wd=initializeDriver("Chrome");
		POManager pom=new POManager(wd);
		Navigatetosite(wd);
		log.add("Strore Website is loaded successfully using the given Url: "+prop.getProperty("url"));
		pom.performImplicitWait(wd);
		pom.CreateTestCase("test123");
		pom.getHomePage();
	for(WebElement catergory:pom.getCatergory())
	{
		if(catergory.getText().equals(Category)) {
			catergory.click();
			log.add(Category+" Category is selected");
			for(WebElement plppage:pom.getPLP())
			{
				if(plppage.getText().equals(td.get("Product"+i+" Name")))
				{
					flag=true;
					plppage.click();
					log.add(td.get("Product"+i+" Name")+" is selected from PLP Page"); 
					PDP pdp=new PDP(wd);
					s_assert.assertEquals(td.get("Product"+i+" Name"),pdp.pdpName().getText());
					log.add(td.get("Product"+i+" Name")+" Selected in PLP is Verified with ProductName in PDP"+pdp.pdpName().getText()); 
					String arr[]=pdp.pdpPrice().getText().split(" ");
					float pri=Float.parseFloat(td.get("Product"+i+" Price"));
					NumberFormat nf = NumberFormat.getNumberInstance();
					nf.setMaximumFractionDigits(0);
					String rounded = nf.format(pri);
					s_assert.assertEquals("$"+rounded, arr[0]);
					log.add("Product price is verified with PLP and PDP");
					pdp.pdpAddtocart().click();
					log.add("Product added to the cart");
					WebDriverWait wdw = new WebDriverWait(wd,500);
					wdw.until(ExpectedConditions.alertIsPresent());
					s_assert.assertEquals("Product added", wd.switchTo().alert().getText());
					wd.switchTo().alert().accept();
					i++;
					break;
				}
				
			}
			if(!flag)
			{
				System.out.println("Product not available: "+td.get("Product"+i+" Name"));
			} 
			
			break;
		}
	}

	wd.close();

	}
@Test(dataProvider="category")
public void categoryInEDGE(HashMap<String,String> td) throws IOException, InterruptedException {
log=new ArrayList<String>();
	boolean flag=false;
	int i=1;
	s_assert=new SoftAssert();
	wd=initializeDriver("Edge");
	POManager pom=new POManager(wd);
	Navigatetosite(wd);
	log.add("Strore Website is loaded successfully using the given Url: "+prop.getProperty("url"));
	pom.performImplicitWait(wd);
	pom.CreateTestCase("test123");
	pom.getHomePage();
for(WebElement catergory:pom.getCatergory())
{
	if(catergory.getText().equals(td.get("Category"))) {
		catergory.click();
		log.add(td.get("Category")+" Category is selected");
		Thread.sleep(8000);
		for(WebElement plppage:pom.getPLP())
		{
			if(plppage.getText().equals(td.get("Product"+i+" Name")))
			{
				flag=true;
				plppage.click();
				log.add(td.get("Product"+i+" Name")+" is selected from PLP Page"); 
				PDP pdp=new PDP(wd);
				s_assert.assertEquals(td.get("Product"+i+" Name"),pdp.pdpName().getText());
				log.add(td.get("Product"+i+" Name")+" Selected in PLP is Verified with ProductName in PDP"+pdp.pdpName().getText()); 
				String arr[]=pdp.pdpPrice().getText().split(" ");
				float pri=Float.parseFloat(td.get("Product"+i+" Price"));
				NumberFormat nf = NumberFormat.getNumberInstance();
				nf.setMaximumFractionDigits(0);
				String rounded = nf.format(pri);
				s_assert.assertEquals("$"+rounded, arr[0]);
				log.add("Product price is verified with PLP and PDP");
				pdp.pdpAddtocart().click();
				log.add("Product added to the cart");
				WebDriverWait wdw = new WebDriverWait(wd,500);
				wdw.until(ExpectedConditions.alertIsPresent());
				s_assert.assertEquals("Product added", wd.switchTo().alert().getText());
				wd.switchTo().alert().accept();
				i++;
				break;
			}
			
		}
		if(!flag)
		{
			System.out.println("Product not available: "+td.get("Product"+i+" Name"));
		} 
		
		break;
	}
}

wd.close();

}

	
//@Test(dataProvider="category")
//public void categoryInEdge(HashMap<String,String> td) throws IOException, InterruptedException {
//log=new ArrayList<String>();
//test=extent.createTest(td.get("Test Id"));
//	boolean flag=false;
//	int i=1;
//
//	s_assert=new SoftAssert();
//	WebDriver wd=initializeDriver("Edge");
//	wd.get(prop.getProperty("url"));
//	log.add("Strore Website is loaded successfully using the given Url: "+prop.getProperty("url"));
//	wd.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
//
//HomePage hp=new HomePage(wd);
//for(WebElement w:hp.Category())
//{
//	if(w.getText().equals(td.get("Category"))) {
//		w.click();
//		log.add(td.get("Category")+" Category is selected");
//		Thread.sleep(8000);
//		for(WebElement we:hp.plp())
//		{
//			if(we.getText().equals(td.get("Product"+i+" Name")))
//			{
//				flag=true;
//				we.click();
//				log.add(td.get("Product"+i+" Name")+" is selected from PLP Page"); 
//				PDP pdp=new PDP(wd);
//				s_assert.assertEquals(td.get("Product"+i+" Name"),pdp.pdpName().getText());
//				log.add(td.get("Product"+i+" Name")+" Selected in PLP is Verified with ProductName in PDP"+pdp.pdpName().getText()); 
//				String arr[]=pdp.pdpPrice().getText().split(" ");
//				float pri=Float.parseFloat(td.get("Product"+i+" Price"));
//				NumberFormat nf = NumberFormat.getNumberInstance();
//				nf.setMaximumFractionDigits(0);
//				String rounded = nf.format(pri);
//				s_assert.assertEquals("$"+rounded, arr[0]);
//				log.add("Product price is verified with PLP and PDP");
//				pdp.pdpAddtocart().click();
//				log.add("Product added to the cart");
//				WebDriverWait wdw = new WebDriverWait(wd,500);
//				wdw.until(ExpectedConditions.alertIsPresent());
//				s_assert.assertEquals("Product added", wd.switchTo().alert().getText());
//				wd.switchTo().alert().accept();
//				i++;
//				break;
//			}
//			
//		}
//		if(!flag)
//		{
//			System.out.println("Product not available: "+td.get("Product"+i+" Name"));
//		} 
//		
//		break;
//	}
//}
//
//wd.close();
//s_assert.assertAll();
//}


	
	
	@DataProvider
	public Object[][] category() throws IOException
	{
		ArrayList<HashMap<String,String>>  td=tcdata("Category");
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
