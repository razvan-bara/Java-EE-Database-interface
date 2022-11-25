package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import models.Department;
import models.Teacher;
import models.Teacher_Department;

public class Teacher_DepartmentDAO extends DAO {

	private String SELECT_ALL_TEACHER_DEPARTMENT_ROLES 
	= "SELECT id_profesor, id_catedra, nume, prenume, denumire, pozitie FROM FUNCTII_PROFESOR_CATEDRA INNER JOIN PROFESORI USING(id_profesor) INNER JOIN CATEDRA USING(id_catedra) ORDER BY nume ASC, prenume ASC";
	private String FIND_TEACHER_DEPARTMENT_BY_IDS
	= "SELECT id_profesor, id_catedra, nume, prenume, denumire, pozitie FROM FUNCTII_PROFESOR_CATEDRA INNER JOIN PROFESORI USING(id_profesor) INNER JOIN CATEDRA USING(id_catedra) where id_profesor = ? AND id_catedra = ?";
	private String INSERT_NEW_TEACHER_DEPARTMENT = "INSERT INTO functii_profesor_catedra VALUES(?,?,?)";
	private String UPDATE_TEACHER_DEPARTMENT 
	= "UPDATE functii_profesor_catedra SET id_profesor = ?, id_catedra = ?, pozitie = ? WHERE id_profesor = ? AND id_catedra = ?";
	private String DELETE_TEACHER_DEPARTMENT = "DELETE FROM FUNCTII_PROFESOR_CATEDRA WHERE id_profesor = ? AND id_catedra = ?";
	
	public ArrayList<Teacher_Department> getAllTeachersDepartments() {
		ArrayList<Teacher_Department> teachers_departments = new ArrayList<Teacher_Department>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_TEACHER_DEPARTMENT_ROLES);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				Teacher teacher = new Teacher(
						rs.getLong("id_profesor"),
						rs.getString("nume"),
						rs.getString("prenume")
						);
				
				Department department = new Department(
						rs.getLong("id_catedra"),
						rs.getString("denumire")
						);
				
				Teacher_Department teacher_department = new Teacher_Department(
						teacher,
						department,
						rs.getString("pozitie")
				);
				
				teachers_departments.add(teacher_department);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teachers_departments;
	}
	
	public Teacher_Department getTeacher_Department(long teacher_id, long department_id) {
		
		Teacher_Department teacher_department = new Teacher_Department();
		
		try {
		
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_TEACHER_DEPARTMENT_BY_IDS);
			
			statement.setLong(1, teacher_id);
			statement.setLong(2, department_id);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				Teacher teacher = new Teacher(
						rs.getLong("id_profesor"),
						rs.getString("nume"),
						rs.getString("prenume")
						);
				
				Department department = new Department(
						rs.getLong("id_catedra"),
						rs.getString("denumire")
						);
				
				teacher_department.setTeacher(teacher);
				teacher_department.setDepartment(department);
				teacher_department.setPosition( rs.getString("pozitie") );
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return teacher_department;
	}

	public String insertNewTeacher_Department(Long teacher_id, Long department_id, String position) {
		
		String error_msg = "";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_NEW_TEACHER_DEPARTMENT);
			
			statement.setLong(1, teacher_id);
			statement.setLong(2, department_id);
			statement.setString(3, position);
			int rows = statement.executeUpdate();
			System.out.println("im here");
			System.out.println(rows);
			conn.close();
			
		}catch(SQLIntegrityConstraintViolationException e){
			System.out.println("create pk nerespectata");
			error_msg = "Profesorul apartine deja ce aceasta catedra";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return error_msg;
		
	}

	public String updateTeacher_Department(Teacher_Department existing_teacher_department, Teacher_Department teacher_department) {
		String error_msg = "";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_TEACHER_DEPARTMENT);
			
			statement.setLong(1, teacher_department.getTeacher().getId());
			statement.setLong(2, teacher_department.getDepartment().getId());
			statement.setString(3, teacher_department.getPosition());
			
			statement.setLong(4, existing_teacher_department.getTeacher().getId());
			statement.setLong(5, existing_teacher_department.getDepartment().getId());

			int rows = statement.executeUpdate();
			System.out.println("im here");
			System.out.println(rows);
			conn.close();
			
		}catch(SQLIntegrityConstraintViolationException e){
			System.out.println("update pk nerespectata");
			error_msg = "Profesorul apartine deja ce aceasta catedra";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return error_msg;
	}
	
	public void deleteTeacher_Department(long teacher_id, long department_id) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_TEACHER_DEPARTMENT);
			statement.setLong(1, teacher_id);
			statement.setLong(2, department_id);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
