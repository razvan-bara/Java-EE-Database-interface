package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Department;
import models.Student;
import models.Teacher;
import models.Teacher_Department;
import models.Teacher_Department;
import service.DepartmentService;
import service.TeacherService;
import service.Teacher_DepartmentService;
import utils.SessionMessage;

@WebServlet("/functii/*")
public class Teacher_DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Teacher_DepartmentService teacher_departmentService;
	private TeacherService teacherService;
	private DepartmentService departmentService;
	private ArrayList<String> positions = new ArrayList<String>();
	
    public Teacher_DepartmentServlet() {
    	
    	teacher_departmentService = new Teacher_DepartmentService();
    	teacherService = new TeacherService();
    	departmentService = new DepartmentService();
    	
    	positions.add("Sef");
    	positions.add("Editor");
    	positions.add("Ceva");
    	positions.add("Prodecan");
    }
    
    private ArrayList<String> filterCurrentPosition(String position){
    	
    	ArrayList<String> filtered_positions = new ArrayList<String>();
    	
    	for (String pos : positions){
    		
    		if( !pos.equalsIgnoreCase(position) ) {
    			filtered_positions.add(pos);
    		}
    		
    	}
    	
		return filtered_positions;
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		if(request.getPathInfo() != null) {
			action = request.getPathInfo().substring(1);
		}
		System.out.println(action);
		
		switch(action) {
			case "new": 	showNewTeacher_DepartmentForm(request, response);
							break;
			case "edit": 	showEditTeacher_DepartmentForm(request, response);
							break;
			case "delete":  deleteTeacher_Department(request, response);
							break;
			default: listTeacher_Departments(request,response);
		}
		
	}

	protected void submitNewTeacher_DepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long teacher_id = Long.parseLong( request.getParameter("teacher") );
		Long department_id = Long.parseLong( request.getParameter("department") );
		String position = request.getParameter("position");

		System.out.println(teacher_id);
		System.out.println(department_id);
		System.out.println(position);
		
		String error_msg = teacher_departmentService.processNewTeacher_Department(teacher_id, department_id, position);
		
		if(error_msg.isBlank()) {
			SessionMessage.setSuccessMsg(request, "Profesor adaugat la catedra cu succes");
		} else {
			SessionMessage.setErrorMsg(request, error_msg);
		}
		
		response.sendRedirect("/functii");
	}
	
	protected void deleteTeacher_Department(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if( request.getParameter("teacher_id") == null || request.getParameter("department_id") == null ) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listTeacher_Departments(request,response);
		}
		
		long teacher_id = Long.parseLong( request.getParameter("teacher_id") );
		long department_id = Long.parseLong( request.getParameter("department_id") );
		
		teacher_departmentService.processTeacher_DepartmentDelete(teacher_id, department_id);
		SessionMessage.setSuccessMsg(request, "Relatie profesor-catedra stearsa");
		
		response.sendRedirect("/functii");
	}
	
	protected void submitEditTeacher_DepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long existing_teacher_id = Long.parseLong( request.getParameter("existing_teacher_id") );
		Long existing_department_id = Long.parseLong( request.getParameter("existing_department_id") );
		String existing_position = request.getParameter("existing_position");
		
		Long teacher_id = Long.parseLong( request.getParameter("teacher") );
		Long department_id = Long.parseLong( request.getParameter("department") );
		String position = request.getParameter("position");
		
		Teacher_Department existing_teacher_department = new Teacher_Department(existing_teacher_id, existing_department_id);
		Teacher_Department teacher_department = new Teacher_Department(teacher_id, department_id, position);
		
		String error_msg = teacher_departmentService.processTeacher_DepartmentUpdate(existing_teacher_department, teacher_department);
		
		if(error_msg.isBlank()) {
			SessionMessage.setSuccessMsg(request, "Profesor adaugat la catedra cu succes");
		} else {
			SessionMessage.setErrorMsg(request, error_msg);
		}
		
		response.sendRedirect("/functii");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("_METHOD") != null &&  request.getParameter("_METHOD").equalsIgnoreCase("PUT") ) {
			submitEditTeacher_DepartmentForm(request, response);
		} else {
			submitNewTeacher_DepartmentForm(request, response);
		}
	}

	protected void showNewTeacher_DepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Teacher> teachers = teacherService.serveAllTeachers();
		ArrayList<Department> departments = departmentService.serveAllDepartments();
		
		
		request.setAttribute("teachers", teachers);
		request.setAttribute("departments", departments);
		request.setAttribute("positions", positions);
		
		getServletContext().getRequestDispatcher("/pages/teachers_departments/teacherDepartmentForm.jsp").forward(request, response);
	}
	
	
	protected void showEditTeacher_DepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if( request.getParameter("teacher_id") == null || request.getParameter("department_id") == null ) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listTeacher_Departments(request,response);
		}
		
		long teacher_id = Long.parseLong( request.getParameter("teacher_id") );
		long department_id = Long.parseLong( request.getParameter("department_id") );
		
		Teacher_Department existing_teacher_department = teacher_departmentService.retriveTeacher_Department(teacher_id, department_id);
		
		ArrayList<Teacher> teachers = teacherService.serveAllTeachersExcept(teacher_id);
		ArrayList<Department> departments = departmentService.serveAllDepartmentsExcept(department_id);
		ArrayList<String> filtered_positions = filterCurrentPosition( existing_teacher_department.getPosition() );
		
		request.setAttribute("teachers", teachers);
		request.setAttribute("departments", departments);
		request.setAttribute("positions", filtered_positions);
		request.setAttribute("teacher_department", existing_teacher_department);
		getServletContext().getRequestDispatcher("/pages/teachers_departments/teacherDepartmentForm.jsp").forward(request, response);
	}
	
	protected void listTeacher_Departments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Teacher_Department> teachers_departments = teacher_departmentService.serveAllTeachers_Departments();
		
		request.setAttribute("teachers_departments", teachers_departments);
		getServletContext().getRequestDispatcher("/pages/teachers_departments/index.jsp").forward(request, response);
	}
}
