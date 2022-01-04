package Modelo;

public class Sensor {
	private int numeracionSensor;
	private int voltaje;
	private String fabricante;
	private String datoActual;
	
	
	
	public Sensor(int numeracionSensor, int voltaje, String fabricante, String datoActual) {
		this.numeracionSensor = numeracionSensor;
		this.voltaje = voltaje;
		this.fabricante = fabricante;
		this.datoActual = datoActual;
	}
	
	public int getNumeracionSensor() {
		return numeracionSensor;
	}
	public void setNumeracionSensor(int numeracionSensor) {
		this.numeracionSensor = numeracionSensor;
	}
	public int getVoltaje() {
		return voltaje;
	}
	public void setVoltaje(int voltaje) {
		this.voltaje = voltaje;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getDatoActual() {
		return datoActual;
	}
	public void setDatoActual(String datoActual) {
		this.datoActual = datoActual;
	}
	
	

}
