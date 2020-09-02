package it.objectmethod.world.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.world.services.dao.ICityDao;
import it.objectmethod.world.services.model.City;

@RestController
public class CityController {

	@Autowired
	private ICityDao cityDao;

	@GetMapping("/cities")
	public List<City> getCityListByCountrycode(@RequestParam(name = "countrycode") String countrycode) {
		List<City> cityList = cityDao.getCityListByCountrycode(countrycode);
		return cityList;
	}

	@GetMapping("/search-cities-by-name")
	public List<City> searchCitiesByName(@RequestParam(name = "searchStr") String searchStr) {
		searchStr = "%" + searchStr + "%";
		List<City> cityList = cityDao.searchCitiesByName(searchStr);
		return cityList;
	}

	@GetMapping("/get-city-by-id")
	public City getCityById(@RequestParam(name = "id") int id) {
		City city = cityDao.getCityById(id);
		return city;
	}

	@PostMapping("/add-city")
	public ResponseEntity<String> addCity(@RequestBody City city) {
		ResponseEntity<String> resp = null;
		if (city.getPopulation() < 0) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			cityDao.addCity(city);
			String action = "city added";
			resp = new ResponseEntity<>(action, HttpStatus.ACCEPTED);
		}
		return resp;
	}

	@PutMapping("/update-city")
	public ResponseEntity<String> updateCity(@RequestBody City city) {
		ResponseEntity<String> resp = null;
		if (city.getPopulation() < 0) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			cityDao.updateCity(city);
			String action = "city updated";
			resp = new ResponseEntity<>(action, HttpStatus.ACCEPTED);
		}
		return resp;
	}
	
	@PutMapping("/delete-city")
	public String deleteCity(@RequestParam(name = "id") int id) {
		cityDao.deleteCity(id);
		return "city deleted";
	}
}
