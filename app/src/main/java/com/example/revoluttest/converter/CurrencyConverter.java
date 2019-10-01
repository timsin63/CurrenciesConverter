package com.example.revoluttest.converter;

import com.example.domain.RevolutCurrencyRate;

public class CurrencyConverter {

    private CurrencyConverter() {}

    public static RevolutCurrencyRate getConvertedRate(RevolutCurrencyRate unconvertedRate, RevolutCurrencyRate base, double baseCount) {
        double convertedValue = getConvertedValue(unconvertedRate.getValue(), base.getValue(), baseCount);
        RevolutCurrencyRate convertedRate = new RevolutCurrencyRate(unconvertedRate.getCode(), base.getCode(), convertedValue);
        convertedRate.setValue(convertedValue);

        return convertedRate;
    }

    public static double getConvertedValue(double unconvertedRate, double chosenCurrencyRate, double baseCount) {
//        return (chosenCurrencyRate * baseCount) * unconvertedRate;
        return (unconvertedRate / chosenCurrencyRate) * baseCount;
    }
}
