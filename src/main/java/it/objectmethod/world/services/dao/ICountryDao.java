package it.objectmethod.world.services.dao;

import java.util.List;

import it.objectmethod.world.services.model.Country;

public interface ICountryDao {

	public List<String> getAllContinents();

	public List<Country> getCountryListByContinent(String continent);
	
	public List<Country> getAllCountries();

}
