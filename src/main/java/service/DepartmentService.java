package service;

import java.util.ArrayList;

import dao.DepartmentDAO;
import models.Department;

public class DepartmentService {

	private DepartmentDAO departmentDAO;
	
	public DepartmentService() {
		departmentDAO = new DepartmentDAO();
	}
	
	public ArrayList<Department> serveAllDepartments(){
		return departmentDAO.getAllDepartments();
	}
	
	public void processNewDepartment(Department department) {
		departmentDAO.insertNewDepartment(department);
	}
	
	public Department retriveDepartment(long id) {
		return departmentDAO.getDepartment(id);
	}
	
	public void processDepartmentUpdate(Department department) {
		departmentDAO.updateDepartment(department);
	}
	
	public void processDepartmentDelete(long id) {
		departmentDAO.deleteDepartment(id);
	}

	public ArrayList<Department> serveAllDepartmentsExcept(long department_id) {
		return departmentDAO.serveAllDepartmentsExcept(department_id);
	}
	
}
