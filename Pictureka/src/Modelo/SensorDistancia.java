package Modelo;
//io

/**
 * 
 * En esta clase se almacena la informacion del sensor de distancia 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
public class SensorDistancia extends Sensor {
	private int rangoMinimo;

	/**
	 *  Constructor del Sensor de distancia, dónde se especifican los respectivos atributos.
	 * 
	 * @param numeracionSensor El codigo unico que tiene el sensor
	 * @param voltaje voltaje que necesita el sensor para funcionar
	 * @param fabricante Empresa que hizo el sensor
	 * @param datoActual Dato que maneja actualmente el sensor
	 * @param rangoMinimo rango minimo de que admite que alguien se acerque al sensor
	 */
	public SensorDistancia(int numeracionSensor, int voltaje, String fabricante, String datoActual, int rangoMinimo) {
		super(numeracionSensor, voltaje, fabricante, datoActual);
		this.rangoMinimo = rangoMinimo;
	}
}