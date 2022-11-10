package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Teacher;
import service.TeacherService;


@WebServlet("/profesori/*")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TeacherService teacherService;

    public TeacherServlet() {
    	teacherService = new TeacherService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		if(request.getPathInfo() != null) {
			action = request.getPathInfo().substring(1);
		}
		System.out.println(action);
		
		switch(action) {
			case "new": 	showNewTeacherForm(request, response);
							break;
			case "edit": 	showEditTeacherForm(request, response);
							break;
			case "delete":  deleteTeacher(request, response);
							break;
			default: listTeachers(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("_METHOD") != null &&  request.getParameter("_METHOD").equalsIgnoreCase("PUT") ) {
			submitEditTeacherForm(request, response);
		} else {
			submitNewTeacherForm(request, response);
		}

	}

	protected void showNewTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/pages/teachers/teacherForm.jsp").forward(request, response);
	}
	
	
	protected void showEditTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listTeachers(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		Teacher existing_teacher = teacherService.retriveTeacher(id);
		
		request.setAttribute("teacher", existing_teacher);
		getServletContext().getRequestDispatcher("/pages/teachers/teacherForm.jsp").forward(request, response);
	}
	
	protected void submitNewTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String adresa = request.getParameter("adresa");
		
		Teacher teacher = new Teacher(nume,prenume,adresa);
		teacherService.processNewTeacher(teacher);
		setSuccessMsg(request, "Profesor creeat cu succes");
		response.sendRedirect("/profesori");
	}
	
	protected void submitEditTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong( request.getParameter("id") );
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String adresa = request.getParameter("adresa");

		Teacher teacher = new Teacher(id,nume,prenume,adresa);
		teacherService.processTeacherUpdate(teacher);
		setSuccessMsg(request, "Profesor editat cu succes");
		response.sendRedirect("/profesori");
	}
	
	protected void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listTeachers(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		teacherService.processTeacherDelete(id);
		setSuccessMsg(request, "Profesor sters cu succes");
		response.sendRedirect("/profesori");
	}
	
	protected void listTeachers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Teacher> teachers = teacherService.serveAllTeachers();
		
		request.setAttribute("teachers", teachers);
		getServletContext().getRequestDispatcher("/pages/teachers/index.jsp").forward(request, response);
	}
	
	protected static void setSuccessMsg(HttpServletRequest request, String msg) {
		HttpSession session = request.getSession(false);
		session.setAttribute("success", msg);
	}

}
