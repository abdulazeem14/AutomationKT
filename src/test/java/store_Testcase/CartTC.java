package store_Testcase;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Manager.POManager;
import common.Base;


public class CartTC extends Base{

	SoftAssert s_assert;
	@Test(dataProvider="validtestdata")
	public void cart(HashMap<String,String> td) throws IOException, InterruptedException {
		log=new ArrayList<String>();
		test=extent.createTest(td.get("Test ID"));
		WebDriver wd=initializeDriver("Chrome");
		s_assert=new SoftAssert();
		POManager pom=new POManager(wd);
	NumberFormat nf = NumberFormat.getNumberInstance();
	nf.setMaximumFractionDigits(0);
	ADDProductToCart addproduct=new ADDProductToCart(wd);
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
	pom.getHomePage().cart().click();
	Thread.sleep(5000);
	
	i=1;
	while(i<=3)
	{
		if(td.get("Product"+i+" Name").equals(""))
		{
			break;
			
		}
		else {
			
			s_assert.assertEquals(td.containsValue(pom.getCart().title(i).getText()),true,"product name");
		
		}
		i++;
		
	}
	float total=Float.parseFloat(td.get("Total Amount"));
	String rounded = nf.format(total).replaceAll("[^a-zA-Z0-9]","");
	System.out.println(rounded+"==check total amt="+pom.getCart().total().getText());
	s_assert.assertEquals(rounded, pom.getCart().total().getText(),"Total Amount");
	pom.getCart().placeOrder().click();
	Thread.sleep(5000);
	pom.getCart().pname().sendKeys(td.get("Name"));
	pom.getCart().creditcard().sendKeys(td.get("Credit Card Number"));
	pom.getCart().purchase().click();
	if(td.get("Name").equals("")||td.get("Credit Card Number").equals(""))
	{
		Thread.sleep(5000);
		s_assert.assertEquals(wd.switchTo().alert().getText(),"Please fill out Name and Creditcard.");
		wd.switchTo().alert().accept();
	}
	else {
	
	Thread.sleep(5000);
	
	String str[]=pom.getCart().orderDetails().getText().split("\\r?\\n");
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
