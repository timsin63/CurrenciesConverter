package com.example.revoluttest.currencies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.R;

import java.util.List;

class CurrencyRatesAdapter extends RecyclerView.Adapter<CurrencyRatesAdapter.CurrencyRatesViewHolder> {

    private List<RevolutCurrencyRate> currencyRates;
    private static final String TAG = "CurrencyRatesAdapter";

    @NonNull
    @Override
    public CurrencyRatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_currency, parent, false);
        return new CurrencyRatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRatesViewHolder currencyRatesViewHolder, int position) {
        currencyRatesViewHolder.currencyCode.setText(currencyRates.get(position).getCode());
        currencyRatesViewHolder.currencyName.setText(currencyRates.get(position).getName());
        currencyRatesViewHolder.currencyValue.setText(String.valueOf(currencyRates.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return currencyRates != null ? currencyRates.size() : 0;
    }

    void setCurrencyRates(List<RevolutCurrencyRate> rates) {
        this.currencyRates = rates;
        notifyDataSetChanged();
    }

    class CurrencyRatesViewHolder extends RecyclerView.ViewHolder {

        private ImageView currencyFlag;
        private TextView currencyCode;
        private TextView currencyName;
        private TextView currencyValue;

        CurrencyRatesViewHolder(@NonNull View itemView) {
            super(itemView);

            currencyFlag = itemView.findViewById(R.id.country_flag);
            currencyCode = itemView.findViewById(R.id.currency_code);
            currencyName = itemView.findViewById(R.id.currency_name);
            currencyValue = itemView.findViewById(R.id.currency_value);
        }
    }
}
