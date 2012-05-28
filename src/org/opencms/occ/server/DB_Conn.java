package org.opencms.occ.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * Diese Klasse ermoeglicht Anbindung an mySQL-DB und Speicherung(Insert-Method) von Daten in DB. 
 * This class enables connection to mySQL DB and storage (Insertion Method) data in DB.
 * @author Bagautdinov
 * @version 1.0
 */
public class DB_Conn {
	
	String dbUrl = "jdbc:mysql://localhost:3306/cloudservice";
	String dbClass = "com.mysql.jdbc.Driver";

	public String connectToDB(String id, String cpuSize, String name, String lastName, String firmaName, String email, String password, String street, String house, String post, String city, String methodName)
			throws IllegalArgumentException {
		Connection con = null;
		
		if (methodName.equals("Insert")) {
			try {
				Class.forName(dbClass).newInstance();
				con = DriverManager.getConnection(dbUrl, "root", "root");
				System.out.println("my connection  " + con + " ende!!!");
				Statement stmt = con.createStatement();
				System.out.println("cpuSize in DB_Conn: " + cpuSize);
				String query = "insert into registration (id, cloudvalue, name, lastname, firmaname, email, password, street, house, post, city) values ("
						+"'"+ id + "'" + ",'" + cpuSize + "'" + ",'" + name + "'" + ",'" + lastName + "'" + ",'"+ "'" + ",'" + firmaName + "'" + ",'" + email + "'" 
						+ ",'" + password + "'" + ",'" + street + "'" + ",'" + house + "'" + ",'" + post + "'" + ",'" + city + "')";
//				System.out.println("my query  "+ query);
				/*int i = */stmt.executeUpdate(query);
				con.close();
				return "success";
			} // end try
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (methodName.equals("Delete")) {
			try {
				Class.forName(dbClass).newInstance();
				con = DriverManager.getConnection(dbUrl, "root", "root");
				Statement stmt = con.createStatement();
				String query = "delete from test_table where id =" + id;
				/*int i = */stmt.executeUpdate(query);
				con.close();
				return "success";
			} // end try
			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (methodName.equals("Update")) {
			try {
				Class.forName(dbClass).newInstance();
				con = DriverManager.getConnection(dbUrl, "root", "root");
				Statement stmt = con.createStatement();
				String query = "update test_table set name = ‘" + name
						+ "‘ where id =" + id;
				/*int i = */stmt.executeUpdate(query);
				con.close();
				return "success";
			} // end try
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "failure";
	}

}
