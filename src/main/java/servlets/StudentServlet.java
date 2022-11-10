package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Student;
import service.StudentService;

@WebServlet(urlPatterns = {"/studenti/*"})
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentService studentService;

    public StudentServlet() {
    	studentService = new StudentService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		if(request.getPathInfo() != null) {
			action = request.getPathInfo().substring(1);
		}
		System.out.println(action);
		
		switch(action) {
			case "new": 	showNewStudentForm(request, response);
							break;
			case "edit": 	showEditStudentForm(request, response);
							break;
			case "delete":  deleteStudent(request, response);
							break;
			default: listStudents(request,response);
		}
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong( request.getParameter("id") );
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String adresa = request.getParameter("adresa");

		Student student = new Student(id,nume,prenume,adresa);
		studentService.updateStudent(student);
		setSuccessMsg(request, "Student editat cu succes");
		response.sendRedirect("/studenti");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("_METHOD") != null &&  request.getParameter("_METHOD").equalsIgnoreCase("PUT") ) {
			doPut(request, response);
		} else {

			String nume = request.getParameter("nume");
			String prenume = request.getParameter("prenume");
			String adresa = request.getParameter("adresa");
			
			Student student = new Student(nume,prenume,adresa);
			studentService.processNewStudent(student);
			setSuccessMsg(request, "Student creeat cu succes");
			response.sendRedirect("/studenti");
		}

	}

	protected void showNewStudentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/pages/students/studentForm.jsp").forward(request, response);
	}
	
	
	protected void showEditStudentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listStudents(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		Student existing_student = studentService.retriveStudent(id);
		
		request.setAttribute("student", existing_student);
		getServletContext().getRequestDispatcher("/pages/students/studentForm.jsp").forward(request, response);
	}
	
	protected void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listStudents(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		studentService.deleteStudent(id);
		setSuccessMsg(request, "Student sters cu succes");
		response.sendRedirect("/studenti");
	}
	
	protected void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Student> students = studentService.serveAllStudents();
		
		request.setAttribute("students", students);
		getServletContext().getRequestDispatcher("/pages/students/index.jsp").forward(request, response);
	}
	
	protected static void setSuccessMsg(HttpServletRequest request, String msg) {
		HttpSession session = request.getSession(false);
		session.setAttribute("success", msg);
	}

}
