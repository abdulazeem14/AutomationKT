package store_Testcase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Manager.PgObjManager;
import common.Basecls;
public class CategoryTC extends Basecls {
    SoftAssert s_assert;
    WebDriver wd;

    @Test(dataProvider = "category")
    public void categoryInChrome(HashMap < String, String > td){
        arrLog = new ArrayList < String > ();
        String Category = td.get("Category");
        boolean flag = false;
        int i = 1;
        s_assert = new SoftAssert();
        wd = initializeDriver("Chrome");
        PgObjManager pom = new PgObjManager(wd);
        arrLog.add("Strore Website is loaded successfully using the given Url: " + objProp.getProperty("url"));
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getReport().CreateTestCase("test123");

        for (WebElement catergory: pom.getHomePage().getCatergory()) {
            if (catergory.getText().equals(Category)) {
                catergory.click();
                arrLog.add(Category + " Category is selected");
                for (WebElement plppage: pom.getHomePage().getPLP()) {
                    if (plppage.getText().equals(td.get("Product" + i + " Name"))) {
                        flag = true;
                        plppage.click();
                        arrLog.add(td.get("Product" + i + " Name") + " is selected from PLP Page");

                        s_assert.assertEquals(td.get("Product" + i + " Name"), pom.getPDP().pdpName().getText()); //product name
                        arrLog.add(td.get("Product" + i + " Name") + " Selected in PLP is Verified with ProductName in PDP" + pom.getPDP().pdpName().getText());
                        String arr[] = pom.getPDP().pdpPrice().getText().split(" "); //get price
                        float pri = Float.parseFloat(td.get("Product" + i + " Price"));
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        nf.setMaximumFractionDigits(0);
                        String rounded = nf.format(pri);
                        s_assert.assertEquals("$" + rounded, arr[0]);
                        arrLog.add("Product price is verified with PLP and PDP");
                        pom.getPDP().pdpAddtocart().click(); // add to cart
                        arrLog.add("Product added to the cart");
                        WebDriverWait wdw = new WebDriverWait(wd, 500);
                        wdw.until(ExpectedConditions.alertIsPresent());
                        s_assert.assertEquals("Product added", pom.getalert().alertGettext(wd));
                        pom.getalert().alertaccept(wd);
                        i++;
                        break;
                    }

                }
                if (!flag) {
                    System.out.println("Product not available: " + td.get("Product" + i + " Name"));
                }

                break;
            }
        }

        wd.close();
    }


