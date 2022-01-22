package com.company.model;

import java.util.Objects;

public class Country {
    private String countryIsoCode;
    private String location;
    private Integer continentId;
    private Long population;
    private Double medianAge;

    public Country()
    {
    }

    public Country(String countryIsoCode, String location, Integer continentId, Long population, Double medianAge) {
        this();
        this.countryIsoCode = countryIsoCode;
        this.location = location;
        this.continentId = continentId;
        this.population = population;
        this.medianAge = medianAge;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(Double medianAge) {
        this.medianAge = medianAge;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country that = (Country) o;
        return Objects.equals(countryIsoCode, that.countryIsoCode);
    }

    public int hashCode() {
        return Objects.hash(countryIsoCode);
    }

    @Override
    public String toString() {
        return "Country{" +
                "iso_code='" + countryIsoCode + '\'' +
                ", location='" + location + '\'' +
                ", continent_id='" + continentId + '\'' +
                ", population=" + population +
                ", median_age=" + medianAge +
                '}';
    }







}
