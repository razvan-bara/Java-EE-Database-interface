package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	String dbURL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	String username = "razvan_bara";
	String password =  "r";
	Connection conn = null;
	
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
	
	protected void getCtasonnection( ) {
		try {
			Connection conn = DriverManager.getConnection(dbURL,username,password);
			String sql = "SELECT * FROM STUDENTI";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				
				sb.append(rs.getString("nume") + " ");
				sb.append(rs.getString("prenume"));
				
				System.out.println(sb.toString());
				
				sb.setLength(0);
			}
			rs.close();
			s.close();
			
			
		} catch(SQLException e) {
			System.out.println("Eroare lol");
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				try { 
					conn.close();
				} catch(Exception e) {
						
				}
				
			}
		}
	}
}
