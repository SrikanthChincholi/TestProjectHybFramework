package dbconfig;

import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelUtility;

/**
 * This class will have all dataproviders
 * @author DELL
 *
 */
public class DataProviderClass {
	
	@DataProvider(name = "DP")
	public Iterator<EmployeeDetails> getData() throws Exception
	{
		return DBConfig.getDBdetailUsingDP();
		
	}
	
	@DataProvider(name = "getExcelData")
	public Object[][] readdata() throws Exception {
		return ExcelUtility.getExcelData();
	}

}
