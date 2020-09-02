package it.objectmethod.world.services.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.world.services.dao.ICountryDao;
import it.objectmethod.world.services.model.Country;

@RestController
public class CountryController {

	@Autowired
	ICountryDao countryDao;

	@GetMapping("/continents")
	public List<String> getAllContinents() {
		List<String> continents = countryDao.getAllContinents();
		return continents;
	}

	@GetMapping("/countries")
	public List<Country> getCountryByContinent(@RequestParam(name = "continent", required = false) String continent, HttpSession session) {
		if (continent != null) {
			session.setAttribute("continent", continent);
		} else {
			continent = (String) session.getAttribute("continent");
		}
		List<Country> countryList = countryDao.getCountryListByContinent(continent);
		return countryList;
	}

	@GetMapping("get-all-countries")
	public List<Country> getAllCountries() {
		List<Country> countryList = countryDao.getAllCountries();
		return countryList;
	}

}
