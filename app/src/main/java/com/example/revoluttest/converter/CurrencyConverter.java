package com.example.revoluttest.converter;

import com.example.domain.RevolutCurrencyRate;

class CurrencyConverter {

    private CurrencyConverter() {}

    static RevolutCurrencyRate getConvertedRate(RevolutCurrencyRate unconvertedRate, RevolutCurrencyRate base, double baseCount) {
        double convertedValue = getConvertedValue(unconvertedRate.getValue(), base.getValue(), baseCount);
        RevolutCurrencyRate convertedRate = new RevolutCurrencyRate(unconvertedRate.getCode(), base.getCode(), convertedValue);
        convertedRate.setValue(convertedValue);

        return convertedRate;
    }

    private static double getConvertedValue(double unconvertedRate, double chosenCurrencyRate, double baseCount) {
        return (unconvertedRate / chosenCurrencyRate) * baseCount;
    }

    static double getBaseCount(RevolutCurrencyRate baseRate, RevolutCurrencyRate chosenRate, double chosenCount) {
        return (chosenCount / chosenRate.getValue()) * baseRate.getValue();
    }
}
