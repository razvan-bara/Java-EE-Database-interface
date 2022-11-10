package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	String dbURL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	String username = "razvan_bara";
	String password =  "r";
	
	protected Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(dbURL,username,password);

		} catch(SQLException e) {
			System.out.println("Eroare de conexiune");
			e.printStackTrace();
		}
		return conn;
		
	}
	
}
