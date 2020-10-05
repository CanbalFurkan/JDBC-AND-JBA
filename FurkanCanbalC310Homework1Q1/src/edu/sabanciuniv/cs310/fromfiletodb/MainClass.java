package edu.sabanciuniv.cs310.fromfiletodb;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) 
	{
		
		 
		ArrayList<Country> AllCountries =
				CS310DBWriter.readFromFile("world.txt");
		
		CS310DBWriter.writeIntoTable(AllCountries);
		CS310DBWriter.deleteCountryByID(2500);
		Country z=CS310DBWriter.getCountryByID(25001);
		CS310DBWriter.updateCountryPopulation(2501, 1);
		
		
		

	}

}
