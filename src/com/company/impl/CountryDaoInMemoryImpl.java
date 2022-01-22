package com.company.impl;

import com.company.factory.DaoFactory;
import com.company.interfaces.ContinentDao;
import com.company.interfaces.CountryDao;
import com.company.interfaces.CovidDao;
import com.company.model.Country;
import com.company.model.Covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CountryDaoInMemoryImpl implements CountryDao {

    private Map<String, Country> table;

    public CountryDaoInMemoryImpl() {
        table = new ConcurrentHashMap<>();
    }

    @Override
    public Country find(String key) {
        Country country = table.get(key);
        if(country==null) {
            throw new NullPointerException("no such country with key: " + key);
        }
        return country;
    }

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(table.values());
    }

    @Override
    public void insert(Country country) {
        String key = country.getCountryIsoCode();
        if(table.containsKey(key))
            throw new IllegalArgumentException("already existing country: " + key);
        table.put(key, country);
    }

    @Override
    public void update(Country country) {
        String key = country.getCountryIsoCode();
        if(table.containsKey(key))
            table.put(key, country);
        else
            throw new NullPointerException("Record was not found");
    }

    @Override
    public void delete(Country country) {
        table.remove(country.getCountryIsoCode());
    }

    @Override
    public List<Covid> getCovidData(Country country) {
        CovidDao covidDao = DaoFactory.createCovidDao();
        return covidDao.findAll()
                .stream()
                .filter(i -> i.getIsoCode().equals(country.getCountryIsoCode()))
                .collect(Collectors.toList());
    }
    @Override
    public Integer getContinentIdFromName(String continentName){
        ContinentDao continentDao = DaoFactory.createContinentDao();
        return continentDao.getKeyByValue(continentName);
    }
}
