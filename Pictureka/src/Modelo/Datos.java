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


public class Datos {
	/*
	public static void main(String[] args) {
		Datos miManejadorJson = new Datos();
		
		miManejadorJson.serializarArrayAJson();
		
		Vector<Usuario> empl = new Vector<Usuario>();
		empl = miManejadorJson.desserializarJsonAArray();
		Usuario Usuario1 = empl.get(0);
		Usuario Usuario2 = empl.get(1);
		Usuario Usuario3 = empl.get(2);
		
		System.out.println(Usuario1);
		System.out.println(Usuario2);
		System.out.println(Usuario3);
	}
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
	
	public void serializarVectorEventosAJson(Vector<Evento> eventos) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(eventos);
		
		try(FileWriter writer = new FileWriter("Eventos.json")){
			prettyGson.toJson(eventos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public void serializarStaffAJson(Vector<Staff> staff) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(staff);
		
		try(FileWriter writer = new FileWriter("staff.json")){
			prettyGson.toJson(staff, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
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
	
}

