package DemoTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Born {
	private Connection conn;

	public void createConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=born;trustServerCertificate=true";
		this.conn = DriverManager.getConnection(url, "sa", "5tgbnmlp");
		boolean status = !conn.isClosed();
		if (status) {
			System.out.println("連線開啟");
		}
	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}

	public void printAll() throws SQLException {
		String sql = "select * from bornInTaoyuan";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);

		while (rs.next()) {
			System.out.println("地區別:" + rs.getString(1) + " " + "性別:" + rs.getString(2) 
			+ " " + "人數:" + rs.getInt(3));
		}
	}

	public static void main(String[] args) {
		Born born = new Born();

		try {
			born.createConnection();
			born.printAll();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				born.closeConnection();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
