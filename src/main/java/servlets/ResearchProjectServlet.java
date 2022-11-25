package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Student;
import models.Teacher;
import models.ResearchProject;
import service.StudentService;
import service.TeacherService;
import service.ResearchProjectService;
import utils.SessionMessage;

/**
 * Servlet implementation class ResearchProjectServlet
 */
@WebServlet("/proiecte_cercetare/*")
public class ResearchProjectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private ResearchProjectService researchProjectService;
	private TeacherService teacherService;
	private StudentService studentService;
	
    public ResearchProjectServlet() {
    	
    	researchProjectService = new ResearchProjectService();
    	teacherService = new TeacherService();
    	studentService = new StudentService();
    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		if(request.getPathInfo() != null) {
			action = request.getPathInfo().substring(1);
		}
		System.out.println(action);
		
		switch(action) {
			case "new": 	showNewResearchProjectForm(request, response);
							break;
			case "edit": 	showEditResearchProjectForm(request, response);
							break;
			case "delete":  deleteResearchProject(request, response);
							break;
			default: listResearchProjects(request,response);
		}
		
	}

	protected void submitNewResearchProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("titlu");
		Long teacher_id = Long.parseLong( request.getParameter("teacher") );
		Long student_id = Long.parseLong( request.getParameter("student") );


		System.out.println(teacher_id);
		System.out.println(student_id);
		System.out.println(title);
		
		String error_msg = researchProjectService.processNewResearchProject(teacher_id, student_id, title);
		
		if(error_msg.isBlank()) {
			SessionMessage.setSuccessMsg(request, "Proiect de cercetare adaugat");
		} else {
			SessionMessage.setErrorMsg(request, error_msg);
		}
		
		response.sendRedirect("/proiecte_cercetare");
	}
	
	protected void deleteResearchProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if( request.getParameter("teacher_id") == null || request.getParameter("student_id") == null ) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listResearchProjects(request,response);
		}
		
		long teacher_id = Long.parseLong( request.getParameter("teacher_id") );
		long student_id = Long.parseLong( request.getParameter("student_id") );
		
		researchProjectService.processResearchProjectDelete(teacher_id, student_id);
		SessionMessage.setSuccessMsg(request, "Proiect de cercetare sters");
		
		response.sendRedirect("/proiecte_cercetare");
	}
	
	protected void submitEditResearchProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long existing_teacher_id = Long.parseLong( request.getParameter("existing_teacher_id") );
		Long existing_student_id = Long.parseLong( request.getParameter("existing_student_id") );
		
		Long teacher_id = Long.parseLong( request.getParameter("teacher") );
		Long student_id = Long.parseLong( request.getParameter("student") );
		String title = request.getParameter("titlu");
		
		ResearchProject existing_researchProject = new ResearchProject(existing_teacher_id, existing_student_id);
		ResearchProject researchProject = new ResearchProject(teacher_id, student_id, title);
		
		String error_msg = researchProjectService.processResearchProjectUpdate(existing_researchProject, researchProject);
		
		if(error_msg.isBlank()) {
			SessionMessage.setSuccessMsg(request, "Proiect de cercetare modificat");
		} else {
			SessionMessage.setErrorMsg(request, error_msg);
		}
		
		response.sendRedirect("/proiecte_cercetare");
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("_METHOD") != null &&  request.getParameter("_METHOD").equalsIgnoreCase("PUT") ) {
			submitEditResearchProjectForm(request, response);
		} else {
			submitNewResearchProjectForm(request, response);
		}
	}

	protected void showNewResearchProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Teacher> teachers = teacherService.serveAllTeachers();
		ArrayList<Student> students = studentService.serveAllStudents();
		
		
		request.setAttribute("teachers", teachers);
		request.setAttribute("students", students);
		
		getServletContext().getRequestDispatcher("/pages/researchProjects/researchProjectForm.jsp").forward(request, response);
	}
	
	
	protected void showEditResearchProjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if( request.getParameter("teacher_id") == null || request.getParameter("student_id") == null ) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listResearchProjects(request,response);
		}
		
		long teacher_id = Long.parseLong( request.getParameter("teacher_id") );
		long student_id = Long.parseLong( request.getParameter("student_id") );
		
		ResearchProject existing_researchProject = researchProjectService.retriveResearchProject(teacher_id, student_id);
		
		ArrayList<Teacher> teachers = teacherService.serveAllTeachersExcept(teacher_id);
		ArrayList<Student> students = studentService.serveAllStudentsExcept(student_id);
		
		request.setAttribute("teachers", teachers);
		request.setAttribute("students", students);
		request.setAttribute("researchProject", existing_researchProject);
		
		getServletContext().getRequestDispatcher("/pages/researchProjects/researchProjectForm.jsp").forward(request, response);
	}
	
	protected void listResearchProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ResearchProject> researchProjects = researchProjectService.serveAllResearchProjects();
		
		request.setAttribute("researchProjects", researchProjects);
		getServletContext().getRequestDispatcher("/pages/researchProjects/index.jsp").forward(request, response);
	}


}
