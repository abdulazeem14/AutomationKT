package Utilities;

import org.openqa.selenium.WebDriver;

import common.Base;

public class ReportingUtils extends Base {

	
	public void CreateTestCase(String TestID)
	{
	test=extent.createTest(TestID);
	}
	
}
