package com.example.revoluttest.currencies;

import com.example.domain.RevolutCurrencies;
import com.example.revoluttest.base.BaseContract;
import com.example.revoluttest.base.BasePresenter;

public interface CurrenciesContract {
    public interface View extends BaseContract.View {
        void showCurrencies(RevolutCurrencies currencies);
    }

    public abstract class Presenter extends BasePresenter {

    }

    public interface Model {

    }
}
