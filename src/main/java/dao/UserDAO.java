package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.User;

public class UserDAO extends DAO {

	private String INSERT_NEW_USER= "INSERT INTO UTILIZATORI(nume_complet,email,parola) VALUES(?,?,?)";
	
	public void insertNewUser(User user) {
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_NEW_USER);
			statement.setString(1, user.getNume_complet());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getParola());	
			
			//by default rol client 
			
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
