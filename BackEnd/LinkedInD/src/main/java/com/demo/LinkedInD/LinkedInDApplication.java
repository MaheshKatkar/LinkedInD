package com.demo.LinkedInD;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class LinkedInDApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedInDApplication.class, args);

		// Output the generated secret key

	}

}
