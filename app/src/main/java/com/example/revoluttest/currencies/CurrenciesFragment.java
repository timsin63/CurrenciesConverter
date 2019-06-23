package com.example.revoluttest.currencies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.domain.RevolutCurrencies;
import com.example.revoluttest.R;
import com.example.revoluttest.base.BaseFragment;

public class CurrenciesFragment extends BaseFragment implements CurrenciesContract.View {

    public static final String TAG = "com.example.revoluttest.currencies.CurrenciesFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);

        View view = inflater.inflate(R.layout.f_currencies, parent, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getPresenter() == null) {
            CurrenciesContract.Presenter presenter = new CurrenciesPresenter();
            setPresenter(presenter);
        }

        getPresenter().subscribe(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (getPresenter() != null) {
            getPresenter().unsubscribe();
        }
    }

    @Override
    public void showCurrencies(RevolutCurrencies currencies) {
        System.out.println("");
    }
}