    @Test(dataProvider = "category")
    public void categoryIn(HashMap < String, String > td){
        arrLog = new ArrayList < String > ();
        String Category = td.get("Category");
        boolean flag = false;
        int i = 1;
        s_assert = new SoftAssert();
        wd = initializeDriver("Chrome");
        PgObjManager pom = new PgObjManager(wd);
        arrLog.add("Strore Website is loaded successfully using the given Url: " + objProp.getProperty("url"));
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getReport().CreateTestCase("test123");

        for (WebElement catergory: pom.getHomePage().getCatergory()) {
            if (catergory.getText().equals(Category)) {
                catergory.click();
                arrLog.add(Category + " Category is selected");
                for (WebElement plppage: pom.getHomePage().getPLP()) {
                    if (plppage.getText().equals(td.get("Product" + i + " Name"))) {
                        flag = true;
                        plppage.click();
                        arrLog.add(td.get("Product" + i + " Name") + " is selected from PLP Page");
                        s_assert.assertEquals(td.get("Product" + i + " Name"), pom.getPDP().getProductName()); //product name
                        arrLog.add(td.get("Product" + i + " Name") + " Selected in PLP is Verified with ProductName in PDP" + pom.getPDP().getProductName());
                        String arr[] = pom.getPDP().getProductPrice(); //get price
                        float pri = Float.parseFloat(td.get("Product" + i + " Price"));
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        nf.setMaximumFractionDigits(0);
                        String rounded = nf.format(pri);
                        s_assert.assertEquals("$" + rounded, arr[0]);
                        arrLog.add("Product price is verified with PLP and PDP");
                        pom.getPDP().addToCart(); // add to cart
                        arrLog.add("Product added to the cart");
                        WebDriverWait wdw = new WebDriverWait(wd, 500);
                        wdw.until(ExpectedConditions.alertIsPresent());
                        s_assert.assertEquals("Product added", pom.getalert().alertGettext(wd));
                        pom.getalert().alertaccept(wd);
                        i++;
                        break;
                    }

                }
                if (!flag) {
                    System.out.println("Product not available: " + td.get("Product" + i + " Name"));
                }

                break;
            }
        }

        wd.close();
    }
    //	
    ////@Test(dataProvider="category")
    ////public void categoryInEdge(HashMap<String,String> td) throws IOException, InterruptedException {
    ////log=new ArrayList<String>();
    ////test=extent.createTest(td.get("Test Id"));
    ////	boolean flag=false;
    ////	int i=1;
    ////
    ////	s_assert=new SoftAssert();
    ////	WebDriver wd=initializeDriver("Edge");
    ////	wd.get(prop.getProperty("url"));
    ////	log.add("Strore Website is loaded successfully using the given Url: "+prop.getProperty("url"));
    ////	wd.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
    ////
    ////HomePage hp=new HomePage(wd);
    ////for(WebElement w:hp.Category())
    ////{
    ////	if(w.getText().equals(td.get("Category"))) {
    ////		w.click();
    ////		log.add(td.get("Category")+" Category is selected");
    ////		Thread.sleep(8000);
    ////		for(WebElement we:hp.plp())
    ////		{
    ////			if(we.getText().equals(td.get("Product"+i+" Name")))
    ////			{
    ////				flag=true;
    ////				we.click();
    ////				log.add(td.get("Product"+i+" Name")+" is selected from PLP Page"); 
    ////				PDP pdp=new PDP(wd);
    ////				s_assert.assertEquals(td.get("Product"+i+" Name"),pdp.pdpName().getText());
    ////				log.add(td.get("Product"+i+" Name")+" Selected in PLP is Verified with ProductName in PDP"+pdp.pdpName().getText()); 
    ////				String arr[]=pdp.pdpPrice().getText().split(" ");
    ////				float pri=Float.parseFloat(td.get("Product"+i+" Price"));
    ////				NumberFormat nf = NumberFormat.getNumberInstance();
    ////				nf.setMaximumFractionDigits(0);
    ////				String rounded = nf.format(pri);
    ////				s_assert.assertEquals("$"+rounded, arr[0]);
    ////				log.add("Product price is verified with PLP and PDP");
    ////				pdp.pdpAddtocart().click();
    ////				log.add("Product added to the cart");
    ////				WebDriverWait wdw = new WebDriverWait(wd,500);
    ////				wdw.until(ExpectedConditions.alertIsPresent());
    ////				s_assert.assertEquals("Product added", wd.switchTo().alert().getText());
    ////				wd.switchTo().alert().accept();
    ////				i++;
    ////				break;
    ////			}
    ////			
    ////		}
    ////		if(!flag)
    ////		{
    ////			System.out.println("Product not available: "+td.get("Product"+i+" Name"));
    ////		} 
    ////		
    ////		break;
    ////	}
    ////}
    ////
    ////wd.close();
    ////s_assert.assertAll();
    ////}
    //
    //
    //	
    //	
    @DataProvider
    public Object[][] category(){
        ArrayList<HashMap<String, String>> td;
        td = tcdata("Category");
        Iterator < HashMap < String, String >> itr = td.iterator();
        Object[][] obj = new Object[td.size()][1];
        int i = 0;
        while (itr.hasNext()) {

            HashMap < String, String > a = itr.next();


            obj[i++][0] = a;


        }
        return obj;
              

    }


}