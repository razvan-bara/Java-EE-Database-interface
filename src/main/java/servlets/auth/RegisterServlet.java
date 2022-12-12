package servlets.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import service.AuthService;
import utils.AuthSessionHandler;
import utils.Hash;
import utils.SessionMessage;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String PATH_TO_REGISTER_PAGE="/pages/auth/register.jsp";
	private AuthService authService;
	
	public RegisterServlet() {
		authService = new AuthService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(PATH_TO_REGISTER_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String full_name = request.getParameter("full_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String rol = "client";
		
		if( !password.equals(confirm_password) ) {
			
			request.setAttribute("error", "Parolele nu corespund");
			request.getRequestDispatcher(PATH_TO_REGISTER_PAGE).forward(request, response);
			return;
		}
		
		User user = new User(full_name, email, password, rol);
		String error_msg = authService.registerUser(user);
		
		String redirectTo = "";
		
		if(error_msg.isBlank()) {
			SessionMessage.setSuccessMsg(request, "Cont creat cu succes");
			AuthSessionHandler.setSessionUser(request, user);
			redirectTo = "/";
		} else {
			SessionMessage.setErrorMsg(request, error_msg);
			 redirectTo = "/register";
		}
		
		response.sendRedirect(redirectTo);
	}

}
