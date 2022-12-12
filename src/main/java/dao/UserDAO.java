package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import models.User;

public class UserDAO extends DAO {

	private String ATTEMPT_LOGIN_USER = "SELECT * FROM UTILIZATORI WHERE email = ? AND parola = ?";
	private String SELECT_ALL_USERS_ACTIVE_OR_PENDING = "SELECT * FROM UTILIZATORI ORDER BY rol asc";
	private String SELECT_ALL_PENDING_USERS = "SELECT * FROM UTILIZATORI ORDER BY id_utilizator asc";
	private String INSERT_NEW_USER= "INSERT INTO UTILIZATORI(nume_complet,email,parola) VALUES(?,?,?)";
	private String FIND_USER_BY_ID = "SELECT * FROM UTILIZATORI WHERE id_utilizator = ?";
	private String UPDATE_USER = "UPDATE UTILIZATORI SET nume_complet = ?, email = ?, rol = ? WHERE id_utilizator = ?";
	private String DELETE_USER = "DELETE FROM UTILIZATORI WHERE id_utilizator = ?";
	
	public ArrayList<User> getAllActiveUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_USERS_ACTIVE_OR_PENDING);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				users.add(new User(
					rs.getLong("id_utilizator"),
					rs.getString("nume_complet"),
					rs.getString("email"),
					rs.getString("parola"),
					rs.getString("rol")
					)
				);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public ArrayList<User> getAllPendingUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_USERS_ACTIVE_OR_PENDING);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				users.add(new User(
					rs.getLong("id_utilizator"),
					rs.getString("nume"),
					rs.getString("prenume"),
					rs.getString("adresa")
					)
				);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public String insertNewUser(User user) {
		
		String error_msg = "";
		
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
		}catch(SQLIntegrityConstraintViolationException e) {
			error_msg = "Email deja inregistrat";
		}
		catch(Exception e) {
			e.printStackTrace();
			error_msg= "Problema de conexiune";
		}
		
		return error_msg;
		
	}
	
	public User getUser(long id) {
		User user = new User();
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_USER_BY_ID);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				user = new User(
						id,
						rs.getString("nume_complet"),
						rs.getString("email"),
						rs.getString("parola"),
						rs.getString("rol")
						);				
			}
			conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void updateUser(User user) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_USER);
			statement.setString(1, user.getNume_complet());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getRol());
			statement.setLong(4, user.getId_utilizator());
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(long id) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_USER);
			statement.setLong(1, id);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public User loginUser(String email, String password) {
		
		User user = null;
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(ATTEMPT_LOGIN_USER);
			
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
			
				user = new User(
					rs.getLong("id_utilizator"),
					rs.getString("nume_complet"),
					rs.getString("email"),
					rs.getString("rol")
				);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
