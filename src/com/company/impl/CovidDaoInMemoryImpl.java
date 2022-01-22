package com.company.impl;

import com.company.factory.DaoFactory;
import com.company.interfaces.CovidDao;
import com.company.model.Country;
import com.company.model.Covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CovidDaoInMemoryImpl implements CovidDao {

    private Map<Integer, Covid> table;

    public CovidDaoInMemoryImpl() {
        table = new ConcurrentHashMap<>();
    }

    @Override
    public List<Covid> findAll() {
        return new ArrayList<>(table.values());
    }

    @Override
    public void insert(Covid covid) {
        int key = covid.hashCode();
        if (table.containsKey(key))
            throw new IllegalArgumentException("already existing covid: " + key);
        table.put(key, covid);
    }

    @Override
    public void update(Covid covid) {
        int key = covid.hashCode();
        if ( table.containsKey(key) )
            table.put(key, covid);
        else
            throw new NullPointerException("Record was not found");
    }

    @Override
    public void delete(Covid covid) {
        table.remove(covid.hashCode());
    }


    @Override
    public Covid find(String key) {
        return table.get(key);
    }

    @Override
    public Country getCountryData(String isoCode) {
        return DaoFactory
                .createCountryDao()
                .find(isoCode);
    }
}
