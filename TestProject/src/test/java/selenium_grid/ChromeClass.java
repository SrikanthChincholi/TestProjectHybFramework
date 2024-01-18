package selenium_grid;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChromeClass extends GridConfig {

	@Test
	public void chromeTest() throws MalformedURLException {

		rd.get().manage().window().maximize();
		rd.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		rd.get().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		rd.get().findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		rd.get().findElement(By.xpath("//input[@name='password']")).sendKeys("admin123", Keys.RETURN);
		List<WebElement> dashboard = rd.get().findElements(By.xpath("//h6[text()='Dashboard']"));
		Assert.assertEquals(dashboard.get(0).getText(), "Dashboard", "Login Success !!");
	}

}
