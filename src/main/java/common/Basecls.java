package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basecls {
    public static org.apache.logging.log4j.Logger log1;
    public static WebDriver objWD;
    public static Properties objProp;
    public static ExtentHtmlReporter objHtmlReport;
    public static ExtentReports objExtent;
    public static ExtentTest objTest;
    public ExtentTest objChildTest;
    public static ArrayList < String > arrLog;

    @BeforeSuite
    public void Bsuite() {
        //String log4jConfigFile = ("C:\Users\azeem.hameed\Downloads\QTRecognition\QTRecognition\src\main\java\resources\log4j2.xml");
        //String log4jConfigFile = ("C:\\Users\\azeem.hameed\\Downloads\\QTRecognition\\QTRecognition\\src\\main\\java\\resources\\log4j2.xml");
        //ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jConfigFile));
        //Configurator.initialize(null, source);
        objProp = new Properties();
        //FileInputStream fis=new FileInputStream("C:\\Users\\Qualitest\\Downloads\\QTRecognition-2\\QTRecognition-2\\src\\main\\java\\resources\\data.properties");
        FileInputStream objFIS;
        try {

            objFIS = new FileInputStream("C:\\Users\\azeem.hameed\\eclipse-workspace\\Store\\src\\main\\java\\resources\\data.properties");
            objProp.load(objFIS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // log1 = LogManager.getLogger(Base.class.getName()); 
        objHtmlReport = new ExtentHtmlReporter("./Reports/ex_report.html");
        objExtent = new ExtentReports();
        objExtent.attachReporter(objHtmlReport);


    }
    @AfterMethod
    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            //test.fail(MarkupHelper.createLabel(result.getName()+"Test case Failed", ExtentColor.RED));
            objChildTest = objTest.createNode(result.getThrowable().getMessage());
            String[] strarrMSG = result.getThrowable().getMessage().split(":");
            String[] strarrMSG1 = strarrMSG[1].split(",");
            for (String s: arrLog) {
                objChildTest.pass(s);
            }
            for (int i = 0; i < strarrMSG1.length; i++) {
                objChildTest.fail(strarrMSG1[i]);
            }
            //childTest.addScreenCaptureFromPath("C:\\Users\\azeem.hameed\\OneDrive - Qualitest Group\\Pictures\\Screenshots\\Screenshot (35).png", "error");

            //test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            //test.pass(MarkupHelper.createLabel(result.getName()+"Test case passes", ExtentColor.GREEN));
            objChildTest = objTest.createNode(result.getName());
            //test.createNode("MyFirstChildTest","check for node 1");
            for (String s: arrLog) {
                objChildTest.pass(s);
            }


        }

    }


    @AfterSuite
    public void aft() {
        objExtent.flush();

    }
    public void takeScreenshot() {
        Random objRandom = new Random();
        String strVal = "screenshot" + objRandom.nextInt(1000);
        File objSrc = ((TakesScreenshot) objWD).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(src, new File("C:\\Users\\Qualitest\\Desktop\\ss\\sc12.png"));
        try {
            FileUtils.copyFile(objSrc, new File("/Users/abdulazeem/Desktop/qtscreenshot/" + strVal + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static WebDriver initializeDriver(String browsername){

        if (browsername.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            objWD = new ChromeDriver();

        } else if (browsername.equals("firefox")) {
            objWD = new FirefoxDriver();
        } else if (browsername.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            objWD = new EdgeDriver();
        } else {
            objWD = new InternetExplorerDriver();
        }
        return objWD;
    }

    public static ArrayList < HashMap < String, String >> tcdata(String sheetname){
        try {
        FileInputStream objFIS = new FileInputStream(new File("./TestData/testdata.xlsx"));
        ArrayList < HashMap < String, String >> arrLst = new ArrayList < > ();
        //  XSSFRow row;
        XSSFWorkbook objWorkbook = new XSSFWorkbook(objFIS);
        XSSFSheet objSpreadsheet = objWorkbook.getSheet(sheetname);
        int intLastRow = objSpreadsheet.getLastRowNum();
        Row firstRow = objSpreadsheet.getRow(0);


        for (int i = 1; i <= intLastRow; i++) {

            HashMap < String, String > hm = new HashMap < > ();
            Row rows = objSpreadsheet.getRow(i);
            //System.out.println(i+"::"+row);
            int intlastCell = firstRow.getLastCellNum();
            for (int j = 0; j < intlastCell; j++) {
                Cell objdataCell = rows.getCell(j);
                Cell objheaderCell = firstRow.getCell(j);
                String strHeader = getCellData(objheaderCell, objWorkbook);
                String strValue = getCellData(objdataCell, objWorkbook);
                hm.put(strHeader, strValue);

            }
            arrLst.add(hm);
        }
        return arrLst;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
        
   

    }
    public static String getCellData(Cell cell, Workbook workbook) {
        String strCellDataValue = "";
        if (cell == null)
            return strCellDataValue;
        switch (cell.getCellType()) {
            case BOOLEAN:
                strCellDataValue = Boolean.toString(cell.getBooleanCellValue());
                break;

            case _NONE:
            case BLANK:
            case ERROR:
                break;

            case NUMERIC:
                strCellDataValue = Double.toString(cell.getNumericCellValue());
                break;

            case STRING:
                strCellDataValue = cell.getStringCellValue();
                break;
            case FORMULA:
                break;
            default:
                break;
        }
        return strCellDataValue;
    }

    public WebElement getShadowRoot(WebElement host, WebDriver wdr) {
        JavascriptExecutor js = (JavascriptExecutor) wdr;
        WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", host);
        return shadowRoot;
    }
    public void Navigatetosite(WebDriver wd) {
        wd.get(objProp.getProperty("url"));
    }


}