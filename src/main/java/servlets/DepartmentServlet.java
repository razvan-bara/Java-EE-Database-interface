package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Department;
import service.DepartmentService;
import utils.SessionMessage;

@WebServlet("/catedre/*")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DepartmentService departmentService;
	
    public DepartmentServlet() {
    	departmentService = new DepartmentService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		if(request.getPathInfo() != null) {
			action = request.getPathInfo().substring(1);
		}
		System.out.println(action);
		
		switch(action) {
			case "new": 	showNewDepartmentForm(request, response);
							break;
			case "edit": 	showEditDepartmentForm(request, response);
							break;
			case "delete":  deleteDepartment(request, response);
							break;
			default: listDepartments(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("_METHOD") != null &&  request.getParameter("_METHOD").equalsIgnoreCase("PUT") ) {
			submitEditDepartmentForm(request, response);
		} else {
			submitNewDepartmentForm(request, response);
		}

	}

	protected void showNewDepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/pages/departments/departmentForm.jsp").forward(request, response);
	}
	
	
	protected void showEditDepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listDepartments(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		Department existing_department = departmentService.retriveDepartment(id);
		
		request.setAttribute("department", existing_department);
		getServletContext().getRequestDispatcher("/pages/departments/departmentForm.jsp").forward(request, response);
	}
	
	protected void submitNewDepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String denumire = request.getParameter("denumire");
		
		Department department = new Department(denumire);
		departmentService.processNewDepartment(department);
		SessionMessage.setSuccessMsg(request, "Catedra creeata cu succes");
		response.sendRedirect("/catedre");
	}
	
	protected void submitEditDepartmentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong( request.getParameter("id") );
		String denumire = request.getParameter("denumire");

		Department department = new Department(id,denumire);
		departmentService.processDepartmentUpdate(department);
		SessionMessage.setSuccessMsg(request, "Catedra editata cu succes");
		response.sendRedirect("/catedre");
	}
	
	protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listDepartments(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		departmentService.processDepartmentDelete(id);
		SessionMessage.setSuccessMsg(request, "Catedra stearsa cu succes");
		response.sendRedirect("/catedre");
	}
	
	protected void listDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Department> departments = departmentService.serveAllDepartments();
		
		request.setAttribute("departments", departments);
		getServletContext().getRequestDispatcher("/pages/departments/index.jsp").forward(request, response);
	}

}
