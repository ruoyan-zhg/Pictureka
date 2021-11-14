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

	public void serializarArrayAJson(Vector<Usuario> usuarios) {
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionVisible = prettyGson.toJson(usuarios);
		System.out.println(representacionVisible);
		
		try(FileWriter writer = new FileWriter("usuarios.json")){
			prettyGson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public Vector<Usuario> desserializarJsonAusuarios() {
		Vector<Usuario> usuarios = new Vector<Usuario>();
		
		try (Reader reader = new FileReader("usuarios.json")) {
			Gson gson = new Gson();
			Type tipoListausuarios = new TypeToken<Vector<Usuario>>(){}.getType();
			usuarios = gson.fromJson(reader, tipoListausuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return usuarios;
	}
}

