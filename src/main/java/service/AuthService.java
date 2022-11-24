package service;

import dao.UserDAO;
import models.User;

public class AuthService {
	
	private UserDAO userDAO;

	public AuthService() {
		userDAO = new UserDAO();
	}
	
	public void registerUser(User user) {
		userDAO.insertNewUser(user);
	}

}
