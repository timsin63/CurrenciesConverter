package com.example.revoluttest.converter;

import com.example.domain.RevolutCurrencyRate;

import java.util.ArrayList;
import java.util.List;

public class RateList implements DependentConvertableList<RevolutCurrencyRate> {

    private RevolutCurrencyRate base;
    private RevolutCurrencyRate chosenRate;
    private double chosenCount;
    private List<RevolutCurrencyRate> rates;

    public RateList(RevolutCurrencyRate base) {
        this(base, new ArrayList<>());
    }

    public RateList(RevolutCurrencyRate base, List<RevolutCurrencyRate> rates) {
        this.base = base;
        this.chosenRate = base;
        this.rates = rates;
        this.chosenCount = base.getValue();
    }

    @Override
    public void chooseItem(RevolutCurrencyRate chosenItem) {
        this.chosenRate = chosenItem;
        setChosenCount(chosenItem.getValue());
    }

    @Override
    public void setChosenCount(double value) {
        this.chosenCount = value;
    }

    public double getChosenCount() {
        return chosenCount;
    }

    @Override
    public void updateList(List<RevolutCurrencyRate> newList) {
        updateList(base, newList);
    }

    @Override
    public void updateList(RevolutCurrencyRate base, List<RevolutCurrencyRate> newList) {
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

            RevolutCurrencyRate convertedRate = CurrencyConverter.getConvertedRate(rate, chosenRate, chosenCount);
            convertedRates.add(convertedRate);
        }
        convertedRates.add(0, chosenRate);

        return convertedRates;
    }
}
