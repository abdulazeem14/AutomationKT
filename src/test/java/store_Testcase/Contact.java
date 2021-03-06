package store_Testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Manager.PgObjManager;
import common.Basecls;


public class Contact extends Basecls {
    SoftAssert s_assert;
    WebDriver wd;
    @Test(dataProvider = "validtestdata")
    public void contactInChrome(HashMap < String, String > td){
        String ContactEmail = td.get("ContactEmail");
        String ContactName = td.get("ContactName");
        String Message = td.get("Message");
        String TestID = td.get("Test ID");
        arrLog = new ArrayList < String > ();
        wd = initializeDriver("Chrome");
        s_assert = new SoftAssert();
        Navigatetosite(wd);
        arrLog.add("Store website is loaded Successfully using the Given URL: " + objProp.getProperty("url"));
        PgObjManager pom = new PgObjManager(wd);
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getReport().CreateTestCase(TestID);
        pom.getHomePage().NavigatetoContact();
        pom.getHomePage().EnterContactDetails(ContactEmail, ContactName, Message);

        arrLog.add("Contact Email filled with: " + ContactEmail);

        arrLog.add("Contact name is filled with: " + ContactName);

        arrLog.add("Message field is filled with: " + Message);
        pom.getHomePage().SendMessage();

        if (ContactEmail.equals("") || ContactName.equals("") || Message.equals("")) {
            s_assert.assertEquals(wd.switchTo().alert().getText(), "input field is empty", "User can send a message if input field is empty" +
                "");

            pom.getalert().alertaccept(wd);
        } else {
            s_assert.assertEquals(wd.switchTo().alert().getText().toString(), "Thanks for the message!!");
            pom.getalert().alertaccept(wd);
            arrLog.add("After message sent Acknowledgement message has been displayed as Thanks for the message!!");
        }
        wd.close();
        s_assert.assertAll();

    }

    @Test(dataProvider = "validtestdata")
    public void contactInEDGE(HashMap < String, String > td){
        String ContactEmail = td.get("ContactEmail");
        String ContactName = td.get("ContactName");
        String Message = td.get("Message");
        String TestID = td.get("Test ID");
        arrLog = new ArrayList < String > ();
        wd = initializeDriver("EDGE");
        s_assert = new SoftAssert();
        Navigatetosite(wd);
        arrLog.add("Store website is loaded Successfully using the Given URL: " + objProp.getProperty("url"));
        PgObjManager pom = new PgObjManager(wd);
        pom.getHomePage();
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getReport().CreateTestCase(TestID);
        pom.getHomePage().NavigatetoContact();
        pom.getHomePage().EnterContactDetails(ContactEmail, ContactName, Message);
        arrLog.add("Contact Email filled with: " + ContactEmail);
        arrLog.add("Contact name is filled with: " + ContactName);
        arrLog.add("Message field is filled with: " + Message);
        pom.getHomePage().SendMessage();

        if (ContactEmail.equals("") || ContactName.equals("") || Message.equals("")) {
            s_assert.assertEquals(wd.switchTo().alert().getText(), "input field is empty", "User can send a message if input field is empty" +
                "");

            pom.getalert().alertaccept(wd);
        } else {
            s_assert.assertEquals(wd.switchTo().alert().getText().toString(), "Thanks for the message!!");
            pom.getalert().alertaccept(wd);
            arrLog.add("After message sent Acknowledgement message has been displayed as Thanks for the message!!");
        }
        wd.close();
        s_assert.assertAll();

    }

    //	@Test(dataProvider="validtestdata")
    //	public void contactInEdge(HashMap<String,String> td) throws IOException, InterruptedException {
    //		String ContactEmail=td.get("ContactEmail");
    //		String ContactName=td.get("ContactName");
    //		String Message=td.get("Message");
    //		log=new ArrayList<String>();
    //		 wd=initializeDriver("Edge");
    //		s_assert=new SoftAssert();
    //		Navigatetosite(wd);
    //		//wd.get(prop.getProperty("url"));
    //		log.add("Store website is loaded Successfully using the Given URL: "+prop.getProperty("url"));
    //		POManager pom=new POManager(wd);
    //		pom.getHomePage();
    //		pom.performImplicitWait(wd);
    //		//wd.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    //		pom.CreateTestCase("test1");
    //		// test=extent.createTest(td.get("Test ID"));
    //		pom.NavigatetoContact();
    //	//HomePage hp=new HomePage(wd);
    //		
    //	//hp.contact().click();
    //pom.EnterContactDetails(ContactEmail, ContactName, Message);
    ////hp.cEmail().sendKeys(ContactEmail);
    //	log.add("Contact Email filled with: "+ContactEmail);
    //	//hp.cContact().sendKeys(ContactName);
    //	log.add("Contact name is filled with: "+ContactName);
    //	//hp.CMessage().sendKeys(Message);
    //	log.add("Message field is filled with: "+Message);
    //	//pom.SendMessage();
    //
    //	//hp.cSendMessage().click();
    //	if(ContactEmail.equals("")||ContactName.equals("")||Message.equals(""))
    //	{
    //		s_assert.assertEquals(wd.switchTo().alert().getText(), "input field is empty","User can send a message if input field is empty"
    //				+ "");
    //		
    //		wd.switchTo().alert().accept();
    //	}
    //	else {
    //s_assert.assertEquals(wd.switchTo().alert().getText().toString(), "Thanks for the message!!");
    //wd.switchTo().alert().accept();
    //log.add("After message sent Acknowledgement message has been displayed as Thanks for the message!!");
    //	}
    //	wd.close();
    //s_assert.assertAll();
    //	
    //	}
    //	
    //	


    @DataProvider(name = "validtestdata")
    public Object[][] validtestdata() {

        ArrayList<HashMap<String, String>> td;
        td = tcdata("Contact");
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