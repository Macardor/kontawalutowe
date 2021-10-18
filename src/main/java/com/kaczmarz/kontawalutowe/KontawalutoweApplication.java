package com.kaczmarz.kontawalutowe;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class KontawalutoweApplication {

	public static void main(String[] args) throws IOException, ParseException {

		SpringApplication.run(KontawalutoweApplication.class, args);
	}

}
