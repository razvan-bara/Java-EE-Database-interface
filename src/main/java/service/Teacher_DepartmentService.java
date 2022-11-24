package service;

import java.util.ArrayList;

import dao.Teacher_DepartmentDAO;
import models.Teacher_Department;


public class Teacher_DepartmentService {

	
	private Teacher_DepartmentDAO teacher_departmentDAO;
	
	public Teacher_DepartmentService(){
		teacher_departmentDAO = new Teacher_DepartmentDAO();
	}
	
	public ArrayList<Teacher_Department> serveAllTeachers_Departments(){
		return teacher_departmentDAO.getAllTeachersDepartments();
	}
	
	public Teacher_Department retriveTeacher_Department(long teacher_id, long department_id) {
		Teacher_Department existing_teacher_department = teacher_departmentDAO.getTeacher_Department(teacher_id, department_id);
		return existing_teacher_department;
	}

	public void processNewTeacher_Department(Long teacher_id, Long department_id, String position) {
		teacher_departmentDAO.insertNewTeacher_Department(teacher_id, department_id, position);
		
	}
}
