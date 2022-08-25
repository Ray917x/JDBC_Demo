package DemoTest2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class csvInSQL {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=InJDBC;trustServerCertificate=true";
			Connection conn = DriverManager.getConnection(url, "sa", "5tgbnmlp");

			FileReader fr = new FileReader("c:/bornInTaoyuan.csv");
			BufferedReader brdFile = new BufferedReader(fr);
			String strLine = null;

			brdFile.readLine(); // 跳過第一行(欄位)，從第二行寫入

			while ((strLine = brdFile.readLine()) != null) {
				String[] array = strLine.split(",");

				for (int i = 0; i < array.length; i++) {
					System.out.println(array[i]);
				}
				String qryInsert = "insert into jdbc values(?,?,?)";

				PreparedStatement preState = conn.prepareStatement(qryInsert);

				preState.setString(1, array[0]);
				preState.setString(2, array[1]);
				preState.setInt(3, Integer.valueOf(array[2]));

				preState.execute();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}