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

	@GetMapping("/search-cities")
	public List<City> searchCities(@RequestParam(name = "searchStr") String searchStr,
			@RequestParam(name = "countrycode") String countrycode) {
		searchStr = "%" + searchStr + "%";
		List<City> cityList = cityDao.searchCities(searchStr, countrycode);
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
			resp = new ResponseEntity<>("city added", HttpStatus.ACCEPTED);
		}
		return resp;
	}

	@PutMapping("/update-city")
	public ResponseEntity<String> updateCity(@RequestBody City city) {
		ResponseEntity<String> resp = null;
		int num = 0;
		if (city.getPopulation() > 0) {
			num = cityDao.updateCity(city);
		} else {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (num > 0) {
			resp = new ResponseEntity<>("city added", HttpStatus.ACCEPTED);
		} else {
			resp = new ResponseEntity<>("city not found", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@PutMapping("/delete-city")
	public ResponseEntity<String> deleteCity(@RequestParam(name = "id") int id) {
		ResponseEntity<String> resp = null;
		int num = cityDao.deleteCity(id);
		if (num > 0) {
			resp = new ResponseEntity<>("city deleted", HttpStatus.ACCEPTED);
		} else {
			resp = new ResponseEntity<>("city not found", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}
