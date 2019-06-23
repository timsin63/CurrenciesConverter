package com.example.revoluttest.currencies;

import com.example.model.CurrenciesModel;

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

        addDisposable(model.loadLatestCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rates -> getView().showCurrencies(rates)));
    }
}
