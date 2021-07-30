package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PDP {
    WebDriver wd;
    By PDPname = By.xpath("//*[@id=\"tbodyid\"]/h2");
    By PDPprice = By.xpath("//*[@id=\"tbodyid\"]/h3");
    By PDPAddtocart = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");
    public PDP(WebDriver wd) {
        this.wd = wd;
    }
    public WebElement pdpName() {
        return wd.findElement(PDPname);

    }
    public WebElement pdpPrice() {
        return wd.findElement(PDPprice);

    }
    public WebElement pdpAddtocart() {
        return wd.findElement(PDPAddtocart);

    }
    public String getProductName() {
        return pdpName().getText();
    }
    public String[] getProductPrice() {
        return pdpPrice().getText().split(" ");
    }
    public void addToCart() {
        pdpAddtocart().click();
    }

}