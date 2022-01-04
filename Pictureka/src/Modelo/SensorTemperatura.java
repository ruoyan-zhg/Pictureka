package Modelo;

public class SensorTemperatura extends Sensor {
	private int temperaturaMaxima;
	private int temperaturaMinima;
	
	public SensorTemperatura(int numeracionSensor, int voltaje, String fabricante, String datoActual,
			int temperaturaMaxima, int temperaturaMinima) {
		super(numeracionSensor, voltaje, fabricante, datoActual);
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
	}

	public int getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(int temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public int getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(int temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}
	
	

}
