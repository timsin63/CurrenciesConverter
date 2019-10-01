package com.example.revoluttest.flags;

import com.blongho.country_data.Country;
import com.blongho.country_data.World;

import java.util.HashMap;
import java.util.Map;

public class CurrencyFlagsProvider {

    private static CurrencyFlagsProvider instance;

    private final Map<String, Integer> flagsMap;

    private CurrencyFlagsProvider() {
        Map<String, Integer> flags = new HashMap<>();

        for (Country country : World.getAllCountries()) {
            if (country.getCurrency() == null) continue;

            String currencyCode = country.getCurrency().getCode();
            int flagResource = country.getFlagResource();

            flags.put(currencyCode, flagResource);
        }

        flagsMap = new HashMap<>(flags);
    }

    public static CurrencyFlagsProvider getInstance() {
        if (instance == null) {
            instance = new CurrencyFlagsProvider();
        }

        return instance;
    }

    public int getCurrencyFlag(String currencyCode) {
        Integer res = flagsMap.get(currencyCode);
        int flagResource = res != null ? res : 0;

        return flagResource != 0 ? flagResource : World.getWorldFlag();
    }
}
