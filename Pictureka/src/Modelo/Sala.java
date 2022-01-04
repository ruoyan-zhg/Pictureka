package Modelo;

import java.util.Vector;

public class Sala {
	private int identificador;
	private Vector <SensorLuz> sensoresLuz;
	private Vector <SensorTemperatura> sensoresTemperatura;
	private Vector<SensorDistancia> sensoresDistancia;
	private Vector<Evento> eventos;
	
	
	
	//proximamente se necesitan los sensores que tendran cada sala
	public Sala(int identificador) {
		this.identificador = identificador;
		this.sensoresDistancia = new Vector<SensorDistancia>();
		this.sensoresTemperatura = new Vector<SensorTemperatura>();
		this.sensoresLuz = new Vector<SensorLuz>();
		this.eventos = new Vector<Evento>();
	}
	
	
	public int getIdentificador() {
		return identificador;
	}


	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}


	public Vector<Evento> getEventos() {
		return eventos;
	}


	public void setEventos(Vector<Evento> eventos) {
		this.eventos = eventos;
	}


	public Vector<SensorLuz> getSensoresLuz() {
		return sensoresLuz;
	}
	public void setSensoresLuz(Vector<SensorLuz> sensoresLuz) {
		this.sensoresLuz = sensoresLuz;
	}
	public Vector<SensorTemperatura> getSensoresTemperatura() {
		return sensoresTemperatura;
	}
	public void setSensoresTemperatura(Vector<SensorTemperatura> sensoresTemperatura) {
		this.sensoresTemperatura = sensoresTemperatura;
	}
	public Vector<SensorDistancia> getSensoresDistancia() {
		return sensoresDistancia;
	}
	public void setSensoresDistancia(Vector<SensorDistancia> sensoresDistancia) {
		this.sensoresDistancia = sensoresDistancia;
	}

}
