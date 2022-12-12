package service;

import javax.servlet.http.HttpServletRequest;

import dao.UserDAO;
import models.User;
import utils.AuthSessionHandler;

public class AuthService {
	
	private UserDAO userDAO;

	public AuthService() {
		userDAO = new UserDAO();
	}
	
	public String registerUser(User user) {
		String error_msg = userDAO.insertNewUser(user);
		return error_msg;
	}

	public User attemptLogin(String email, String password) {
		User user = userDAO.loginUser(email, password);
		return user;
	}

	public void logoutUser(HttpServletRequest request) {
		AuthSessionHandler.logoutUser(request);
		
	}

}
