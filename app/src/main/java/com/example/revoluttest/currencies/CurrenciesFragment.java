package com.example.revoluttest.currencies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.RevolutCurrencies;
import com.example.revoluttest.R;
import com.example.revoluttest.base.BaseFragment;
import com.example.revoluttest.currencies.adapter.CurrencyRatesAdapter;

import java.util.ArrayList;

public class CurrenciesFragment extends BaseFragment implements CurrenciesContract.View {

    private CurrencyRatesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);

        View view = inflater.inflate(R.layout.f_currencies, parent, false);

        RecyclerView currencyRateList = view.findViewById(R.id.list_currencies);
        adapter = new CurrencyRatesAdapter(getContext(), new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        currencyRateList.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(() -> currencyRateList.scrollToPosition(0));

        currencyRateList.setAdapter(adapter);

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
        if (adapter != null) {
            adapter.updateCurrencyRates(currencies.getBase(), currencies.getRates());
        }
    }
}
