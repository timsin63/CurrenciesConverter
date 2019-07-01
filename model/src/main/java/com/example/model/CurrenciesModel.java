package com.example.model;

import com.example.domain.RevolutCurrencies;
import com.example.domain.RevolutCurrencyRate;
import com.example.remote.ApiServiceProvider;

import java.util.List;

import io.reactivex.Single;

public class CurrenciesModel {

    public List<RevolutCurrencyRate> getCurrencies() {
        throw new UnsupportedOperationException("Currencies caching logic is not implemented yet");
    }

    public Single<RevolutCurrencies> loadLatestCurrencies() {
        return ApiServiceProvider.getInstance()
                    .getCurrenciesApiService()
                    .getCurrencies();
    }
}
