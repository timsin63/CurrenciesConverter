package com.example.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {

    private static ApiServiceProvider instance;
    private static final String BASE_URL = "https://revolut.duckdns.org";
    private CurrenciesApiService currenciesApiService;

    private ApiServiceProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        CurrenciesApi currenciesApi = retrofit.create(CurrenciesApi.class);
        currenciesApiService = new CurrenciesApiService(currenciesApi);
    }

    public static synchronized ApiServiceProvider getInstance() {
        if (instance == null) {
            instance = new ApiServiceProvider();
        }

        return instance;
    }

    public CurrenciesApiService getCurrenciesApiService() {
        return currenciesApiService;
    }
}
