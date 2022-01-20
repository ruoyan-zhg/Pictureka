package Modelo;

import java.util.Vector;

public class Museo {
	private Vector<Sala> salas;
	
	public Museo() {
		this.salas = new Vector<Sala>();
	}
	
	
	public void recuperarSalas() {
		Datos datos = new Datos();
		Vector<Sala> _salas = datos.desserializarJsonSalas();
		if (_salas != null) {
			this.salas = _salas;
		}
	}
	
	public void escribirSalas() {
		Datos datos = new Datos();
		datos.serializarVectorSalasAJson(salas);
	}
	
	public void MNuevaSala(int identificador) {
		recuperarSalas();
		if (comprobarIdentificadorSala(identificador)) {
			salas.addElement(new Sala(identificador));
			escribirSalas();
		}
		else {
			
		}
	}
	
	private boolean comprobarIdentificadorSala(int identificador) {
		boolean noRepetido = true;
		int contador = 0;
		if (salas != null) {
			while (noRepetido != false && contador < salas.size()) {
				if (salas.elementAt(contador).getIdentificador()==identificador) {
					noRepetido = false;	
				}
				contador++;
			}
		}
		return noRepetido;
	}
	
	
	public Sala recuperar1Salas(int identificador) {
		recuperarSalas();
		boolean encontrado = false;
		Sala temporal = new Sala(-1);
		int contador = 0;
		if(salas != null) {
			while (encontrado != true && contador < salas.size()) {
				if (salas.elementAt(contador).getIdentificador()== identificador) {
					encontrado = true;	
					temporal = salas.elementAt(contador);
				}
				contador++;
			}
		}
		return temporal;
	}
	






	public Vector<Sala> getSalas() {
		return salas;
	}

	public void setSalas(Vector<Sala> salas) {
		this.salas = salas;
	}
	

}
