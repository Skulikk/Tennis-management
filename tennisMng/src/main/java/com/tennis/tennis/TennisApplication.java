package com.tennis.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TennisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisApplication.class, args);
	}

}

//TODO List:
/*
vytvořit rezervaci
	kontrola existujícího zákazníka - jinak založit nového
	kontrola překrývání časů na kurtu
	vrátit cenu rezervace
zobrazit registrace pro kurt
zobrazit registrace pro telefonní číslo
	rezervace v budoucnosti
ošetření chybných dotazů 400
unit testy
dokumentace
UML a Class diagram
Autentizace
 */
