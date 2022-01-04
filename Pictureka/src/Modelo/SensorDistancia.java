package Modelo;

public class SensorDistancia extends Sensor {
	private int rangoMinimo;

	public SensorDistancia(int numeracionSensor, int voltaje, String fabricante, String datoActual, int rangoMinimo) {
		super(numeracionSensor, voltaje, fabricante, datoActual);
		this.rangoMinimo = rangoMinimo;
	}
}
