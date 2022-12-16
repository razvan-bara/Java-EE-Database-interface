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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String PATH_TO_LOGIN_PAGE="/pages/auth/login.jsp";
	private AuthService authService;
	
	public LoginServlet() {
		authService = new AuthService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(PATH_TO_LOGIN_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		password = Hash.hashPass(password);
		User user = authService.attemptLogin(email, password);
		
		String redirectTo = "";
		
		if(user != null) {
			AuthSessionHandler.setSessionUser(request, user);
			
			SessionMessage.setSuccessMsg(request, "Ai intrat cu succes in cont");
			redirectTo = "/";
		} else {
			SessionMessage.setErrorMsg(request, "Mai incearca sa o data");
			 redirectTo = "/login";
		}
		
		response.sendRedirect(redirectTo);
	}

}
