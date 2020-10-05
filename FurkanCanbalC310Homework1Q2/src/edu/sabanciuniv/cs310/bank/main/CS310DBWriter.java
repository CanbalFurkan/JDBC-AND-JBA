package edu.sabanciuniv.cs310.bank.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;




public class CS310DBWriter {
	
	public static ArrayList<Country> readFromFile(String filename)
	{
		ArrayList<Country> countries= new ArrayList<Country>();
		try 
		{
			FileReader reader = new FileReader("world.txt");
			BufferedReader bfr = new BufferedReader(reader);
			while(true)
			{
				String line = bfr.readLine();
				if (line == null)
				{
					break;
				}
				//System.out.println(line);
				String[] arr = line.split(",");
				String name = arr[0];
				String continent = arr[1];
				String capital = arr[2];
				String population = arr[3];
				int pop=Integer.parseInt(population);
				Country s = new Country(name, continent,capital,pop);
				
				countries.add(s);
			}
			reader.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("no have rights to read that file");
			e.printStackTrace();
		}
		return countries;
	}

	
	public static void writeIntoTable(ArrayList<Country> countries) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager  =emf.createEntityManager();
		entityManager.getTransaction().begin();
		try
		{
			
		
				
			for (Country s : countries)
			{
				
			
				entityManager.persist(s);
			
			
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		entityManager.getTransaction().commit();
		System.out.println("List is inserted");
}
	
	
	public static Country getCountryByID(int countryID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager  =emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Country x=entityManager.find(Country.class,countryID);
		return x;
	}




	public static void updateCountryPopulation(int id,int population) {
	
	
	try
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager  =emf.createEntityManager();	
		


		entityManager.getTransaction().begin();
		
			Country x=entityManager.find(Country.class,id);
			x.setPopulation(population);
			entityManager.merge(x);
			entityManager.getTransaction().commit();

		
		System.out.println(id+" is updated "+population);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
	
	
	public static void deleteCountryByID(int id) {
		
		
		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
			EntityManager entityManager  =emf.createEntityManager();	
			


			entityManager.getTransaction().begin();
			
			Country x=entityManager.find(Country.class,id);
		      entityManager.remove( x );
		      entityManager.getTransaction().commit();

			
			System.out.println(id+" deleted ");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
}

}
