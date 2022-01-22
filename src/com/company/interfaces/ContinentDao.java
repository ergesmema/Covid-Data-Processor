package com.company.interfaces;

import com.company.model.Continent;

public interface ContinentDao extends BaseDao<Continent, Integer>{
    Integer getKeyByValue(String continentName);
}