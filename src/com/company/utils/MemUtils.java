package com.company.utils;

import com.company.factory.DaoFactory;
import com.company.interfaces.CountryDao;
import com.company.model.Continent;
import com.company.model.Country;
import com.company.model.Covid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class MemUtils {

    public static Continent newContinent(List<String> continentList, List<String> columns){
        Continent continent = new Continent();
        continent.setContinentId(Objects.hash(continentList.get(columns.indexOf("continent"))));
        continent.setContinentName(continentList.get(columns.indexOf("continent")));
        return continent;
    }

    public static Country newCountry(List<String> countryList, List<String> columns){
        CountryDao countryDao = DaoFactory.createCountryDao();
        Country country = new Country();
        country.setCountryIsoCode(countryList.get(columns.indexOf("iso_code")));
        country.setLocation(countryList.get(columns.indexOf("location")));
        country.setContinentId(countryDao.getContinentIdFromName(countryList.get(columns.indexOf("continent"))));
        country.setMedianAge(ParseDouble(countryList.get(columns.indexOf("median_age"))));
        country.setPopulation(ParseLong(countryList.get(columns.indexOf("population"))));
        return country;
    }

    public static Covid newCovid(List<String> CovidList, List<String> columns){
        Covid covid = new Covid();
        covid.setIsoCode(CovidList.get(columns.indexOf("iso_code")));
        covid.setDate(ParseDate(CovidList.get(columns.indexOf("date"))));
        covid.setTotalCases(ParseLong(CovidList.get(columns.indexOf("total_cases"))));
        covid.setNewCases(ParseLong(CovidList.get(columns.indexOf("new_cases"))));
        covid.setNewCasesSmoothed(ParseDouble(CovidList.get(columns.indexOf("new_cases_smoothed"))));
        covid.setTotalDeaths(ParseLong(CovidList.get(columns.indexOf("total_deaths"))));
        covid.setNewDeaths(ParseLong(CovidList.get(columns.indexOf("new_deaths"))));
        covid.setNewDeathsSmoothed(ParseDouble(CovidList.get(columns.indexOf("new_deaths_smoothed"))));
        covid.setReproductionRate(ParseDouble(CovidList.get(columns.indexOf("reproduction_rate"))));
        covid.setNewTests(ParseLong(CovidList.get(columns.indexOf("new_tests"))));
        covid.setTotalTests(ParseLong(CovidList.get(columns.indexOf("total_tests"))));
        covid.setStringencyIndex(ParseDouble(CovidList.get(columns.indexOf("stringency_index"))));
        covid.setNewDeathsPerCase(ParseDouble(CovidList.get(columns.indexOf("new_deaths"))),ParseDouble(CovidList.get(columns.indexOf("new_cases"))));
        return covid;
    }

    public static Double ParseDouble(String strNumber) {
        if (strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                e.printStackTrace();   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        return null;
    }

    public static Long ParseLong(String strNumber) {
        if (strNumber.length() > 0) {
            try {
                return Long.parseLong(String.valueOf(Math.round(Double.parseDouble(strNumber))));
            } catch (Exception e) {
                e.printStackTrace();   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        return null;
    }

    public static Date ParseDate(String strNumber) {
        List<String> formatStrings = Arrays.asList("MM/dd/yyyy", "yyyy-MM-dd");
        for (String formatString : formatStrings) {
            DateFormat d = new SimpleDateFormat(formatString);
            if (strNumber.length() > 0) {
                try {
                    return d.parse(strNumber);
                } catch (Exception ignored) {

                }
            }

        }
        return null;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static String FormatDate(Date strNumber) {
        DateFormat d = new SimpleDateFormat("MM/dd/yyyy");
        return d.format(strNumber);
    }
}
