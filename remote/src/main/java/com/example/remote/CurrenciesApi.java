package com.example.remote;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrenciesApi {

    @GET("latest")
    Single<CurrenciesRsp> getCurrencies(@Query("base") String base);
}
