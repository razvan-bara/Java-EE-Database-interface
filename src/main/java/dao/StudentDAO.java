package dao;

import java.sql.*;
import java.util.ArrayList;

import models.Student;

public class StudentDAO extends DAO {
	
	private String SELECT_ALL_STUDENTS = "SELECT * FROM STUDENTI";
	private String INSERT_NEW_STUDENT="INSERT INTO STUDENTI(nume,prenume,adresa) VALUES(?,?,?)";
	
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
}
