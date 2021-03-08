package com.javatechie.aws.example.cliente;

public class Cliente {
	private String id;
	private String nombres;
	private String apellidos;
	private String fecha_nacimiento;
	private Integer edad;
	private String fecha_probable;

	public String getFecha_probable() {
		return fecha_probable;
	}

	public void setFecha_probable(String fecha_probable) {
		this.fecha_probable = fecha_probable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}


}