package models;

import utils.Hash;

public class User {

	private long id_utilizator;
	private String nume_complet;
	private String email;
	private String parola;
	private String rol;
	
	public User() {
		
	};
	
	public User(long id_utilizator, String nume_complet, String email, String parola, String rol) {
		super();
		this.id_utilizator = id_utilizator;
		this.nume_complet = nume_complet;
		this.email = email;
		this.parola = parola;
		this.rol = rol;
	}
	
	

	public User(String nume_complet, String email, String parola, String rol) {
		super();
		this.nume_complet = nume_complet;
		this.email = email;
		this.parola = parola;
		this.rol = rol;
	}

	public User(long id, String nume_complet, String email, String rol) {
		super();
		this.id_utilizator = id;
		this.nume_complet = nume_complet;
		this.email = email;
		this.rol = rol;
	}



	public User(String nume_complet, String email, String parola) {
		super();
		this.nume_complet = nume_complet;
		this.email = email;
		this.parola = parola;
	}

	public long getId_utilizator() {
		return id_utilizator;
	}

	public void setId_utilizator(long id_utilizator) {
		this.id_utilizator = id_utilizator;
	}

	public String getNume_complet() {
		return nume_complet;
	}

	public void setNume_complet(String nume_complet) {
		this.nume_complet = nume_complet;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isAdmin() {
		return this.rol.equals("admin");
	}

	public boolean checkPassword(String password) {
		return this.parola.equals( password );
	}
	
}
