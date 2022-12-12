package service;

import java.util.ArrayList;

import dao.UserDAO;
import models.User;

public class UserService {

	private UserDAO studentDAO;
	
	public UserService(){
		studentDAO = new UserDAO();
	}
	
	public ArrayList<User> serveAllActiveUsers(){
		return studentDAO.getAllActiveUsers();
	}
	
	public ArrayList<User> serveAllPendingUsers(){
		return studentDAO.getAllPendingUsers();
	}
	
	public void processNewUser(User student) {
		studentDAO.insertNewUser(student);
	}
	
	public User retriveUser(long id) {
		return studentDAO.getUser(id);
	}
	
	public void processUserUpdate(User student) {
		studentDAO.updateUser(student);
	}

	public void processUserDelete(long id) {
		studentDAO.deleteUser(id);
	}

}

