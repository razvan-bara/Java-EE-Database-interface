package service;

import java.util.ArrayList;

import dao.TeacherDAO;
import models.Teacher;

public class TeacherService {

	
	private TeacherDAO teacherDAO;
	
	public TeacherService(){
		teacherDAO = new TeacherDAO();
	}
	
	public ArrayList<Teacher> serveAllTeachers(){
		return teacherDAO.getAllTeachers();
	}
	
	public void processNewTeacher(Teacher student) {
		teacherDAO.insertNewTeacher(student);
	}
	
	public Teacher retriveTeacher(long id) {
		return teacherDAO.getTeacher(id);
	}
	
	public void processTeacherUpdate(Teacher student) {
		teacherDAO.updateTeacher(student);
	}
	
	public void processTeacherDelete(long id) {
		teacherDAO.deleteTeacher(id);
	}

}
