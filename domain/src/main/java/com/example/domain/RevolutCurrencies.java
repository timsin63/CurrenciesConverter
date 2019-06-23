package com.example.domain;

import java.util.Date;
import java.util.List;

public class RevolutCurrencies {
    private String base;
    private Date date;
    private List<RevolutCurrencyRate> rates;

    public RevolutCurrencies(String base, Date date, List<RevolutCurrencyRate> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public Date getDate() {
        return date;
    }

    public List<RevolutCurrencyRate> getRates() {
        return rates;
    }
}
