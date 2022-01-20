package Modelo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Vector;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * 
 * 
 * @author Jolie Alain Vásquez
 * @author Oscar González Guerra
 * @author Ruoyan Zhang
 * @author Lian Salmerón López
 *
 */

public class Datos {

	/**
	 * Convierte el vector de usuarios en json
	 * 
	 * @param usuarios El vector con los datos de los usuarios 
	 */
	public void serializarArrayAJson(Vector<Cliente> usuarios) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(usuarios);
		
		try(FileWriter writer = new FileWriter("usuarios.json")){
			prettyGson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * Convierte el vector de Eventos en json
	 * 
	 * @param eventos El vector con los datos de los eventos 
	 */
	public void serializarVectorEventosAJson(Vector<Evento> eventos) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(eventos);
		
		try(FileWriter writer = new FileWriter("Eventos.json")){
			prettyGson.toJson(eventos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Convierte el vector de staff en json
	 * 
	 * @param usuarios El vector con los datos de los staff 
	 */
	public void serializarStaffAJson(Vector<Staff> staff) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(staff);
		
		try(FileWriter writer = new FileWriter("staff.json")){
			prettyGson.toJson(staff, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * Convierte el vector de salas en json
	 * 
	 * @param salas El vector con los datos de las salas 
	 */
	public void serializarVectorSalasAJson(Vector<Sala> salas) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(salas);
		
		try(FileWriter writer = new FileWriter("Salas.json")){
			prettyGson.toJson(salas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Convierte el vector de informes en json
	 * 
	 * @param informes El vector con los datos de los informes 
	 */
	public void serializarVectorInformesAJson(Vector<Informe> informes) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(informes);
		
		try(FileWriter writer = new FileWriter("Informes.json")){
			prettyGson.toJson(informes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * Convierte el vector de reservas en json
	 * 
	 * @param reservas El vector con los datos de los reservas 
	 */
	public void serializarVectorReservasAJson(Vector<Reserva> reservas) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(reservas);
		
		try(FileWriter writer = new FileWriter("Reservas.json")){
			prettyGson.toJson(reservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	/**
	 * Convierte los datos de un json en un vector de reservas
	 * 
	 * @return Devuelve el vector de reservas 
	 */
	public Vector<Reserva> desserializarJsonAReservas() {
		Vector<Reserva> reservas = new Vector<Reserva>();
		
		try (Reader reader = new FileReader("Reservas.json")) {
			Gson gson = new Gson();
			Type tipoListausuarios = new TypeToken<Vector<Reserva>>(){}.getType();
			reservas = gson.fromJson(reader, tipoListausuarios);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
		return reservas;
	}
	
	
	/**
	 * Convierte los datos de un json en un vector de usuarios
	 * 
	 * @return Devuelve el vector de usuarios 
	 */
	public Vector<Cliente> desserializarJsonAusuarios() {
		Vector<Cliente> usuarios = new Vector<Cliente>();
		
		try (Reader reader = new FileReader("usuarios.json")) {
			Gson gson = new Gson();
			Type tipoListausuarios = new TypeToken<Vector<Cliente>>(){}.getType();
			usuarios = gson.fromJson(reader, tipoListausuarios);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
		return usuarios;
	}
	
	/**
	 * Convierte los datos de un json en un vector de Eventos
	 * 
	 * @return Devuelve el vector de Eventos 
	 */
	public Vector<Evento> desserializarJsonAEventos() {
		Vector<Evento> eventos = new Vector<Evento>();
		
		try (Reader reader = new FileReader("Eventos.json")) {
			Gson gson = new Gson();
			Type tipoListaEventos = new TypeToken<Vector<Evento>>(){}.getType();
			eventos = gson.fromJson(reader, tipoListaEventos);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
		return eventos;
	}
	
	/**
	 * Convierte los datos de un json en un vector de staff
	 * 
	 * @return Devuelve el vector de staff 
	 */
	public Vector<Staff> desserializarJsonStaff() {
		Vector<Staff> staff = new Vector<Staff>();
		
		try (Reader reader = new FileReader("staff.json")) {
			Gson gson = new Gson();
			Type tipoListaStaff = new TypeToken<Vector<Staff>>(){}.getType();
			staff = gson.fromJson(reader, tipoListaStaff);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
		return staff;
	}
	
	/**
	 * Convierte los datos de un json en un vector de salas
	 * 
	 * @return Devuelve el vector de salas 
	 */
	public Vector<Sala> desserializarJsonSalas() {
		Vector<Sala> salas = new Vector<Sala>();
		
		try (Reader reader = new FileReader("Salas.json")) {
			Gson gson = new Gson();
			Type tipoListaSalas = new TypeToken<Vector<Sala>>(){}.getType();
			salas = gson.fromJson(reader, tipoListaSalas);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
		return salas;
	}

	/**
	 * Convierte los datos de un json en un vector de informes
	 * 
	 * @return Devuelve el vector de informes 
	 */
	public Vector<Informe> desserializarJsonInforme() {
		Vector<Informe> informes = new Vector<Informe>();
		
		try (Reader reader = new FileReader("Informes.json")) {
			Gson gson = new Gson();
			Type tipoListaInformes = new TypeToken<Vector<Informe>>(){}.getType();
			informes = gson.fromJson(reader, tipoListaInformes);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
		return informes;
	}
	
}

