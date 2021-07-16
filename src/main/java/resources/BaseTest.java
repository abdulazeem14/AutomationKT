package resources;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	@BeforeSuite
	public void setUp() {
		htmlReporter=new ExtentHtmlReporter("./Reports/ex_report.html");
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
 	@AfterMethod
 	public void getResult(ITestResult result)
 	{
 		if(result.getStatus()==ITestResult.FAILURE)
 		{
 			test.fail(MarkupHelper.createLabel(result.getName()+"Test case Failed", ExtentColor.RED));
 			test.fail(result.getThrowable());
 		}
 		else if(result.getStatus()==ITestResult.SUCCESS)
 		{
 			test.pass(MarkupHelper.createLabel(result.getName()+"Test case passes", ExtentColor.GREEN));
 		}
 		
 	}
 	@AfterSuite
 	public void tearDown()
 	{
 		extent.flush();
 	}
	
	
}
