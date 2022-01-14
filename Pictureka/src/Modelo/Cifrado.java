package Modelo;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class Cifrado {

	
	public String hashing(String pass) {
        String sha256hex = Hashing.sha256()
                  .hashString(pass, StandardCharsets.UTF_8)
                  .toString();

        return sha256hex;

    }
	
	
}
