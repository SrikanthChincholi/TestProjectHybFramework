package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This Class will establish db connections and fetch data from database
 * 
 * @author DELL variables : ulr,uname,pswd
 * 
 */

public class DBConfig {
	
	@DataProvider(name="DP")
	public static Iterator<EmployeeDetails> getDBdetailUsingDP() throws Exception {
		final String url = "jdbc:mysql:///Employee";
		final String user = "root";
		final String pswd = "Srikanth@kk42";

		List<EmployeeDetails> alldetails = new ArrayList<>();

		// Register the class
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pswd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from employee");
		while (rs.next()) {
			EmployeeDetails empdetails = new EmployeeDetails();
			empdetails.setEname(rs.getString("username"));
			empdetails.setPswd(rs.getString("epassword"));
			alldetails.add(empdetails);
		}
		return alldetails.iterator();
	}

	public static List<EmployeeDetails> getDBdetails() throws Exception {
		final String url = "jdbc:mysql:///Employee";
		final String user = "root";
		final String pswd = "Srikanth@kk42";

		List<EmployeeDetails> alldetails = new ArrayList<>();

		// Register the class
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pswd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from employee");
		while (rs.next()) {
			EmployeeDetails empdetails = new EmployeeDetails();
			empdetails.setEname(rs.getString("username"));
			empdetails.setPswd(rs.getString("epassword"));
			alldetails.add(empdetails);
		}
		return alldetails;
	}

	

	/*
	 * @Test public void getDBData() throws Exception { List<EmployeeDetails>
	 * alldetails = getDBdetails(); for (EmployeeDetails det : alldetails) {
	 * System.out.println(det.getEname()); System.out.println(det.getPswd()); } }
	 */
	
}
