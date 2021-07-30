package store_Testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Manager.PgObjManager;
import common.Basecls;



public class Login extends Basecls {
    public static SoftAssert softassert;
    WebDriver wd;
    @Test(dataProvider = "validtestdata")
    public void loginCheckEdge(HashMap < String, String > testdata){
        arrLog = new ArrayList < String > ();
        softassert = new SoftAssert();
        wd = initializeDriver("Edge");
        String Username = testdata.get("UserName");
        String Password = testdata.get("Password");
        String Function = testdata.get("Function");
        String TestId = testdata.get("Test Id");
        String AlertMessage = testdata.get("AlertMessage");
        Navigatetosite(wd);
        arrLog.add("Store Website is Loaded with given URL:" + objProp.getProperty("url"));
        PgObjManager pom = new PgObjManager(wd);
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getReport().CreateTestCase(TestId);
        pom.getHomePage().navigatetoLogin();
        if (Function.equals("logout")) {
            pom.getHomePage().login(Username, Password);
            arrLog.add("Login is Success with valid credentials");
            pom.getwaitOpertion().performExplictwait(wd, pom.getHomePage().getlogoutxpath());
            pom.getHomePage().logout();
            arrLog.add("User was able to logOut as Expected");
        }

        if (Function.equals("")) {
            pom.getHomePage().login(Username, Password);
            arrLog.add("Username is entered:" + Username);
            arrLog.add("Password is entered:" + Password);

            WebDriverWait w = new WebDriverWait(wd, 5);
            try {

                if (w.until(ExpectedConditions.alertIsPresent()) != null) {
                    softassert.assertEquals(wd.switchTo().alert().getText(), AlertMessage);
                    arrLog.add(testdata.get("AlertMessage") + ": Alert message is displayed as expected");
                    pom.getalert().alertaccept(wd);
                }
            } catch (Exception E) {
                softassert.assertEquals(pom.getHomePage().getUserName(), "Welcome " + Username);
                arrLog.add("Login success with valid credentials");
            }
        }
        wd.close();
        softassert.assertAll();
    }

    @Test(dataProvider = "validtestdata")
    public void loginCheckChrome(HashMap < String, String > testdata) {
        arrLog = new ArrayList < String > ();
        softassert = new SoftAssert();
        wd = initializeDriver("Chrome");
        String Username = testdata.get("UserName");
        String Password = testdata.get("Password");
        String Function = testdata.get("Function");
        String TestId = testdata.get("Test Id");
        String AlertMessage = testdata.get("AlertMessage");
        Navigatetosite(wd);
        arrLog.add("Store Website is Loaded with given URL:" + objProp.getProperty("url"));
        PgObjManager pom = new PgObjManager(wd);
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getReport().CreateTestCase(TestId);
        pom.getHomePage().navigatetoLogin();
        if (Function.equals("logout")) {
            pom.getHomePage().login(Username, Password);
            arrLog.add("Login is Success with valid credentials");
            pom.getwaitOpertion().performExplictwait(wd, pom.getHomePage().getlogoutxpath());
            pom.getHomePage().logout();
            arrLog.add("User was able to logOut as Expected");
        }

        if (Function.equals("")) {
            pom.getHomePage().login(Username, Password);
            arrLog.add("Username is entered:" + Username);
            arrLog.add("Password is entered:" + Password);

            WebDriverWait w = new WebDriverWait(wd, 5);
            try {

                if (w.until(ExpectedConditions.alertIsPresent()) != null) {
                    softassert.assertEquals(wd.switchTo().alert().getText(), AlertMessage);
                    arrLog.add(testdata.get("AlertMessage") + ": Alert message is displayed as expected");
                    pom.getalert().alertaccept(wd);
                }
            } catch (Exception E) {
                softassert.assertEquals(pom.getHomePage().getUserName(), "Welcome " + Username);
                arrLog.add("Login success with valid credentials");
            }
        }
        wd.close();
        softassert.assertAll();
    }



    @DataProvider
    public Object[][] validtestdata() {

        ArrayList<HashMap<String, String>> td;
        Object[][] obj;
        td = tcdata("Login");
        Iterator < HashMap < String, String >> itr = td.iterator();
        obj = new Object[td.size()][1];
        int i = 0;
        while (itr.hasNext()) {

            HashMap < String, String > a = itr.next();
            obj[i++][0] = a;
        }
        return obj;
     
        
       
   

    }


}