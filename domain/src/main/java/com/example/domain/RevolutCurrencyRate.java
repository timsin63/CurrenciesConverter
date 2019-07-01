package com.example.domain;

import java.util.Currency;
import java.util.Locale;

public class RevolutCurrencyRate {
    private final String code;
    private final Currency currency;
    private final String base;
    private double value;

    public RevolutCurrencyRate(String code, String base, double value) {
        this.code = code;
        this.base = base;
        this.value = value;
        this.currency = Currency.getInstance(code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return currency.getDisplayName(Locale.UK);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        RevolutCurrencyRate rate = (RevolutCurrencyRate) o;

        return rate != null
                && this.getCode().equals(rate.getCode())
                && this.getName().equals(rate.getName())
                && this.getValue() == rate.getValue();
    }
}
