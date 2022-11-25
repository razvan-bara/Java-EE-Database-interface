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

	public String processNewTeacher_Department(Long teacher_id, Long department_id, String position) {
		String error_msg = teacher_departmentDAO.insertNewTeacher_Department(teacher_id, department_id, position);
		return error_msg;
	}

	public String processTeacher_DepartmentUpdate(Teacher_Department existing_teacher_department, Teacher_Department teacher_department) {
		String error_msg = teacher_departmentDAO.updateTeacher_Department(existing_teacher_department, teacher_department);
		return error_msg;
	}
	
	public void processTeacher_DepartmentDelete(long teacher_id, long department_id) {
		teacher_departmentDAO.deleteTeacher_Department(teacher_id, department_id);
	}
}
