package com.example.model;

import com.example.domain.RevolutCurrencyRate;
import com.example.domain.RevolutCurrencies;
import com.example.remote.ApiServiceProvider;

import java.util.List;

import io.reactivex.Single;

public class CurrenciesModel {

    public List<RevolutCurrencyRate> getCurrencies() {
        return null;
    }

    public Single<RevolutCurrencies> loadLatestCurrencies() {
        return ApiServiceProvider.getInstance()
                    .getCurrenciesApiService()
                    .getCurrencies();
    }
}
