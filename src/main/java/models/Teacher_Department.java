package models;

public class Teacher_Department {
	
	private Teacher teacher;
	private Department department;
	private String position;
	
	
	public Teacher_Department(Teacher teacher, Department department, String position) {
		this.teacher = teacher;
		this.department = department;
		this.position = position;
	}
	
	public Teacher_Department(long teacher_id, long department_id, String position) {
		this.teacher = new Teacher(teacher_id);
		this.department = new Department(department_id);
		this.position = position;
	}
	
	public Teacher_Department(long teacher_id, long department_id) {
		this.teacher = new Teacher(teacher_id);
		this.department = new Department(department_id);
	}


	public Teacher_Department() {
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Department getDepartment() {
		return department;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
