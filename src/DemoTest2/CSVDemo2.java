package DemoTest2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVDemo2 {

	public static void main(String[] args) throws IOException {
		String file = "c:/bornInTaoyuan.csv";
		BufferedReader reader = null;
		String line = "";
		
		 try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				
				for(String index: row) {
					System.out.println(index);
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally {
			reader.close();
		}

	
	}
}
