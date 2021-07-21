package store_Testcase;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.Base;
import pageObjects.HomePage;
import pageObjects.PDP;

public class ADDProductToCart extends Base{
	
	SoftAssert s_assert;
	WebDriver wd;
	public ADDProductToCart(WebDriver wd) {
		this.wd = wd;
	}


	@Test(dataProvider="categoryPhone")
	public void category(String productname,String price) throws IOException, InterruptedException {
		boolean flag=false;
		wd.get(prop.getProperty("url"));
		Thread.sleep(5000);
	HomePage hp=new HomePage(wd);
	for(WebElement we:hp.plp())
	{
		if(we.getText().equals(productname))
		{
			flag=true;
			we.click();
			Thread.sleep(5000);
			PDP pdp=new PDP(wd);
			String arr[]=pdp.pdpPrice().getText().split(" ");
			float pri=Float.parseFloat(price);
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(0);
			String rounded = nf.format(pri);
			pdp.pdpAddtocart().click();
			Thread.sleep(5000);
			wd.switchTo().alert().accept();
			
			break;
		}
		
	}
	if(!flag)
	{
		System.out.println("Product not available: "+productname);
	}
	}
	
	
	
	
	
	@DataProvider
	public Object[][] categoryPhone() throws IOException
	{
		wd=initializeDriver("Chrome");
		ArrayList<HashMap<String,String>>  td=tcdata("Category");
		Iterator<HashMap<String, String>> itr=td.iterator();
		
		boolean f=false;
	int i=0;
	int p=1;

	Object product[][]=new Object[2][2];
	System.out.println(td);
	
		while(itr.hasNext())
		{
		
			HashMap<String, String> a=itr.next();
			System.out.println(a);
			String base=a.get("Test Id");
			if(base.equals(""))
			{
				break;
			}
			while(true) {
			if(a.containsKey("Product"+p+" Name"))
			{
		product[i][0]=a.get("Product"+p+" Name");
		product[i++][1]=a.get("Product"+p+" Price");
		p++;
		f=true;
			}
			else {
				break;
			}
			
			}
			if(f)
			{
				break;
			}
		}
		
		test=extent.createTest("Test_Id-401");
		return product;
				
	}
	
	
}
