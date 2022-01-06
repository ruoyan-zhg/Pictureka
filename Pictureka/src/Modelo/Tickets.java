package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tickets {
	private int identificador = 0;
	private LocalDate fecha;
	private LocalTime hora;
	
	public Tickets(int identificador, LocalDate fecha, LocalTime hora) {
		super();
		this.identificador = identificador;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getIdentificador() {
		return identificador;
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
