package com.example.revoluttest.currencies.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.flags.CurrencyFlagsProvider;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;

abstract class CurrencyAdapterDelegate extends AdapterDelegate<List<RevolutCurrencyRate>> {

    static final String KEY_CODE = "key_currency_code";
    static final String KEY_NAME = "key_currency_name";
    static final String KEY_VALUE = "key_currency_value";

    private void onBindViewHolder(@NonNull List<RevolutCurrencyRate> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        RevolutCurrencyRate currencyRate = items.get(position);

        RateViewHolderBase currencyRatesViewHolder = (RateViewHolderBase) holder;

        currencyRatesViewHolder.setCurrencyCode(currencyRate.getCode());
        currencyRatesViewHolder.setCurrencyName(currencyRate.getName());
        currencyRatesViewHolder.setCurrencyValue(currencyRate.getValue());

        int flagResource = CurrencyFlagsProvider.getInstance().getCurrencyFlag(currencyRate.getCode());
        currencyRatesViewHolder.setCurrencyFlagImage(flagResource);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<RevolutCurrencyRate> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(items, position, holder);
        } else {
            RateViewHolderBase currencyRatesViewHolder = (RateViewHolderBase) holder;

            Bundle bundle = (Bundle) payloads.get(0);

            if (!bundle.isEmpty()) {
                String code = bundle.getString(KEY_CODE);
                if (code != null) {
                    currencyRatesViewHolder.setCurrencyCode(code);

                    int flagResource = CurrencyFlagsProvider.getInstance().getCurrencyFlag(code);
                    currencyRatesViewHolder.setCurrencyFlagImage(flagResource);
                }

                String name = bundle.getString(KEY_NAME);
                if (name != null) {
                    currencyRatesViewHolder.setCurrencyName(name);
                }

                double value = bundle.getDouble(KEY_VALUE, -1);
                if (value != -1) {
                    currencyRatesViewHolder.setCurrencyValue(value);
                }
            }
        }
    }
}
