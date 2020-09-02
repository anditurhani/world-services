package it.objectmethod.world.services.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.world.services.dao.ICityDao;
import it.objectmethod.world.services.model.City;

@Service
public class CityDaoImpl extends NamedParameterJdbcDaoSupport implements ICityDao {

	@Autowired
	public CityDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}

	public List<City> getCityListByCountrycode(String countrycode) {
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE countrycode = ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		List<City> cityList = getJdbcTemplate().query(sql, new Object[] { countrycode }, rm);
		return cityList;
	}

	public List<City> searchCitiesByName(String searchStr) {
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE name LIKE ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		List<City> cityList = getJdbcTemplate().query(sql, new Object[] { searchStr }, rm);
		return cityList;
	}

	public City getCityById(int id) {
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE id = ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		City city = getJdbcTemplate().queryForObject(sql, new Object[] { id }, rm);
		return city;
	}

	public void addCity(City city) {
		String sql = "INSERT INTO city (name, population, countrycode) VALUES (:cityname, :citypopulation, :citycountrycode)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cityname", city.getName());
		params.addValue("citypopulation", city.getPopulation());
		params.addValue("citycountrycode", city.getCountrycode());
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	public void updateCity(City city) {
		String sql = "UPDATE city SET (name = :cityname, population = :citypopulation, countrycode = :citycountrycode) WHERE id = :cityid";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cityname", city.getName());
		params.addValue("citypopulation", city.getPopulation());
		params.addValue("citycountrycode", city.getCountrycode());
		params.addValue("cityid", city.getId());
		getNamedParameterJdbcTemplate().update(sql, params);
	}

}
