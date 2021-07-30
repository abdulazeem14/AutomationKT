package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {
    WebDriver wd;
    By Total = By.xpath("//*[@id=\"totalp\"]");
    By PlaceOrder = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button");
    By TotalNproduct = By.xpath("//*[@id=\"tbodyid\"]/tr");
    By Pname = By.xpath("//*[@id=\"name\"]");
    By Pcountry = By.xpath("//*[@id=\"country\"]");
    By Pcity = By.xpath("//*[@id=\"city\"]");
    By Pcreditcard = By.xpath("//*[@id=\"card\"]");
    By Pmonth = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]");
    By Pyear = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]");
    By Purchase = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By Close = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]");
    By OrderC = By.xpath("/html/body/div[10]/p");
    By OrderOk = By.xpath("/html/body/div[10]/div[7]/div/button");
    By Oderdetails = By.xpath("/html/body/div[10]/p");

    public Cart(WebDriver wd) {
        super();
        this.wd = wd;
    }
    public WebElement title(int i) {
        By Title = By.xpath("//*[@id='tbodyid']/tr[" + i + "]/td[2]");
        return wd.findElement(Title);
    }
    public WebElement price(int i) {
        By Price = By.xpath("//*[@id='tbodyid']/tr[" + i + "]/td[3]");
        return wd.findElement(Price);
    }
    public WebElement delete(int i) {
        By delete = By.xpath("//*[@id='tbodyid']/tr[" + i + "]/td[4]");
        return wd.findElement(delete);
    }
    public WebElement total() {
        return wd.findElement(Total);
    }
    public WebElement placeOrder() {
        return wd.findElement(PlaceOrder);
    }
    public int totalNproduct() {
        List < WebElement > tp = wd.findElements(TotalNproduct);
        return tp.size();
    }

    public WebElement pname() {
        return wd.findElement(Pname);
    }
    public WebElement pcountry() {
        return wd.findElement(Pcountry);
    }
    public WebElement pCity() {
        return wd.findElement(Pcity);
    }
    public WebElement creditcard() {
        return wd.findElement(Pcreditcard);
    }
    public WebElement pMonth() {
        return wd.findElement(Pmonth);
    }
    public WebElement pYear() {
        return wd.findElement(Pyear);
    }
    public WebElement purchase() {
        return wd.findElement(Purchase);
    }
    public WebElement colse() {
        return wd.findElement(Close);
    }
    public WebElement orederc() {
        return wd.findElement(OrderC);
    }
    public WebElement orderOk() {
        return wd.findElement(OrderOk);
    }
    public WebElement orderDetails() {
        return wd.findElement(Oderdetails);
    }




}