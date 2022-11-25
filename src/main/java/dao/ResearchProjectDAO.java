package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import models.Student;
import models.Teacher;
import models.ResearchProject;

public class ResearchProjectDAO extends DAO {

	private String SELECT_ALL_RESEARCH_PROJECTS
	= "SELECT id_profesor, id_student, titlu, s.nume numeStudent, s.prenume prenumeStudent, p.nume numeProfesor, p.prenume prenumeProfesor FROM PROIECTE_CERCETARE INNER JOIN PROFESORI p USING(id_profesor) INNER JOIN STUDENTI s USING(id_student) ORDER BY titlu ASC";
	
	private String FIND_RESEARCH_PROJECT_BY_IDS
	= "SELECT id_profesor, id_student, titlu, s.nume numeStudent, s.prenume prenumeStudent, p.nume numeProfesor, p.prenume prenumeProfesor FROM PROIECTE_CERCETARE INNER JOIN PROFESORI p USING(id_profesor) INNER JOIN STUDENTI s USING(id_student) where id_profesor = ? AND id_student = ?";
	
	private String INSERT_NEW_RESEARCH_PROJECT = "INSERT INTO PROIECTE_CERCETARE VALUES(?,?,?)";
	
	private String UPDATE_RESEARCH_PROJECT 
	= "UPDATE PROIECTE_CERCETARE SET id_profesor = ?, id_student = ?, titlu = ? WHERE id_profesor = ? AND id_student = ?";
	
	private String DELETE_RESEARCH_PROJECT = "DELETE FROM PROIECTE_CERCETARE WHERE id_profesor = ? AND id_student = ?";
	
	public ArrayList<ResearchProject> getAllResearchProjects() {
		ArrayList<ResearchProject> researchProjects = new ArrayList<ResearchProject>();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_RESEARCH_PROJECTS);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				Teacher teacher = new Teacher(
						rs.getLong("id_profesor"),
						rs.getString("numeProfesor"),
						rs.getString("prenumeProfesor")
						);
				
				Student student = new Student(
						rs.getLong("id_student"),
						rs.getString("numeStudent"),
						rs.getString("prenumeStudent")
						);
				
				ResearchProject researchProject = new ResearchProject(
						teacher,
						student,
						rs.getString("titlu")
				);
				
				researchProjects.add(researchProject);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return researchProjects;
	}
	
	public ResearchProject getResearchProject(long teacher_id, long student_id) {
		
		ResearchProject researchProject = new ResearchProject();
		
		try {
		
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(FIND_RESEARCH_PROJECT_BY_IDS);
			
			statement.setLong(1, teacher_id);
			statement.setLong(2, student_id);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				Teacher teacher = new Teacher(
						rs.getLong("id_profesor"),
						rs.getString("numeProfesor"),
						rs.getString("prenumeProfesor")
						);
				
				Student student = new Student(
						rs.getLong("id_student"),
						rs.getString("numeStudent"),
						rs.getString("prenumeStudent")
						);
				
				researchProject.setTeacher(teacher);
				researchProject.setStudent(student);
				researchProject.setTitle( rs.getString("titlu") );

			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return researchProject;
	}

	public String insertNewResearchProject(Long teacher_id, Long student_id, String title) {
		
		String error_msg = "";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_NEW_RESEARCH_PROJECT);
			
			statement.setLong(1, teacher_id);
			statement.setLong(2, student_id);
			statement.setString(3, title);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
			
		}catch(SQLIntegrityConstraintViolationException e){
			System.out.println("create pk nerespectata");
			error_msg = "Studentul deja colaboreaza cu acest profesor";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return error_msg;
		
	}

	public String updateResearchProject(ResearchProject existing_researchProject, ResearchProject researchProject) {
		String error_msg = "";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_RESEARCH_PROJECT);
			
			statement.setLong(1, researchProject.getTeacher().getId());
			statement.setLong(2, researchProject.getStudent().getId());
			statement.setString(3, researchProject.getTitle());
			
			statement.setLong(4, existing_researchProject.getTeacher().getId());
			statement.setLong(5, existing_researchProject.getStudent().getId());

			int rows = statement.executeUpdate();
			System.out.println("im here");
			System.out.println(rows);
			conn.close();
			
		}catch(SQLIntegrityConstraintViolationException e){
			System.out.println("update pk nerespectata");
			error_msg = "Studentul deja lucreaza cu acest profesor";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return error_msg;
	}
	
	public void deleteResearchProject(long teacher_id, long student_id) {
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_RESEARCH_PROJECT);
			statement.setLong(1, teacher_id);
			statement.setLong(2, student_id);
			int rows = statement.executeUpdate();
			System.out.println(rows);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
