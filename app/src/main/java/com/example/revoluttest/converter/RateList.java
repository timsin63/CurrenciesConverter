package com.example.revoluttest.converter;

import com.example.domain.RevolutCurrencyRate;

import java.util.ArrayList;
import java.util.List;

public class RateList implements DependentConvertableList<RevolutCurrencyRate> {

    private RevolutCurrencyRate base;
    private RevolutCurrencyRate chosenRate;
    private double baseCount;
    private List<RevolutCurrencyRate> rates;

    public RateList(RevolutCurrencyRate base) {
        this(base, new ArrayList<>());
    }

    public RateList(RevolutCurrencyRate base, List<RevolutCurrencyRate> rates) {
        this.base = base;
        this.chosenRate = base;
        this.rates = rates;
        this.baseCount = base.getValue();
    }

    @Override
    public void chooseItem(RevolutCurrencyRate chosenItem) {
        this.chosenRate = new RevolutCurrencyRate(chosenItem.getCode(), base.getCode(), chosenItem.getValue() / baseCount);
    }

    @Override
    public void setBaseCount(double value) {
        this.baseCount = CurrencyConverter.getBaseCount(base, chosenRate, value);
    }

    public double getBaseCount() {
        return baseCount;
    }

    @Override
    public void updateList(List<RevolutCurrencyRate> newList) {
        updateList(base, newList);
    }

    @Override
    public synchronized void updateList(RevolutCurrencyRate base, List<RevolutCurrencyRate> newList) {
        if (this.base == null) {
            this.base = base;
        }
        this.rates = new ArrayList<>(newList);
        rates.add(0, base);
    }

    @Override
    public List<RevolutCurrencyRate> getConvertedValues() {
        List<RevolutCurrencyRate> convertedRates = new ArrayList<>();

        for (RevolutCurrencyRate rate : rates) {
            if (rate.getCode().equals(chosenRate.getCode())) continue;

            RevolutCurrencyRate convertedRate = CurrencyConverter.getConvertedRate(rate, base, baseCount);
            convertedRates.add(convertedRate);
        }
        RevolutCurrencyRate chosen = new RevolutCurrencyRate(chosenRate.getCode(), chosenRate.getCode(), chosenRate.getValue() * baseCount);
        convertedRates.add(0, chosen);

        return convertedRates;
    }
}
