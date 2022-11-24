package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Teacher;

public class TeacherDAO extends DAO {

	private String SELECT_ALL_TEACHERS = "SELECT * FROM PROFESORI ORDER BY id_profesor asc";
	private String SELECT_ALL_TEACHERS_WHERE_NOT = "SELECT * FROM PROFESORI WHERE id_profesor <> ? ORDER BY id_profesor asc ";
	private String INSERT_NEW_TEACHER= "INSERT INTO PROFESORI(nume,prenume,adresa) VALUES(?,?,?)";
	private String FIND_TEACHER_BY_ID = "SELECT * FROM PROFESORI WHERE id_profesor = ?";
	private String UPDATE_TEACHER = "UPDATE PROFESORI SET nume = ?, prenume = ?, adresa = ? WHERE id_profesor = ?";
	private String DELETE_TEACHER = "DELETE FROM PROFESORI WHERE id_profesor = ?";
	
	public ArrayList<Teacher> getAllTeachers() {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_TEACHERS);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				teachers.add(new Teacher(
					rs.getLong("id_profesor"),
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
		
		return teachers;
	}
	
	public void insertNewTeacher(Teacher teacher) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_NEW_TEACHER);
			statement.setString(1, teacher.getNume());
			statement.setString(2, teacher.getPrenume());
			statement.setString(3, teacher.getAdresa());	
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Teacher getTeacher(long id) {
		Teacher teacher = new Teacher();
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_TEACHER_BY_ID);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				teacher = new Teacher(
						id,
						rs.getString("nume"),
						rs.getString("prenume"),
						rs.getString("adresa")
						);				
			}
			conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}
	
	public void updateTeacher(Teacher teacher) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_TEACHER);
			statement.setString(1, teacher.getNume());
			statement.setString(2, teacher.getPrenume());
			statement.setString(3, teacher.getAdresa());
			statement.setLong(4, teacher.getId());
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTeacher(long id) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_TEACHER);
			statement.setLong(1, id);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Teacher> getAllTeachersExcept(long teacher_id) {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_TEACHERS_WHERE_NOT);
			statement.setLong(1, teacher_id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				teachers.add(new Teacher(
					rs.getLong("id_profesor"),
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
		
		return teachers;
	}

	
}
