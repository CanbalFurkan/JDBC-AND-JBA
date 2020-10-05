package edu.sabanciuniv.cs310.fromfiletodb;

public class Country {
	
	private String name;
	private String continent;
	private String capital;
	private int    population;

	
	public Country() {
		super();
	}
	public Country(String name, String continent,String capital,int population) {
		super();
		this.name = name;
		this.continent = continent;
		this.capital= capital;
		this.population=population;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
	

}
