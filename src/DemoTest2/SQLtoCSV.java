package DemoTest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLtoCSV {
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


//public class SQLtoCSV{
//
//    private static final String demo_file = "c:/HWdata/bornInTaoyuan.csv";
//
//    public static void main(String[] args) {
//        BufferedReader file_read = null;
//        try {
//            // Open the file to read the content
//            file_read = new BufferedReader(new FileReader(new File(demo_file)));
//
//            // Reading the file content
//            String file_line = null;
//            while((file_line = file_read.readLine()) != null)
//                System.out.println(file_line);
//        }
//        catch(IOException e) {
//            System.err.println("FileNotFoundException was caught!");
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                file_read.close();
//            }
//            catch (IOException e) {
//                System.err.println("FileNotFoundException was caught!");
//                e.printStackTrace();
//            }
//        }
//    }
//}
	
	public void outputcsv() throws IOException, SQLException {

		FileWriter fw = new FileWriter("c:/HWdata/outbornInTaoyuan.csv");

		String sql = "select * from bornInTaoyuan";
		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();

		int cols = rs.getMetaData().getColumnCount();

		for (int i = 1; i <= cols; i++) {
			fw.append(rs.getMetaData().getColumnLabel(i));
			if (i < cols)
				fw.append(',');
			else
				fw.append('\n');
		}

		while (rs.next()) {

			for (int i = 1; i <= cols; i++) {
				fw.append(rs.getString(i));
				if (i < cols)
					fw.append(',');
			}
			fw.append('\n');
		}
		fw.flush();
		fw.close();
	}

	public static void main(String[] args) {
		SQLtoCSV demo2 = new SQLtoCSV();

		try {
			demo2.createConnection();
			demo2.outputcsv();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				demo2.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}