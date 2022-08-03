package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {

	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(" select * from bankdetail");
			
			while(rs.next()) {
				System.out.println("accountno: "+rs.getString(1));
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

