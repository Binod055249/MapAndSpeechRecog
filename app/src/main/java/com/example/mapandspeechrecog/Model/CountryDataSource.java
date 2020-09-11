package com.example.mapandspeechrecog.Model;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class CountryDataSource {

    public static final String COUNTRY_KEY = "country";
    public static final float MINIMAL_CONFIDENCE_LEVEL = 0.4f;
    public static final String DEFAULT_COUNTRY_NAME = "Canada";
    public static final double DEFAULT_COUNTRY_LATITUDE = 59.969151;
    public static final double DEFAULT_COUNTRY_LONGITUDE = -111.459050;
    public static final String DEFAULT_MESSAGE = "Welcome!";

    private Hashtable<String, String> countriesAndMessages;

    public CountryDataSource(Hashtable<String, String> countriesAndMessages){

        this.countriesAndMessages = countriesAndMessages;

    }

    public String matchWithMinimumConfidenceLevelOfUserWords (ArrayList<String> userWords,
                                                             float[] confidenceLevels) {

        if (userWords == null || confidenceLevels == null) {

            return DEFAULT_COUNTRY_NAME;

        }

        int numberOfUserWord = userWords.size();

        Enumeration<String> countries;

        for (int index = 0; index < numberOfUserWord && index < confidenceLevels.length; index ++){

            if (confidenceLevels[index] < MINIMAL_CONFIDENCE_LEVEL) {

                break;
            }

            String acceptedUserWord = userWords.get(index);

            countries = countriesAndMessages.keys();

            while (countries.hasMoreElements()) {

                String selectedCountry = countries.nextElement();

                if (acceptedUserWord.equalsIgnoreCase(selectedCountry)) {

                    return acceptedUserWord;
                }
            }
        }

        return DEFAULT_COUNTRY_NAME;
    }

    public String getTheInfoOfTheCountry(String country) {

        return countriesAndMessages.get(country);
    }


}
