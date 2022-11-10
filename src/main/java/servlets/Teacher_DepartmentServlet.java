package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Teacher_Department;
import models.Teacher_Department;
import service.Teacher_DepartmentService;

@WebServlet("/functii/*")
public class Teacher_DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Teacher_DepartmentService teacher_departmentService;
	
    public Teacher_DepartmentServlet() {
    	teacher_departmentService = new Teacher_DepartmentService();
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
//			case "delete":  deleteTeacher_Department(request, response);
//							break;
			default: listTeacher_Departments(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void showNewTeacher_DepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		request.setAttribute("teacher_department", existing_teacher_department);
		getServletContext().getRequestDispatcher("/pages/teachers_departments/teacherDepartmentForm.jsp").forward(request, response);
	}
	
	protected void listTeacher_Departments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Teacher_Department> teachers_departments = teacher_departmentService.serveAllTeachers_Departments();
		
		request.setAttribute("teachers_departments", teachers_departments);
		getServletContext().getRequestDispatcher("/pages/teachers_departments/index.jsp").forward(request, response);
	}
}
