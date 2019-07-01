package com.example.revoluttest.currencies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.domain.RevolutCurrencyRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.revoluttest.currencies.CurrencyRatesAdapter.KEY_CODE;
import static com.example.revoluttest.currencies.CurrencyRatesAdapter.KEY_NAME;
import static com.example.revoluttest.currencies.CurrencyRatesAdapter.KEY_VALUE;

class CurrencyRatesDiffCallback extends DiffUtil.Callback {
    private List<RevolutCurrencyRate> oldList;
    private List<RevolutCurrencyRate> newList;

    CurrencyRatesDiffCallback(List<RevolutCurrencyRate> oldList, List<RevolutCurrencyRate> newList) {
        this.oldList = new ArrayList<>(oldList);
        this.newList = new ArrayList<>(newList);
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        RevolutCurrencyRate oldRate = oldList.get(oldItemPosition);
        RevolutCurrencyRate newRate = newList.get(newItemPosition);

        return (oldRate != null && newRate != null) && Objects.equals(oldRate.getCode(), newRate.getCode());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        RevolutCurrencyRate oldItem = oldList.get(oldItemPosition);
        RevolutCurrencyRate newItem = newList.get(newItemPosition);

        return oldItem.equals(newItem);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        RevolutCurrencyRate oldRate = oldList.get(oldItemPosition);
        RevolutCurrencyRate newRate = newList.get(newItemPosition);

        Bundle bundle = new Bundle();

        if (!oldRate.getCode().equals(newRate.getCode())) bundle.putString(KEY_CODE, newRate.getCode());
        if (!oldRate.getName().equals(newRate.getName())) bundle.putString(KEY_NAME, newRate.getName());
        if (oldRate.getValue() != newRate.getValue()) bundle.putDouble(KEY_VALUE, newRate.getValue());

        return bundle;
    }
}