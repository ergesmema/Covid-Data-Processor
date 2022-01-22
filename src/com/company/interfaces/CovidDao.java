package com.company.interfaces;

import com.company.model.Country;
import com.company.model.Covid;

public interface CovidDao extends BaseDao<Covid, String>{
    Country getCountryData(String isoCode);
}
