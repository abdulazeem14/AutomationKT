package common;

import org.openqa.selenium.WebDriver;

public class AlertMsg {
    public void alertaccept(WebDriver wd) {
        wd.switchTo().alert().accept();
    }
    public String alertGettext(WebDriver wd) {
        return wd.switchTo().alert().getText();
    }

}