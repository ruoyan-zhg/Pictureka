package Modelo;

/**
 * 
 * En esta clase se asignan las respectivas características de un sensor de
 * temperatura.
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class SensorTemperatura extends Sensor {
	private int temperaturaMaxima;
	private int temperaturaMinima;

	/**
	 * 
	 * Constructor del Sensor de temperatura, dónde se especifican los respectivos atributos.
	 * 
	 * @param numeracionSensor		Identificador del sensor.
	 * @param voltaje				
	 * @param fabricante			Nombre de la empresa que fabricó el sensor.
	 * @param datoActual			Temperatura captada por el sensor en el momento de tomar la temperatura.
	 * @param temperaturaMaxima		Medida máxima establecida como límite.
	 * @param temperaturaMinima		Medida mínima establecida como límite.
	 */
	public SensorTemperatura(int numeracionSensor, int voltaje, String fabricante, String datoActual,
			int temperaturaMaxima, int temperaturaMinima) {
		super(numeracionSensor, voltaje, fabricante, datoActual);
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
	}

	//Getters y Setters
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
