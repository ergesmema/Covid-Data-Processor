package com.company.model;

import java.util.Date;
import java.util.Objects;

public class Covid {

    private String isoCode;
    private Date date;
    private Long totalCases;
    private Long newCases;
    private Double newCasesSmoothed;
    private Long totalDeaths;
    private Long newDeaths;
    private Double newDeathsSmoothed;
    private Double reproductionRate;
    private Long newTests;
    private Long totalTests;
    private Double stringencyIndex;
    private Double newDeathsPerCase;


    public Covid() {
    }

    public Covid(String isoCode, Date date, Long totalCases, Long newCases, Double newCasesSmoothed, Long totalDeaths, Long newDeaths, Double newDeathsSmoothed, Double reproductionRate, Long newTests, Long totalTests, Double stringencyIndex) {
        this.isoCode = isoCode;
        this.date=date;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.newCasesSmoothed = newCasesSmoothed;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.newDeathsSmoothed = newDeathsSmoothed;
        this.reproductionRate = reproductionRate;
        this.newTests = newTests;
        this.totalTests = totalTests;
        this.stringencyIndex = stringencyIndex;
        this.setNewDeathsPerCase(newDeaths, newCases);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Covid that = (Covid) o;
        return Objects.equals(isoCode, that.isoCode) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isoCode,date);
    }

    @Override
    public String toString() {
        return "Covid{" +
                "iso_code=" + isoCode +
                ", date='" + date + '\'' +
                ", total_cases='" + totalCases + '\'' +
                ", new_cases='" + newCases + '\'' +
                ", new_cases_smoothed='" + newCasesSmoothed + '\'' +
                ", total_deaths='" + totalDeaths + '\'' +
                ", new_deaths='" + newDeaths + '\'' +
                ", new_deaths_smoothed='" + newDeathsSmoothed + '\'' +
                ", reproduction_rate='" + reproductionRate + '\'' +
                ", new_tests='" + newTests + '\'' +
                ", total_tests='" + totalTests + '\'' +
                ", stringency_index='" + stringencyIndex + '\'' +
                ", new_deaths_per_case='" + newDeathsPerCase + '\'' +
                '}';
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }

    public Long getNewCases() {
        return newCases;
    }

    public void setNewCases(Long newCases) {
        this.newCases = newCases;
    }

    public Double getNewCasesSmoothed() {
        return newCasesSmoothed;
    }

    public void setNewCasesSmoothed(Double newCasesSmoothed) {
        this.newCasesSmoothed = newCasesSmoothed;
    }

    public Long getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Long getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Long newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Double getNewDeathsSmoothed() {
        return newDeathsSmoothed;
    }

    public void setNewDeathsSmoothed(Double newDeathsSmoothed) {
        this.newDeathsSmoothed = newDeathsSmoothed;
    }

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public void setReproductionRate(Double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public Long getNewTests() {
        return newTests;
    }

    public void setNewTests(Long newTests) {
        this.newTests = newTests;
    }

    public Long getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(Long totalTests) {
        this.totalTests = totalTests;
    }

    public Double getStringencyIndex() {
        return stringencyIndex;
    }

    public void setStringencyIndex(Double stringencyIndex) {
        this.stringencyIndex = stringencyIndex;
    }

    public Double getNewDeathsPerCase() {
        return newDeathsPerCase;
    }

    public void setNewDeathsPerCase(Double newDeaths, Double newCases) {
        if(newDeaths==null || newCases==null || newCases==0)
            this.newDeaths=null;
        else
            this.newDeathsPerCase = newDeaths/newCases;
    }

    public void setNewDeathsPerCase(Long newDeaths, Long newCases) {
        try {
            if(newCases!=0)
                this.newDeathsPerCase = Double.valueOf(newDeaths/newCases);
            else
                this.newDeathsPerCase=null;
        }catch (NullPointerException e){
            this.newDeathsPerCase = null;
        }
    }
}
