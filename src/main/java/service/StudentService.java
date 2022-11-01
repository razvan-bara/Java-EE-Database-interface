package service;

import java.util.ArrayList;

import dao.StudentDAO;
import models.Student;

public class StudentService {

	private StudentDAO studentService;
	
	public StudentService(){
		studentService = new StudentDAO();
	}
	
	public ArrayList<Student> serveAllStudents(){
		return studentService.getAllStudents();
	}
	
	public void processNewStudent(Student student) {
		studentService.insertNewStudent(student);
	}
}
