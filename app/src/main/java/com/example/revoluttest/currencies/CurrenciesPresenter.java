package com.example.revoluttest.currencies;

import com.example.model.CurrenciesModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesPresenter extends CurrenciesContract.Presenter {
    private CurrenciesModel model;

    @Override
    public CurrenciesContract.View getView() {
        return (CurrenciesContract.View) super.getView();
    }

    @Override
    public void onSubscribe() {
        if (model == null) {
            model = new CurrenciesModel();
        }

        addDisposable(
                Observable.interval(1, TimeUnit.SECONDS)
                .flatMapSingle(tick -> model.loadLatestCurrencies())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rates -> getView().showCurrencies(rates)));
    }

    @Override
    void onItemSelected() {
        getView().focusSelected();
    }
}
