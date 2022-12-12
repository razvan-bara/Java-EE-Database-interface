package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import service.UserService;
import utils.SessionMessage;

@WebServlet("/utilizatori/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService;

    public UserServlet() {
    	userService = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		if(request.getPathInfo() != null) {
			action = request.getPathInfo().substring(1);
		}
		System.out.println(action);
		
		switch(action) {
			case "edit": 	showEditUserForm(request, response);
							break;
			case "delete":  deleteUser(request, response);
							break;
			default: listUsers(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("_METHOD") != null &&  request.getParameter("_METHOD").equalsIgnoreCase("PUT") ) {
			submitEditUserForm(request, response);
		}

	}
	
	protected void showEditUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listUsers(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		User existing_user = userService.retriveUser(id);
		
		request.setAttribute("user", existing_user);
		getServletContext().getRequestDispatcher("/pages/users/userForm.jsp").forward(request, response);
	}

	protected void submitEditUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong( request.getParameter("id") );
		String full_name = request.getParameter("full_name");
		String email = request.getParameter("email");
		Boolean is_admin =  request.getParameter("is_admin") != null;
		String rol = "client";
		if( is_admin.booleanValue() ) {
			rol = "admin";
		}
		
		User user = new User(id,full_name,email,rol);
		userService.processUserUpdate(user);
		SessionMessage.setSuccessMsg(request, "User editat cu succes");
		response.sendRedirect("/utilizatori");
	}
	
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("id") == null) {
			request.setAttribute("error", "ID-ul nu a fost specificat");
			listUsers(request,response);
		}
		
		long id = Long.parseLong( request.getParameter("id") );
		userService.processUserDelete(id);
		SessionMessage.setSuccessMsg(request, "User sters cu succes");
		response.sendRedirect("/utilizatori");
	}
	
	protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<User> users = userService.serveAllActiveUsers();
		
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/pages/users/index.jsp").forward(request, response);
	}

}
