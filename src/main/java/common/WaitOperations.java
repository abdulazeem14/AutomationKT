package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitOperations {
    WebDriver wd;
    public WaitOperations(WebDriver wd) {
        this.wd = wd;

    }
    public void performImplicitWait(WebDriver wd) {
        wd.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

    }
    public void performExplictwait(WebDriver WD, By xpath) {
        WebDriverWait wait = new WebDriverWait(WD, 1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }
    public void threadSleep(int ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}