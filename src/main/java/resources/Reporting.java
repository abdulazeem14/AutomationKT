package resources;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class Reporting extends Base {
	private static ExtentHtmlReporter  htmlReporter;
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> testCase=new ThreadLocal<>();
	private static String reportsDirectory;
	private static boolean reportsInitialized=false;
	public static final int maxRetryCount=1;
	

	
	public static void initializeReports()
	{
		if(!reportsInitialized)
		{
			reportsInitialized=true;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");

			String reportFilePath="";


			reportFilePath=reportsDirectory+"/Report_"+simpleDateFormat.format(new Date())+".html";

			htmlReporter = new ExtentHtmlReporter(reportFilePath);
			//ConsoleLogger.writeConsoleLog(ConsoleLogger.LogLevel.INFO, "HTML Report created at:"+reportFilePath);


			//initialize ExtentReports and attach the HtmlReporter
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);


			extent.setSystemInfo("OS", System.getProperty("os.name"));
			String browserName="";
			switch("Chrome")
			{
			case "Chrome":
				browserName="Google Chrome";
				break;
			case "Firefox":
				browserName="Mozilla Firefox";
				break;
			default:
				browserName="NA";
				break;

			}
			extent.setSystemInfo("Browser", browserName);
			extent.setSystemInfo("User", System.getProperty("user.name"));
			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);

			htmlReporter.config().setDocumentTitle("Test Execution Report");
			htmlReporter.config().setReportName("Test Execution Report");

			extent.flush();
		}
	}
	public static void createNewTest(String tcName, String tcDescription) {
		//test=extent.createTest(tcName);

		testCase.set(extent.createTest(tcName));
		Markup m1 = MarkupHelper.createLabel("<b>TC Description: </b>"+tcDescription, ExtentColor.WHITE);
		testCase.get().info(m1);
		extent.flush();
	}

}
