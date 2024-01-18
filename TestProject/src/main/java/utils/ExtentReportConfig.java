package utils;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Platform;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportConfig {

	public static ExtentSparkReporter htmlreport;
	public static ExtentReports reporter;
	public static ExtentTest test;

	public static void reportConfig() {
		htmlreport = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//Reports//" + getdata() + "_report.html");
		reporter = new ExtentReports();
		reporter.attachReporter(htmlreport);

		htmlreport.config().setDocumentTitle("Automation Test Report");
		htmlreport.config().setReportName("HRM Test Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setTimeStampFormat("MM:DD:YY:hh:mm:s");

		reporter.setSystemInfo("Machine", "Test Machine");
		reporter.setSystemInfo("OS", Platform.WIN10.toString());
		reporter.setSystemInfo("user", "Srikanth");
		reporter.setSystemInfo("Browser", "chrome");

	}

	public static ExtentTest extentTest(ITestContext context) {
		if (test == null) {
			return ExtentReportConfig.reporter.createTest(context.getName());
		} else {
			return test;
		}

	}

	public static ExtentTest extentTest(Method method) {
		if (test == null) {
			return ExtentReportConfig.reporter.createTest(method.getName());
		} else {
			return test;
		}

	}

	public static String getdata() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
		return myDateObj.format(myFormatObj);

	}

}
