package selenium_grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class GridConfig {

	DesiredCapabilities cap = new DesiredCapabilities();;
	ThreadLocal<RemoteWebDriver> rd = new ThreadLocal<>();

	@Parameters({"browser"})
	@BeforeMethod
	public void setUpBrowser(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("firefox")) {
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
			rd.set(new RemoteWebDriver(new URL("http://localhost:4444"), cap));
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			rd.set(new RemoteWebDriver(new URL("http://localhost:4444"), cap));
		}
	}

	@AfterMethod
	public void tearDown() {
		rd.get().quit();
	}

	@AfterClass
	public void removeThread() {
		rd.remove();
	}

}
