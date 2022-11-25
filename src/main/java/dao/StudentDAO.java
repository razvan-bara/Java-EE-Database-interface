package dao;

import java.sql.*;
import java.util.ArrayList;

import models.Student;

public class StudentDAO extends DAO {
	
	private String SELECT_ALL_STUDENTS = "SELECT * FROM STUDENTI ORDER BY id_student asc";
	private String SELECT_ALL_STUDENTS_WHERE_NOT = "SELECT * FROM STUDENTI WHERE id_student <> ? ORDER BY id_student asc ";
	private String INSERT_NEW_STUDENT= "INSERT INTO STUDENTI(nume,prenume,adresa) VALUES(?,?,?)";
	private String FIND_STUDENT_BY_ID = "SELECT * FROM STUDENTI WHERE id_student = ?";
	private String UPDATE_STUDENT = "UPDATE STUDENTI SET nume = ?, prenume = ?, adresa = ? WHERE id_student = ?";
	private String DELETE_STUDENT = "DELETE FROM STUDENTI WHERE id_student = ?";
	
	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_STUDENTS);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				students.add(new Student(
					rs.getLong("id_student"),
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
		
		return students;
	}
	
	public void insertNewStudent(Student student) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_NEW_STUDENT);
			statement.setString(1, student.getNume());
			statement.setString(2, student.getPrenume());
			statement.setString(3, student.getAdresa());	
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Student getStudent(long id) {
		Student student = new Student();
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_STUDENT_BY_ID);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				student = new Student(
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
		return student;
	}
	
	public void updateStudent(Student student) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_STUDENT);
			statement.setString(1, student.getNume());
			statement.setString(2, student.getPrenume());
			statement.setString(3, student.getAdresa());
			statement.setLong(4, student.getId());
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudent(long id) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_STUDENT);
			statement.setLong(1, id);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Student> getAllStudentsExcept(long student_id) {
			ArrayList<Student> students = new ArrayList<Student>();
			
			try {
				Connection conn = this.getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_ALL_STUDENTS_WHERE_NOT);
				statement.setLong(1, student_id);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					
					students.add(new Student(
						rs.getLong("id_student"),
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
			
			return students;
		}
	
}
