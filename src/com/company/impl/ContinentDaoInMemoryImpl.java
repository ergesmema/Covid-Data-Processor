package com.company.impl;

import com.company.interfaces.ContinentDao;
import com.company.model.Continent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ContinentDaoInMemoryImpl implements ContinentDao {
    private Map<Integer, Continent> table;

    public ContinentDaoInMemoryImpl() {
        table = new ConcurrentHashMap<>();
    }

    @Override
    public Continent find(Integer key) {

        Continent continent = table.get(key);
        if(continent==null)
            throw new NullPointerException("continent is null");
        if(continent.getContinentName().equals("") || continent.getContinentName()==null){
            continent.setContinentName("Continent not defined with id:"+continent.getContinentId());
        }
        return continent;
    }

    public Integer getKeyByValue(String value) {
        return table.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue().getContinentName().toUpperCase(), value.toUpperCase()))
                .findFirst().get().getKey();
    }

    @Override
    public List<Continent> findAll() {
        return new ArrayList<>(table.values());
    }

    @Override
    public void insert(Continent continent) {
        Integer key = continent.hashCode();
        if(table.containsKey(key))
            throw new IllegalArgumentException("already existing continent: " + key);
        table.put(key, continent);
    }

    @Override
    public void update(Continent continent) {
        Integer key = continent.hashCode();
        if(table.containsKey(key))
            table.put(key, continent);
        else
            throw new NullPointerException("Record was not found");
    }

    @Override
    public void delete(Continent continent) {
        table.remove(continent.hashCode());
    }

}

