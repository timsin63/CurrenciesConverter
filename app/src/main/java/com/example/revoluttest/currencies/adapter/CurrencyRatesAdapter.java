package com.example.revoluttest.currencies.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.converter.RateList;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesAdapter extends RecyclerView.Adapter {

    private List<RevolutCurrencyRate> adapterRates;
    private RateList currencyRates;
    private OnItemClickListener onItemClickListener;
    private AdapterDelegatesManager<List<RevolutCurrencyRate>> delegatesManager;

    public CurrencyRatesAdapter(Context context, List<RevolutCurrencyRate> items) {
        this.adapterRates = items;

        delegatesManager = new AdapterDelegatesManager<>();

        SelectedValueAdapterDelegate selectedValueAdapterDelegate = new SelectedValueAdapterDelegate(context);
        selectedValueAdapterDelegate.setSelectedValueChangeListener(this::setSelectedValue);

        RateValueAdapterDelegate rateValueAdapterDelegate = new RateValueAdapterDelegate(context);
        rateValueAdapterDelegate.setOnItemClickListener(position -> {
            synchronized (this) {
                setSelectedItem(position);

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked();
                }
            }
        });

        delegatesManager
                .addDelegate(selectedValueAdapterDelegate)
                .addDelegate(rateValueAdapterDelegate);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(adapterRates, position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(adapterRates, position, holder);
    }

    @Override
    public int getItemCount() {
        return adapterRates.size();
    }

    public void updateCurrencyRates(RevolutCurrencyRate base, List<RevolutCurrencyRate> rates) {
        if (currencyRates == null) {
            currencyRates = new RateList(base);
        }

        currencyRates.updateList(base, rates);

        applyChanges();
    }

    private void applyChanges() {
        List<RevolutCurrencyRate> convertedList = new ArrayList<>(currencyRates.getConvertedValues());

        final CurrencyRatesDiffCallback diffCallback = new CurrencyRatesDiffCallback(adapterRates, convertedList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);

        adapterRates.clear();
        adapterRates.addAll(convertedList);
    }

    private void setSelectedValue(double value) {
        currencyRates.setBaseCount(value);
        adapterRates.get(0).setValue(value);
        applyChanges();
    }

    private void setSelectedItem(int position) {
        RevolutCurrencyRate chosenRate = adapterRates.get(position);
        currencyRates.chooseItem(chosenRate);
        applyChanges();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked();
    }
}
