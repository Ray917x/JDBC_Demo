package com.ispan.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo1Connection {

	public static void main(String[] args) {
		try {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;trustServerCertificate=true";

			Connection conn = DriverManager.getConnection(url, "sa", "5tgbnmlp");

			boolean status = !conn.isClosed();

			System.out.println("Status: " + status);
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
