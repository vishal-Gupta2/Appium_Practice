package org.Appium.TestUtills;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extent;
	public static ExtentReports getReporterObject() {
		
		String path = System.getProperty("user.dir")+"\\Resources\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("App Test Result");
		reporter.config().setReportName("Vishal App test");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vishal Gupta");
		return extent;
		
	}
}
