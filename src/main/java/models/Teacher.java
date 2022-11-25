package models;

public class Teacher {
	
	private long id;
	private String nume;
	private String prenume;
	private String adresa;
	
	public Teacher(long id, String nume, String prenume, String adresa) {
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
	}

	public Teacher(String nume, String prenume, String adresa) {
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
	}

	public Teacher(long id, String nume, String prenume) {
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
	}

	public Teacher(long id) {
		super();
		this.id = id;
	}

	public Teacher() {
		
	}

	public long getId() {
		return id;
	}

	public String getNume() {
		return nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		sb.append( nume );
		sb.append(" ");
		sb.append(prenume);
		
		return sb.toString();
	}
}
