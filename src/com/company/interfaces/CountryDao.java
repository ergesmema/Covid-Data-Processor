package com.company.interfaces;

import com.company.model.Country;
import com.company.model.Covid;

import java.util.List;

public interface CountryDao extends BaseDao<Country, String>{
    List<Covid> getCovidData(Country country);
    Integer getContinentIdFromName(String continentName);
}
