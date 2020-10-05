package edu.sabanciuniv.cs310.fromfiletodb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


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
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "root");		
			for (Country s : countries)
			{
				PreparedStatement ps =  connection.prepareStatement("insert into country (name,continent,capital,population) values (?,?,?,?) ");
				ps.setString(1, s.getName());
				ps.setString(2, s.getContinent());
				ps.setString(3, s.getCapital());
				ps.setInt(4, s.getPopulation());
				
				ps.execute();
			}
			
			
			System.out.println("List is inserted");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static Country getCountryByID(int countryID) {
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "root");		
			

				PreparedStatement ps =  connection.prepareStatement("select * from country where id=?");
				ps.setInt(1, countryID);
		
				ResultSet results=ps.executeQuery();
				
				while (results.next())
				{
					String name=results.getString(2);
					String continent=results.getString(3);
					String capital=results.getString(4);
					int population=results.getInt("population");
					Country x=new Country(name,continent,capital,population);
					System.out.println(name+" is selected");
					return x;
				}
				
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	  
		
	}




	private static void Country(String name, String continent, String capital, int population) {
		// TODO Auto-generated method stub
		
	}



	public static void updateCountryPopulation(int id,int population) {
	
	
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "root");		
		

			PreparedStatement ps =  connection.prepareStatement("UPDATE country SET population = ? where id = ?");
			ps.setInt(1,population);
			ps.setInt(2, id);
			
			ps.execute();
	

		
		System.out.println(id+" is updated "+population);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
	
	
	public static void deleteCountryByID(int id) {
		
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310", "root", "root");		
			

				PreparedStatement ps =  connection.prepareStatement("delete from country where id=?");
				ps.setInt(1,id);
				
				ps.execute();
		

			
			System.out.println(id+" is deleted");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
}

}
