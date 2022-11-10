package service;

import java.util.ArrayList;

import dao.StudentDAO;
import models.Student;

public class StudentService {

	private StudentDAO studentDAO;
	
	public StudentService(){
		studentDAO = new StudentDAO();
	}
	
	public ArrayList<Student> serveAllStudents(){
		return studentDAO.getAllStudents();
	}
	
	public void processNewStudent(Student student) {
		studentDAO.insertNewStudent(student);
	}
	
	public Student retriveStudent(long id) {
		return studentDAO.getStudent(id);
	}
	
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}
	
	public void deleteStudent(long id) {
		studentDAO.deleteStudent(id);
	}
}
