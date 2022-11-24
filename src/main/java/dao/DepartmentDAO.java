package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Department;

public class DepartmentDAO extends DAO {

	private String SELECT_ALL_DEPARTMENTS = "SELECT * FROM CATEDRA ORDER BY id_catedra asc";
	private String SELECT_ALL_DEPARTMENTS_WHERE_NOT = "SELECT * FROM CATEDRA WHERE id_catedra <> ? ORDER BY id_catedra asc";
	private String INSERT_NEW_DEPARTMENT= "INSERT INTO CATEDRA(denumire) VALUES(?)";
	private String FIND_DEPARTMENT_BY_ID = "SELECT * FROM CATEDRA WHERE id_catedra = ?";
	private String UPDATE_DEPARTMENT = "UPDATE CATEDRA SET denumire = ? WHERE id_catedra = ?";
	private String DELETE_DEPARTMENT = "DELETE FROM CATEDRA WHERE id_catedra = ?";
	
	public ArrayList<Department> getAllDepartments() {
		ArrayList<Department> departments = new ArrayList<Department>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_DEPARTMENTS);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				departments.add(new Department(
					rs.getLong("id_catedra"),
					rs.getString("denumire")
					)
				);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departments;
	}
	
	public void insertNewDepartment(Department department) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_NEW_DEPARTMENT);
			statement.setString(1, department.getDenumire());	
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Department getDepartment(long id) {
		Department department = new Department();
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_DEPARTMENT_BY_ID);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				department = new Department(
						id,
						rs.getString("denumire")
						);				
			}
			conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return department;
	}
	
	public void updateDepartment(Department department) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_DEPARTMENT);
			statement.setString(1, department.getDenumire());
			statement.setLong(2, department.getId());
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDepartment(long id) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_DEPARTMENT);
			statement.setLong(1, id);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Department> serveAllDepartmentsExcept(long department_id) {
		ArrayList<Department> departments = new ArrayList<Department>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_DEPARTMENTS_WHERE_NOT);
			statement.setLong(1, department_id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				departments.add(new Department(
					rs.getLong("id_catedra"),
					rs.getString("denumire")
					)
				);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return departments;
	}

	
}
