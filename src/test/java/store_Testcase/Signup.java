package store_Testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Manager.PgObjManager;
import common.Basecls;


public class Signup extends Basecls {

    SoftAssert s_assert;
    WebDriver wd;
    @Test(dataProvider = "validtestdata")
    public void signup(HashMap < String, String > td){
        String username = td.get("Username");
        String password = td.get("Password");
        arrLog = new ArrayList < String > ();
        wd = initializeDriver("Chrome");
        s_assert = new SoftAssert();
        Navigatetosite(wd);
        PgObjManager pom = new PgObjManager(wd);
        arrLog.add("STORE website loaded with the given URL");
        pom.getReport().CreateTestCase("test1");
        pom.getwaitOpertion().performImplicitWait(wd);
        pom.getHomePage();
        pom.getHomePage().Navigatetosignup();
        pom.getHomePage().SignupCredentials(username, password);
        pom.getHomePage().clickSingnup();
        if (!username.contains("@")) {
            s_assert.assertEquals(pom.getalert().alertGettext(wd), "Invalid username.", "Invalid email Error");
        }
        if (username.contains("@") && password.length() < 8) {
            s_assert.assertEquals(pom.getalert().alertGettext(wd), "passsword is not strong.", "password is not strong but new user creted");
        }
        if (username.contains("@")) {
            s_assert.assertEquals(pom.getalert().alertGettext(wd), "Sign up successful.");
            pom.getalert().alertaccept(wd);
            arrLog.add("User was registered Successfully");
        }
        wd.close();
        s_assert.assertAll();
    }

    @DataProvider
    public Object[][] validtestdata() throws IOException {

        ArrayList < HashMap < String, String >> td = tcdata("Signup");
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