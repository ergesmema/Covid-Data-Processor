package com.company.model;
import java.util.Objects;

public class Continent{
    private Integer continentId;
    private String continentName;

    public Continent(){}

    public Continent(Integer continentId, String continentName){
        this.continentId=continentId;
        this.continentName=continentName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent that = (Continent) o;
        return Objects.equals(continentId, that.continentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(continentName);
    }

    @Override
    public String toString() {
        return "Continent{" +
                "continent_id=" + continentId +
                ", continent_name='" + continentName + '\'';

    }

}
