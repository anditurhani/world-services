package it.objectmethod.world.services.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.world.services.dao.ICountryDao;
import it.objectmethod.world.services.model.Country;

@Service
public class CountryDaoImpl extends NamedParameterJdbcDaoSupport implements ICountryDao {

	@Autowired
	public CountryDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}

	public List<String> getAllContinents() {
		String sql = "SELECT DISTINCT continent FROM country";
		List<String> continents = getJdbcTemplate().queryForList(sql, String.class);
		return continents;
	}

	public List<Country> getCountryListByContinent(String continent) {
		String sql = "SELECT code code, name name, population population, continent continent FROM country WHERE continent = ?";
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<Country>(Country.class);
		List<Country> countryList = getJdbcTemplate().query(sql, new Object[] { continent }, rm);
		return countryList;
	}
	
	public List<Country> getAllCountries() {
		String sql = "SELECT code code, name name, population population, continent continent FROM country";
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<Country>(Country.class);
		List<Country> countryList = getJdbcTemplate().query(sql, rm);
		return countryList;
	}

}
