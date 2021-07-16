package store_Testcase;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Cart;
import pageObjects.HomePage;
import resources.Base;

public class CartTC extends Base{

	SoftAssert s_assert;
	@Test(dataProvider="validtestdata")
	public void cart(HashMap<String,String> td) throws IOException, InterruptedException {
		log=new ArrayList<String>();
		test=extent.createTest(td.get("Test ID"));
		WebDriver wd=initializeDriver("Chrome");
		s_assert=new SoftAssert();
	HomePage hp=new HomePage(wd);
	NumberFormat nf = NumberFormat.getNumberInstance();
	nf.setMaximumFractionDigits(0);
	
	Category addproduct=new Category(wd);
int i=1;
		
	while(i<=3) {
		if(td.get("Product"+i+" Name").equals(""))
		{
			break;
		}
		
		else {
		
			addproduct.category(td.get("Product"+i+" Name"), td.get("Product"+i+" Amount"));
			i++;
		}
		
		}
	hp.cart().click();
	Thread.sleep(5000);
	Cart ct=new Cart(wd);
	i=1;
	while(i<=3)
	{
		if(td.get("Product"+i+" Name").equals(""))
		{
			break;
			
		}
		else {
			//System.out.println(td.k(ct.title(i).getText())+"=="+ct.title(i).getText());
			s_assert.assertEquals(td.containsValue(ct.title(i).getText()),true,"product name");
			//have to assert cart items
		}
		i++;
		
	}
	float total=Float.parseFloat(td.get("Total Amount"));
	String rounded = nf.format(total).replaceAll("[^a-zA-Z0-9]","");
	System.out.println(rounded+"==check total amt="+ct.total().getText());
	s_assert.assertEquals(rounded, ct.total().getText(),"Total Amount");
	ct.placeOrder().click();
	Thread.sleep(5000);
	ct.pname().sendKeys(td.get("Name"));
	ct.creditcard().sendKeys(td.get("Credit Card Number"));
	ct.purchase().click();
	if(td.get("Name").equals("")||td.get("Credit Card Number").equals(""))
	{
		Thread.sleep(5000);
		s_assert.assertEquals(wd.switchTo().alert().getText(),"Please fill out Name and Creditcard.");
		wd.switchTo().alert().accept();
	}
	else {
	
	Thread.sleep(5000);
	
	String str[]=ct.orderDetails().getText().split("\\r?\\n");
	HashMap<String,String> od=new HashMap<>();
for(int j=0;j<str.length;j++)
{
	String[] details=str[j].split(":");
	od.put(details[0], details[1].substring(1));
	
}
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDateTime now = LocalDateTime.now();
System.out.println(od.get("Name")+"=="+td.get("Name"));
s_assert.assertEquals(od.get("Name"), td.get("Name"),"order details");
System.out.println(od.get("Card Number")+"=="+td.get("Credit Card Number"));
s_assert.assertEquals(od.get("Card Number"), td.get("Credit Card Number"),"credit card number");
System.out.println(od.get("Amount")+"=="+rounded+" USD");
s_assert.assertEquals(od.get("Amount"),rounded+" USD","total amount");
System.out.println();
System.out.println(dtf.format(now));
System.out.println(od.get("Date")+"=="+dtf.format(now).toString());
s_assert.assertEquals(od.get("Date"), dtf.format(now).toString(),"date");
	}
	wd.close();
s_assert.assertAll();


	
	}
	
	@DataProvider
	public Object[][] validtestdata() throws IOException
	{

		ArrayList<HashMap<String,String>>  td=tcdata("Cart");
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
