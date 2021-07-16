package resources;

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


public class Base {
	public static org.apache.logging.log4j.Logger log1;
	public static WebDriver wd;
	public static Properties prop;
	public static ExtentHtmlReporter htmlReporter;
 	public static ExtentReports extent;
 	public static ExtentTest test;
 	public ExtentTest childTest;
 	public static ArrayList<String> log;
	
 	@BeforeSuite
    public void Bsuite() throws FileNotFoundException, IOException{
     //   String log4jConfigFile = ("C:\Users\azeem.hameed\Downloads\QTRecognition\QTRecognition\src\main\java\resources\log4j2.xml");
//		 String log4jConfigFile = ("C:\\Users\\azeem.hameed\\Downloads\\QTRecognition\\QTRecognition\\src\\main\\java\\resources\\log4j2.xml");
//		ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jConfigFile));
//        Configurator.initialize(null, source);
        prop=new Properties();
		//FileInputStream fis=new FileInputStream("C:\\Users\\Qualitest\\Downloads\\QTRecognition-2\\QTRecognition-2\\src\\main\\java\\resources\\data.properties");
		FileInputStream fis=new FileInputStream("C:\\Users\\azeem.hameed\\eclipse-workspace\\Store\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
       // log1 = LogManager.getLogger(Base.class.getName()); 
        htmlReporter=new ExtentHtmlReporter("./Reports/ex_report.html");
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
       
    	
	}
 	@AfterMethod
 	public void getResult(ITestResult result) throws IOException
 	{
 	
 		if(result.getStatus()==ITestResult.FAILURE)
 		{
 			//test.fail(MarkupHelper.createLabel(result.getName()+"Test case Failed", ExtentColor.RED));
 			childTest=test.createNode(result.getThrowable().getMessage());
 			String[] str=result.getThrowable().getMessage().split(":");
 			String[] str1=str[1].split(",");
 			for(String s:log)
 			{
 				childTest.pass(s);
 			}
 			for(int i=0;i<str1.length;i++) {
 			childTest.fail(str1[i]);
 			}
 			//childTest.addScreenCaptureFromPath("C:\\Users\\azeem.hameed\\OneDrive - Qualitest Group\\Pictures\\Screenshots\\Screenshot (35).png", "error");
 			
 			//test.fail(result.getThrowable());
 		}
 		else if(result.getStatus()==ITestResult.SUCCESS)
 		{
 			//test.pass(MarkupHelper.createLabel(result.getName()+"Test case passes", ExtentColor.GREEN));
 			childTest=test.createNode(result.getName());
 			//test.createNode("MyFirstChildTest","check for node 1");
 			for(String s:log)
 			{
 				childTest.pass(s);
 			}
 			
 			
 		}
 		
 	}
 	
 	
 	@AfterSuite
 	public void aft() {
 		extent.flush();
 		
 	}
	public void takeScreenshot() throws IOException
	{
		Random ran=new Random();
		String val="screenshot"+ran.nextInt(1000);
		File src=((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(src, new File("C:\\Users\\Qualitest\\Desktop\\ss\\sc12.png"));
		FileUtils.copyFile(src, new File("/Users/abdulazeem/Desktop/qtscreenshot/"+val+".png"));
	}
	public static WebDriver initializeDriver(String browsername) throws IOException {
		
		if(browsername.equals("Chrome"))
		{
		WebDriverManager.chromedriver().setup();
			wd=new ChromeDriver();
			
		}
		else if(browsername.equals("firefox"))
		{
			wd=new FirefoxDriver();
		}
		else if(browsername.equals("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			wd=new EdgeDriver();
		}

		else
		{
			wd=new InternetExplorerDriver();
		}
		return wd;
	}

	public static  ArrayList<HashMap<String,String>> tcdata(String sheetname) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File("./TD/testdata.xlsx"));
		
		ArrayList<HashMap<String,String>> ls=new ArrayList<>();
		//  XSSFRow row;
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet spreadsheet = workbook.getSheet(sheetname);
	    int lastRow = spreadsheet.getLastRowNum();
		Row firstRow= spreadsheet.getRow(0);
		
		
		for(int i=1;i<=lastRow;i++){

			HashMap<String,String> hm=new HashMap<>();
			Row rows = spreadsheet.getRow(i);
			//System.out.println(i+"::"+row);
			int lastCell=firstRow.getLastCellNum();
			for(int j=0;j<lastCell;j++)
			{
				Cell dataCell=rows.getCell(j);
				Cell headerCell=firstRow.getCell(j);
				String header=getCellData(headerCell,workbook);
				String value=getCellData(dataCell,workbook);
				hm.put(header, value);
	
			}
			ls.add(hm);
			
		}
		return ls;

}
	public static String getCellData(Cell cell,Workbook workbook)
	{
		String cellDataValue="";
		if(cell==null)
			return cellDataValue;
		switch(cell.getCellType())
		{
		case BOOLEAN:
			cellDataValue=Boolean.toString(cell.getBooleanCellValue());
			break;

		case _NONE:
		case BLANK:
		case ERROR:
			break;

		case NUMERIC:
			cellDataValue=Double.toString(cell.getNumericCellValue());
			break;

		case STRING:
			cellDataValue=cell.getStringCellValue();
			break;
		case FORMULA:
			break;
		default:
			break;
		}
		return cellDataValue;
	}
	
	public WebElement getShadowRoot(WebElement host,WebDriver wdr) {
		  JavascriptExecutor js = (JavascriptExecutor)wdr;
		  WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", host);
		  return shadowRoot;
		}
	public void Navigatetosite(WebDriver wd) {
		wd.get(prop.getProperty("url"));
			}
	

}
