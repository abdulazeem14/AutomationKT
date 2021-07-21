package Manager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ReportingUtils;
import common.Alert;
import common.Base;
import common.WaitOperation;
import pageObjects.Cart;
import pageObjects.HomePage;
import pageObjects.PDP;


public class POManager extends Base{
	private WebDriver wd;
	private HomePage hp;
	private PDP pdp;
	private Cart cart;
	private ReportingUtils tc;
	private WaitOperation waitoperation;
	private Alert alert;
	

	public POManager(WebDriver wd)
	{
		this.wd=wd;
	}
	public WaitOperation getwaitOpertion()
	{
		return (waitoperation==null)?waitoperation=new WaitOperation(wd):waitoperation;
	}
	
	public HomePage getHomePage()
	{
		return (hp == null) ? hp = new HomePage(wd) : hp;
	}
	public PDP getPDP()
	{
		return (pdp == null) ? pdp = new PDP(wd) : pdp;
	}
	public Cart getCart()
	{
		return (cart == null) ? cart = new Cart(wd) : cart;
	}
	public ReportingUtils getReport() {
		return (tc==null) ? tc=new ReportingUtils():tc;
		
	}
	public Alert getalert() {
		return alert==null?alert=new Alert():alert;
	}
	
	



}
