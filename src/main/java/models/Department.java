package models;

public class Department {

	private long id;
	private String denumire;
	
	public Department(long id, String denumire) {
		this.id = id;
		this.denumire = denumire;
	}
	
	public Department(String denumire) {
		this.denumire = denumire;
	}
	
	public Department(long id) {
		this.id = id;
	}

	public Department() {
	}

	public long getId() {
		return id;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	
	
}
