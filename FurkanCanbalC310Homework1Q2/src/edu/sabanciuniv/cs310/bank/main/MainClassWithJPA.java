package edu.sabanciuniv.cs310.bank.main;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MainClassWithJPA {

	public static void main(String[] args) {
		
		ArrayList<Country> AllCountries =
				CS310DBWriter.readFromFile("world.txt");
		
		CS310DBWriter.writeIntoTable(AllCountries);
		
		Country x=CS310DBWriter.getCountryByID(1800);
		CS310DBWriter.updateCountryPopulation(1800, 1);
		CS310DBWriter.deleteCountryByID(1801);
		
		

		
	}

}
