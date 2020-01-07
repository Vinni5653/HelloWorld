package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	private static Connection conn=null;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		return conn;
	}
		
}
