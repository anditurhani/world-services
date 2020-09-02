package it.objectmethod.world.services.dao;

import java.util.List;

import it.objectmethod.world.services.model.City;

public interface ICityDao {

	public List<City> getCityListByCountrycode(String countrycode);

	public List<City> searchCitiesByName(String searchStr);

	public City getCityById(int id);

	public void addCity(City city);

	public int updateCity(City city);

	public int deleteCity(int id);

}
