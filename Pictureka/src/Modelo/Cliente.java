package Modelo;

import java.time.LocalDate;
import java.util.Vector;

public class Cliente { 

	private int identificadorCliente;
	private String usuario;
	private String dni;
	private String email;
	private String contrasenia;
	private LocalDate fechaNacimiento;
	private Vector<Reserva> reservas;
	
	
	//Constructores
	public Cliente(String usuario, String dni, String email, String contrasenia, LocalDate fechaNacimiento, Vector<Reserva> reservas) {
		this.identificadorCliente = 1;
		this.usuario = usuario;
		this.dni = dni;
		this.email = email;
		this.contrasenia = contrasenia;
		this.fechaNacimiento = fechaNacimiento;
		this.reservas = reservas;

	}


	
	
	public int getIdentificadorCliente() {
		return identificadorCliente;
	}


	public void setIdentificadorCliente(int identificadorCliente) {
		this.identificadorCliente = identificadorCliente;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Vector<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Vector<Reserva> reservas) {
		this.reservas = reservas;
	}

	
	
}
