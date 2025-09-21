package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	
	public ExtentReports report;
	public ExtentSparkReporter reporter; 
	public ExtentTest test;
	public String repName;
	
	public void onStart(ITestContext context) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		String timeStamp = sm.format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";

		reporter = new ExtentSparkReporter(".\\reports\\"+repName);

		reporter.config().setDocumentTitle("Opencart Automation Report");
		reporter.config().setReportName("Opencart Functional Testing");
		reporter.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(reporter);

		report.setSystemInfo("Application", "OpenCart");
		report.setSystemInfo("Module", "Admin");
		report.setSystemInfo("sub Module", "Customer");
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		report.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			report.setSystemInfo("Groups", includedGroups.toString());
		}

	}


	public void onTestSuccess(ITestResult result) {
		// not implemented
		
		test = report.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
		test =report.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			
		 String imgPath= new BaseClass().captureSceenshot(result.getName());
		 test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
	
		test = report.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}

	public void onFinish(ITestContext context) {
		
		report.flush();
	}

}
