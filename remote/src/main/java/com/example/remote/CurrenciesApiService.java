package com.example.remote;

import com.example.domain.RevolutCurrencies;

import io.reactivex.Single;

public class CurrenciesApiService {

    private static final String DEFAULT_BASE = "EUR";

    private CurrenciesApi api;

    public CurrenciesApiService(CurrenciesApi api) {
        this.api = api;
    }

    public Single<RevolutCurrencies> getCurrencies() {
        return getCurrencies(DEFAULT_BASE);
    }

    public Single<RevolutCurrencies> getCurrencies(String base) {
        return api.getCurrencies(base).map(CurrenciesMapper::mapRsp);
    }
}
