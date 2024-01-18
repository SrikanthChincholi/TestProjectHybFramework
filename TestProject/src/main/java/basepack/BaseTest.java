package basepack;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.Browsers;
import utils.ExtentReportConfig;
import utils.LogHelper;
import utils.PropertyReader;

public class BaseTest {

	protected WebDriver d;
	public static ExtentTest test;

	static Logger log;

	@BeforeSuite
	public void bsuite() {
		ExtentReportConfig.reportConfig();
		log = LogHelper.getLogger(BaseTest.class);
	}

	@BeforeTest
	public void btest(ITestContext context) {
		// test = ExtentReportConfig.extentTest(context);
	}

	@Parameters({ "browsername" })
	@BeforeMethod
	public void setUpBrowser(@Optional("Chrome") String browsername, Method method) throws Exception {
		test = ExtentReportConfig.extentTest(method);
		if (browsername.equals(Browsers.Chrome.name())) {
			d = new ChromeDriver();
			test.info("Chrome Browser loaded");
			log.info("Chrome Browser loaded");
			d.manage().window().maximize();
			test.info("Browser maximized");
			log.info("Browser maximized");
			d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			d.get(PropertyReader.getpropValue("url"));
			test.info("URL Entered !1");
			log.info("URL Entered !1");
		} else if (browsername.equals(Browsers.Firefox.name())) {
			d = new FirefoxDriver();
			test.info("Firefox Browser loaded");
			log.info("Firefox Browser loaded");
			d.manage().window().maximize();
			test.info("Browser maximized");
			log.info("Browser maximized");
			d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			d.get(PropertyReader.getpropValue("url"));
			test.info("URL Entered !1");
			log.info("URL Entered !1");
		} else if (browsername.equals(Browsers.Edge.name())) {
			d = new EdgeDriver();
			d.manage().window().maximize();
			d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			d.get(PropertyReader.getpropValue("url"));
		} else {
			throw new Exception("No such browser found !!");
		}
	}

	@AfterMethod
	public void ameth(Method m, ITestResult res) throws Exception {
		if (res.isSuccess()) {
			test.pass(m.getName() + " -- PASSED");
			test.log(Status.PASS, MarkupHelper.createLabel(m.getName(), ExtentColor.GREEN));
		} else if (res.getStatus() == ITestResult.FAILURE) {
			test.fail(test.addScreenCaptureFromPath(getDate()) + m.getName() + " -- FAILED " + "<pre>"
					+ res.getThrowable() + "</pre>");
			test.log(Status.FAIL, MarkupHelper.createLabel(m.getName(), ExtentColor.RED));
		} else if (res.getStatus() == ITestResult.SKIP) {
			test.skip(m.getName() + " -- SKIPPED ");
			test.log(Status.SKIP, MarkupHelper.createLabel(m.getName(), ExtentColor.AMBER));
		}
		d.quit();
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		ExtentReportConfig.reporter.flush();
	}

	public String getScreen() throws Exception {
		File srcfile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		File destfile = new File(System.getProperty("user.dir") + "//Screenshots//" + getDate() + ".png");
		FileUtils.copyFile(srcfile, destfile);
		return destfile.getAbsolutePath();
	}

	private String getDate() {
		LocalDateTime cal = LocalDateTime.now();
		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd_mm_yyyy_hh_mm_dd");
		return dateformat.format(cal);
	}
}
