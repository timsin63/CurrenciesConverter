package com.example.remote;

import java.util.HashMap;
import java.util.Map;

class CurrenciesRsp {
    private String base;
    private String date;
    private HashMap<String, Double> rates;

    String getBase() {
        return base;
    }

    String getDate() {
        return date;
    }

    Map<String, Double> getRates() {
        return rates;
    }
}
