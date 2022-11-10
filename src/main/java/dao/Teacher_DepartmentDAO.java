package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Department;
import models.Teacher;
import models.Teacher_Department;

public class Teacher_DepartmentDAO extends DAO {

	private String SELECT_ALL_TEACHER_DEPARTMENT_ROLES 
	= "SELECT id_profesor, id_catedra, nume, prenume, denumire, pozitie FROM FUNCTII_PROFESOR_CATEDRA INNER JOIN PROFESORI USING(id_profesor) INNER JOIN CATEDRA USING(id_catedra)";
	private String FIND_TEACHER_DEPARTMENT_BY_IDS
	= "SELECT id_profesor, id_catedra, nume, prenume, denumire, pozitie FROM FUNCTII_PROFESOR_CATEDRA INNER JOIN PROFESORI USING(id_profesor) INNER JOIN CATEDRA USING(id_catedra) where id_profesor = ? AND id_catedra = ?";
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

}
