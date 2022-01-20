package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;



/**
 * 
 * En esta clase se almacena la informacion de las reservas realizadas por los clientes
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
public class Reserva {
	private int identificador = 0;
	private int numTickets = 0;
	private LocalDate fecha;
	private LocalTime hora;
	
	/**
	 * Constructor Reserva
	 * @param identificador: indica el numero asignado a la reserva
	 * @param numTickets: es el numero de tickets que se han reservado
	 * @param fecha: almacena la fecha de la reserva
	 * @param hora: almacena la hora de la reserva
	 */
	public Reserva(int identificador, int numTickets, LocalDate fecha, LocalTime hora) {
		super();
		this.identificador = identificador;
		this.numTickets = numTickets;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getIdentificador() {
		return identificador;
	}

	public int getNumTickets() {
		return numTickets;
	}

	public void setNumTickets(int numTickets) {
		this.numTickets = numTickets;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	
	
}
