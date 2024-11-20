package com.self.hospital_management_system.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class Hospital_Connection {

	public static Connection get_Connection() {
		
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
}
