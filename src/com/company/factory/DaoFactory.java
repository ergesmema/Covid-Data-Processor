package com.company.factory;

import com.company.impl.ContinentDaoInMemoryImpl;
import com.company.impl.CountryDaoInMemoryImpl;
import com.company.impl.CovidDaoInMemoryImpl;
import com.company.interfaces.ContinentDao;
import com.company.interfaces.CountryDao;
import com.company.interfaces.CovidDao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    static {
        String model = "memory";
        try(InputStream is = new FileInputStream("resources/application.properties")) {
            Properties props = new Properties();
            props.load(is);
            model = props.getProperty("db.factory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        factoryModel = model;
    }

    private static final String factoryModel;
    private static CountryDaoInMemoryImpl memCountryDao;
    private static CovidDaoInMemoryImpl memCovidDao;
    private static ContinentDaoInMemoryImpl memContinentDao;

    public static CountryDao createCountryDao() {
        if ("memory".equals(factoryModel)) {
            if (memCountryDao == null) {
                memCountryDao = new CountryDaoInMemoryImpl();
            }
            return memCountryDao;
        }

        throw new IllegalArgumentException("Illegal factory model: " + factoryModel);
    }

    public static ContinentDao createContinentDao() {
        if ("memory".equals(factoryModel)) {
            if (memContinentDao == null) {
                memContinentDao = new ContinentDaoInMemoryImpl();
            }
            return memContinentDao;
        }

        throw new IllegalArgumentException("Illegal factory model: " + factoryModel);
    }


    public static CovidDao createCovidDao() {
        if ("memory".equals(factoryModel)) {
            if (memCovidDao == null) {
                memCovidDao = new CovidDaoInMemoryImpl();
            }
            return memCovidDao;
        }

        throw new IllegalArgumentException("Illegal factory model: " + factoryModel);
    }

}
