package Manager;

import org.openqa.selenium.WebDriver;
import Utilities.ReportingUtils;
import common.AlertMsg;

import common.Basecls;
import common.WaitOperations;
import pageObjects.Cart;
import pageObjects.HomePage;
import pageObjects.PDP;

public class PgObjManager extends Basecls {
    private WebDriver wd;
    private HomePage hp;
    private PDP pdp;
    private Cart cart;
    private ReportingUtils tc;
    private WaitOperations waitoperation;
    private AlertMsg alert;


    public PgObjManager(WebDriver wd) {
        this.wd = wd;
    }
    public WaitOperations getwaitOpertion() {
        return (waitoperation == null) ? waitoperation = new WaitOperations(wd) : waitoperation;
    }

    public HomePage getHomePage() {
        return (hp == null) ? hp = new HomePage(wd) : hp;
    }
    public PDP getPDP() {
        return (pdp == null) ? pdp = new PDP(wd) : pdp;
    }
    public Cart getCart() {
        return (cart == null) ? cart = new Cart(wd) : cart;
    }

    public ReportingUtils getReport() {
        return (tc == null) ? tc = new ReportingUtils() : tc;

    }
    public AlertMsg getalert() {
        return alert == null ? alert = new AlertMsg() : alert;
    }




}