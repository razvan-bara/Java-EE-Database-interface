package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Student;
import service.StudentService;

@WebServlet("/studenti")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentService studentService;

    public StudentServlet() {
    	studentService = new StudentService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Student> students = studentService.serveAllStudents();
		request.setAttribute("students", students);
		getServletContext().getRequestDispatcher("/pages/students/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String adresa = request.getParameter("adresa");
		Student student = new Student(nume,prenume,adresa);
		studentService.processNewStudent(student);
		response.sendRedirect("/studenti");
	}

}
