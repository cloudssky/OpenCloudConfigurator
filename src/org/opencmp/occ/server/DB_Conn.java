package org.opencmp.occ.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.Window;

/**
 * Diese Klasse ermoeglicht Anbindung an mySQL-DB und Speicherung(Insert-Method)
 * von Daten in DB. This class enables connection to mySQL DB and storage
 * (Insertion Method) data in DB.
 * 
 * @author Bagautdinov
 * @version 1.0
 */
public class DB_Conn{

	Properties pr;
	String dbUrl; 
	String dbClass;
	String tableName;
	String dbLogin;
	String dbPWD;
	
	public String connectToDB(String id, String cpuSize, String ramSize,
			String hddSize, String priceSize, String cloudLocation,
			String cloudApps, String name, String lastName, String firmaName,
			String email, String password, String street, String house,
			String post, String city, String methodName)
			throws IllegalArgumentException {
		Connection con = null;
		readDBData();//alle Daten fuer DB-Verbindung auslesen
		if (methodName.equals("Insert")) {
			try {
				Class.forName(dbClass).newInstance();
				con = DriverManager.getConnection(dbUrl, dbLogin, dbPWD);
				Statement stmt = con.createStatement();
				String query = "insert into " + tableName + " (id, cpusize, ramsize, hddsize, pricesize, cloudlocation, cloudapps, name, lastname, firmaname, email, password, street, house, post, city) values ("
						+ "'" + id + "'"
						+ ",'" + cpuSize + "'"
						+ ",'" + ramSize + "'"
						+ ",'" + hddSize + "'"
						+ ",'" + priceSize + "'"
						+ ",'" + cloudLocation + "'"
						+ ",'" + cloudApps + "'"
						+ ",'" + name + "'"
						+ ",'" + lastName + "'"
						+ ",'" + firmaName + "'"
						+ ",'" + email + "'"
						+ ",'" + password + "'"
						+ ",'" + street + "'"
						+ ",'" + house + "'"
						+ ",'" + post + "'"
						+ ",'" + city + "')";
				// System.out.println("my query  "+ query);
				/*int i =*/ stmt.executeUpdate(query);
				con.close();
				Log.info("new user was save in DB");
				return "success";
			} // end try
			catch (Exception e) {
				e.printStackTrace();
				Log.error("new user can not to be saved in DB", e);
			}
		} else if (methodName.equals("Delete")) {
			try {
				Class.forName(dbClass).newInstance();
				con = DriverManager.getConnection(dbUrl, dbLogin, dbPWD);
				Statement stmt = con.createStatement();
				String query = "delete from test_table where id =" + id;
				/* int i = */stmt.executeUpdate(query);
				con.close();
				return "success";
			} // end try
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (methodName.equals("Update")) {
			try {
				Class.forName(dbClass).newInstance();
				con = DriverManager.getConnection(dbUrl, dbLogin, dbPWD);
				Statement stmt = con.createStatement();
				String query = "update test_table set name = ‘" + name
						+ "‘ where id =" + id;
				/* int i = */stmt.executeUpdate(query);
				con.close();
				return "success";
			} // end try
			catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		return "failure";
	}
	
	/**
	 * Diese Methode liest alle notwendige Daten fuer DB-Verbindung(dbUrl; dbClass; tableName; dbLogin; dbPWD) aus db-connect.property-Datei. 
	 */
	private void readDBData(){
		try {
			pr = new Properties();
			pr.load(new FileInputStream("D:/db-connect.properties"));//for local version
//			pr.load(new FileInputStream("/var/lib/tomcat6/webapps/OpenCloudConfigurator/WEB-INF/properties/db-connect.properties"));
			dbUrl = pr.getProperty("dbUrl");
			dbClass  = pr.getProperty("dbClass");
			tableName = pr.getProperty("tableName");
			dbLogin = pr.getProperty("dbLogin");
			dbPWD = pr.getProperty("dbPWD");
			Log.info("reading  a data from db-connect.properties was successful");
		} catch (FileNotFoundException e) {
			Window.alert("db-connect .properties file not found.");
			e.printStackTrace();
			Log.error("db-connect .properties file not found.", e);
		} catch (IOException e) {
			Window.alert("Problem with readDBData method");
			e.printStackTrace();
			Log.error("Problem with readDBData method", e);
		} 
	}

}
