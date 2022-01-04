package Modelo;

public class SensorLuz extends Sensor {
	
	private int luzMinima;
	private int luzMaxima;
	
	public SensorLuz(int numeracionSensor, int voltaje, String fabricante, String datoActual, int luzMinima,
			int luzMaxima) {
		super(numeracionSensor, voltaje, fabricante, datoActual);
		this.luzMinima = luzMinima;
		this.luzMaxima = luzMaxima;
	}

	public int getLuzMinima() {
		return luzMinima;
	}

	public void setLuzMinima(int luzMinima) {
		this.luzMinima = luzMinima;
	}

	public int getLuzMaxima() {
		return luzMaxima;
	}

	public void setLuzMaxima(int luzMaxima) {
		this.luzMaxima = luzMaxima;
	}
	
	

	
}
