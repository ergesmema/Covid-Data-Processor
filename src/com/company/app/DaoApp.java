package com.company.app;

import com.company.CommandLineParser;
import com.company.factory.DaoFactory;
import com.company.interfaces.ContinentDao;
import com.company.interfaces.CountryDao;
import com.company.interfaces.CovidDao;
import com.company.model.Covid;
import com.company.utils.MemUtils;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class DaoApp {
    private final Properties properties;
    private static CovidDao covidDao;
    private static CountryDao countryDao;
    private static ContinentDao continentDao;

    public static void main(String[] args){
        CommandLineParser clp = new CommandLineParser(args);
        String file = clp.getArgumentValue("file");
        String stat= clp.getArgumentValue("stat");
        String by = clp.getArgumentValue("by");
        String display = clp.getArgumentValue("display");
        long limit = Long.parseLong(clp.getArgumentValue("limit"));

        try {
            DaoApp app = new DaoApp();
            app.readCSV(file);
            app.processUserReq(stat, limit, by, display);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public DaoApp() {
        properties = new Properties();
        try(InputStream is = new FileInputStream("resources/application.properties")) {
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCSV(String file) throws IOException{
        List<String> readLines;
        try {
            readLines = new BufferedReader(new FileReader(file)).lines().toList();
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist or the path is incorrect. Please try again!");

        }
        List<String> columns = readColumns(readLines);
        readContinents(readLines, columns);
        readCountries(readLines, columns);
        readCovidData(readLines, columns);
    }

    public void processUserReq(String stat, Long limit, String by, String display) {
        covidDao = DaoFactory.createCovidDao();
        continentDao = DaoFactory.createContinentDao();
        countryDao = DaoFactory.createCountryDao();
        Comparator compare;

        Stream<Covid> covidStream = covidDao.findAll()
                .stream().parallel();

        switch (by) {
            case "NC" -> {
                compare = Comparator.comparing(Covid::getNewCases);
                covidStream = covidStream.filter(x -> x.getNewCases() != null);
            }
            case "NCS" -> {
                compare = Comparator.comparing(Covid::getNewCasesSmoothed);
                covidStream = covidStream.filter(x -> x.getNewCasesSmoothed() != null);
            }
            case "ND" -> {
                compare = Comparator.comparing(Covid::getNewDeaths);
                covidStream = covidStream.filter(x -> x.getNewDeaths() != null);
            }
            case "NDS" -> {
                compare = Comparator.comparing(Covid::getNewDeathsSmoothed);
                covidStream = covidStream.filter(x -> x.getNewDeathsSmoothed() != null);
            }
            case "NT" -> {
                compare = Comparator.comparing(Covid::getNewTests);
                covidStream = covidStream.filter(x -> x.getNewTests() != null);
            }
            case "NDPC" -> {
                compare = Comparator.comparing(Covid::getNewDeathsPerCase);
                covidStream = covidStream.filter(x -> x.getNewDeathsPerCase() != null);
            }
            default ->  throw new IllegalArgumentException("Invalid 'by' argument input! Please check and try again");

        }

        switch (stat) {
            case "MAX" -> covidStream = covidStream
                                        .sorted(compare.reversed());

            case "MIN" -> covidStream = covidStream.sorted(compare);

            default ->  throw new IllegalArgumentException("Invalid 'stat' argument input! Please check and try again");

        }

        if(!(limit>=1 && limit<=100))
            throw new IllegalArgumentException("Invalid 'limit' argument input! Please check and try again");

        covidStream = covidStream
                .limit(limit);


        switch (display) {
            case "DATE" -> covidStream
                    .forEach(covid -> System.out.println(MemUtils.FormatDate(covid.getDate())));
            case "COUNTRY" -> covidStream
                    .forEach(country -> System.out.println(covidDao.getCountryData(country.getIsoCode()).getLocation()));
            case "CONTINENT" -> covidStream
                    .forEach( continent -> System.out.println((continentDao.find(countryDao.find(continent.getIsoCode()).getContinentId()).getContinentName())));
            default ->  throw new IllegalArgumentException("Invalid 'display' argument input! Please check and try again");

        }
    }

    public  List<String> readColumns(List<String> file) {
        return file.stream().findFirst()
                .map(line -> Arrays.asList(line.split(",")))
                .get();
    }

    public void readContinents(List<String> file, List<String> columns) {
        continentDao = DaoFactory.createContinentDao();

        file.stream().parallel()
                .skip(1)
                .map(line -> Arrays.asList(line.split(",", -1)))
                .filter(MemUtils.distinctByKey(line -> line.get(columns.indexOf("continent"))))
                .map(line -> MemUtils.newContinent(line, columns))
                .forEach(continent -> continentDao.insert(continent));
    }


    public void readCountries(List<String> file, List<String> columns) {
        countryDao = DaoFactory.createCountryDao();

        file.stream().parallel()
                .skip(1)
                .map(line -> Arrays.asList(line.split(",", -1)))
                .filter(MemUtils.distinctByKey(line -> line.get(columns.indexOf("iso_code"))))
                .map(line -> MemUtils.newCountry(line, columns))
                .forEach(country -> countryDao.insert(country));
    }

    public void readCovidData(List<String> file, List<String> columns) {
        covidDao = DaoFactory.createCovidDao();

        file.stream().parallel()
                .skip(1)
                .map(line -> Arrays.asList(line.split(",", -1)))
                .map(line -> MemUtils.newCovid(line, columns))
                .forEach(covidData->covidDao.insert(covidData));
    }
}
