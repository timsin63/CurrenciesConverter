package com.example.domain;

import java.util.Currency;
import java.util.Locale;

public class RevolutCurrencyRate {
    private final String code;
    private final String flagImageUrl;
    private final Currency currency;
    private final String base;
    private double value;

    public RevolutCurrencyRate(String code, String base, double value) {
        this.code = code;
        this.base = base;
        this.value = value;
        this.currency = Currency.getInstance(code);
        this.flagImageUrl = null;
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

    public String getFlagImageUrl() {
        return flagImageUrl;
    }
}
