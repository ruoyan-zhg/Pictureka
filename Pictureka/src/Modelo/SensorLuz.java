package Modelo;
//io

/**
 * 
 * En esta clase se almacena la informacion del sensor de luz
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */
public class SensorLuz extends Sensor {
	
	private int luzMinima;
	private int luzMaxima;
	
	/**
	 * Constructor del Sensor de luz, dónde se especifican los respectivos atributos.
	 * 
	 * @param numeracionSensor El codigo unico que tiene el sensor
	 * @param voltaje voltaje que necesita el sensor para funcionar
	 * @param fabricante Empresa que hizo el sensor
	 * @param datoActual Dato que maneja actualmente el sensor
	 * @param luzMinima Rango minimo de luz permitido por el administrador
	 * @param luzMaxima Rango maximo de luz permitido por el administrador
	 */
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
