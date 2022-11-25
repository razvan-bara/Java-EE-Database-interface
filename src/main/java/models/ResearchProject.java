package models;

public class ResearchProject {
	
	private Teacher teacher;
	private Student student;
	private String title;
	
	
	public ResearchProject(Teacher teacher, Student student, String title) {
		this.teacher = teacher;
		this.student = student;
		this.title = title;
	}
	
	public ResearchProject(long teacher_id, long student_id, String title) {
		this.teacher = new Teacher(teacher_id);
		this.student = new Student(student_id);
		this.title = title;
	}


	public ResearchProject() {
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setStudent(Student student) {
		this.student = student;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
