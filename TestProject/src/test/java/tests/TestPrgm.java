package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import basepack.BaseTest;
import dbconfig.DBConfig;
import dbconfig.DataProviderClass;
import dbconfig.EmployeeDetails;
import utils.PropertyReader;

public class TestPrgm extends BaseTest {

//	@Test(dataProvider = "DP", dataProviderClass = DataProviderClass.class)
	public void loginTest1(EmployeeDetails data) throws Exception {
		
		//List<EmployeeDetails> alldetails = DBConfig.getDBdetails();
		test.info("identifying username");
		d.findElement(By.xpath("//input[@name='username']")).sendKeys(data.getEname());
		test.info("Entered " + data.getEname());
		test.info("identifying password");
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(data.getPswd(), Keys.RETURN);
		test.info("Entered " + data.getPswd());
		Assert.assertEquals(d.findElement(By.xpath("//h6[text()='Dashboard']")).getText(), "Dashboard",
				"Not Matched !!");

	}
	
	@Test(dataProvider = "getExcelData", dataProviderClass = DataProviderClass.class)
	public void loginTestUsingExcel(String uname,String pswd) throws Exception {
		
		//List<EmployeeDetails> alldetails = DBConfig.getDBdetails();
		test.info("identifying username");
		d.findElement(By.xpath("//input[@name='username']")).sendKeys(uname);
		test.info("Entered " + uname);
		test.info("identifying password");
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(pswd, Keys.RETURN);
		test.info("Entered " + pswd);
		Assert.assertEquals(d.findElement(By.xpath("//h6[text()='Dashboard']")).getText(), "Dashboard",
				"Not Matched !!");

	}

	//@Test
	public void loginTest2() throws Exception {
		List<EmployeeDetails> alldetails = DBConfig.getDBdetails();
		test.info("identifying username");
		d.findElement(By.xpath("//input[@name='username']")).sendKeys(alldetails.get(1).getEname());
		test.info("Entered " + alldetails.get(1).getEname());
		test.info("identifying password");
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(alldetails.get(1).getPswd(), Keys.RETURN);
		test.info("Entered " + alldetails.get(1).getPswd());
		Assert.assertEquals(d.findElement(By.xpath("//h6[text()='Dashboard']")).getText(), "Dashboard",
				"Not Matched !!");

	}

//@Test
	public void loginTest3() throws Exception {
		List<EmployeeDetails> alldetails = DBConfig.getDBdetails();
		test.info("identifying username");
		d.findElement(By.xpath("//input[@name='username']")).sendKeys(alldetails.get(2).getEname());
		test.info("identifying password");
		d.findElement(By.xpath("//input[@name='password']")).sendKeys(alldetails.get(2).getPswd(), Keys.RETURN);
		Assert.assertEquals(d.findElement(By.xpath("//h6[text()='Dashboard']")).getText(), "Dashboard",
				"Not Matched !!");

	}

	/*
	 * @Test public void loginTest2() { test.info("identifying username");
	 * d.findElement(By.xpath("//input[@name='username']")).sendKeys(PropertyReader.
	 * getpropValue("name")); test.info("identifying password");
	 * d.findElement(By.xpath("//input[@name='password']")).sendKeys(PropertyReader.
	 * getpropValue("pswd"), Keys.RETURN); }
	 * 
	 * @Test public void loginTest3() {
	 * 
	 * test.info("identifying username");
	 * d.findElement(By.xpath("//input[@name='username']")).sendKeys(PropertyReader.
	 * getpropValue("name")); test.info("identifying password");
	 * d.findElement(By.xpath("//input[@name='password']")).sendKeys(PropertyReader.
	 * getpropValue("pswd"), Keys.RETURN); throw new
	 * SkipException("This is skip exception"); }
	 */
}
