package service;

import java.util.ArrayList;

import dao.ResearchProjectDAO;
import models.ResearchProject;

public class ResearchProjectService {

	private ResearchProjectDAO researchProjectDAO;
	
	public ResearchProjectService(){
		researchProjectDAO = new ResearchProjectDAO();
	}
	
	public ArrayList<ResearchProject> serveAllResearchProjects(){
		return researchProjectDAO.getAllResearchProjects();
	}
	
	public ResearchProject retriveResearchProject(long teacher_id, long department_id) {
		ResearchProject existing_teacher_department = researchProjectDAO.getResearchProject(teacher_id, department_id);
		return existing_teacher_department;
	}

	public String processNewResearchProject(Long teacher_id, Long department_id, String title) {
		String error_msg = researchProjectDAO.insertNewResearchProject(teacher_id, department_id, title);
		return error_msg;
	}

	public String processResearchProjectUpdate(ResearchProject existing_researchProject, ResearchProject researchProject) {
		String error_msg = researchProjectDAO.updateResearchProject(existing_researchProject, researchProject);
		return error_msg;
	}
	
	public void processResearchProjectDelete(long teacher_id, long department_id) {
		researchProjectDAO.deleteResearchProject(teacher_id, department_id);
	}

	
}
