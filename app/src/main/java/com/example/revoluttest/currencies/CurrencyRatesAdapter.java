package com.example.revoluttest.currencies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.R;
import com.example.revoluttest.flags.CircleTransformation;
import com.example.revoluttest.flags.CurrencyFlagsProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CurrencyRatesAdapter extends RecyclerView.Adapter<CurrencyRatesAdapter.CurrencyRatesViewHolder> {

    private Context context;
    private String chosenCurrencyCode;
    private List<RevolutCurrencyRate> currencyRates = Collections.synchronizedList(new ArrayList<>());
    private RevolutCurrencyRate base;
    private OnItemClickListener onItemClickListener;

    static final String KEY_CODE = "key_currency_code";
    static final String KEY_NAME = "key_currency_name";
    static final String KEY_VALUE = "key_currency_value";

    CurrencyRatesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyRatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_currency, parent, false);

        CurrencyRatesViewHolder viewHolder = new CurrencyRatesViewHolder(itemView);

        itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            chosenCurrencyCode = currencyRates.get(position).getCode();

            updateCurrencyRates(base, currencyRates);

            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRatesViewHolder currencyRatesViewHolder, int position) {
        RevolutCurrencyRate currencyRate = currencyRates.get(position);

        currencyRatesViewHolder.currencyCode.setText(currencyRate.getCode());
        currencyRatesViewHolder.currencyName.setText(currencyRate.getName());

        currencyRatesViewHolder.currencyValue.setText(String.valueOf(currencyRate.getValue()));
        currencyRatesViewHolder.currencyValue.setEnabled(currencyRate.getCode().equals(chosenCurrencyCode));

        int flagResource = CurrencyFlagsProvider.getInstance().getCurrencyFlag(currencyRate.getCode());

        currencyRatesViewHolder.setCurrencyFlagImage(flagResource);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRatesViewHolder currencyRatesViewHolder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(currencyRatesViewHolder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);

            if (!bundle.isEmpty()) {
                String code = bundle.getString(KEY_CODE);
                if (code != null) {
                    currencyRatesViewHolder.currencyCode.setText(code);

                    int flagResource = CurrencyFlagsProvider.getInstance().getCurrencyFlag(code);
                    currencyRatesViewHolder.setCurrencyFlagImage(flagResource);
                }

                String name = bundle.getString(KEY_NAME);
                if (name != null) {
                    currencyRatesViewHolder.currencyName.setText(name);
                }

                if (position == 0) {
                    currencyRatesViewHolder.currencyValue.setEnabled(true);
                    currencyRatesViewHolder.currencyValue.requestFocus();
                } else {
                    double value = bundle.getDouble(KEY_VALUE);
                    if (value != 0) {
                        currencyRatesViewHolder.currencyValue.setText(String.valueOf(value));
                    }
                    currencyRatesViewHolder.currencyValue.setEnabled(false);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return currencyRates.size();
    }

    void updateCurrencyRates(RevolutCurrencyRate base, final List<RevolutCurrencyRate> newCurrencyRates) {
        this.base = base;

        List<RevolutCurrencyRate> rates = new ArrayList<>(newCurrencyRates);

        if (!rates.contains(base)) {
            rates.add(0, base);
        }

        if (chosenCurrencyCode == null) {
            chosenCurrencyCode = base.getCode();
        } else {
            for (int i = 0; i < rates.size(); i++) {
                if (rates.get(i).getCode().equals(chosenCurrencyCode)) {
                    RevolutCurrencyRate item = rates.remove(i);
                    rates.add(0, item);
                    break;
                }
            }
        }

        final CurrencyRatesDiffCallback diffCallback = new CurrencyRatesDiffCallback(this.currencyRates, rates);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.currencyRates.clear();
        this.currencyRates = Collections.synchronizedList(rates);
        diffResult.dispatchUpdatesTo(this);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

        void setCurrencyFlagImage(int resId) {
            Picasso.with(context)
                    .load(resId)
                    .transform(new CircleTransformation())
                    .into(currencyFlag);
        }
    }

    interface OnItemClickListener {
        void onItemClicked();
    }
}
