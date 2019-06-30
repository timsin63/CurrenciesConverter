package com.example.revoluttest.currencies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.RevolutCurrencies;
import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.R;
import com.example.revoluttest.flags.CircleTransformation;
import com.example.revoluttest.flags.CurrencyFlagsProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class CurrencyRatesAdapter extends RecyclerView.Adapter<CurrencyRatesAdapter.CurrencyRatesViewHolder> {

    private List<RevolutCurrencyRate> currencyRates = new ArrayList<>();
    private Context context;

    CurrencyRatesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyRatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_currency, parent, false);
        return new CurrencyRatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRatesViewHolder currencyRatesViewHolder, int position) {
        RevolutCurrencyRate currencyRate = currencyRates.get(position);

        currencyRatesViewHolder.currencyCode.setText(currencyRate.getCode());
        currencyRatesViewHolder.currencyName.setText(currencyRate.getName());
        currencyRatesViewHolder.currencyValue.setText(String.valueOf(currencyRate.getValue()));

        int flagResource = CurrencyFlagsProvider.getInstance().getCurrencyFlag(currencyRate.getCode());

        Picasso.with(context)
                .load(flagResource)
                .transform(new CircleTransformation())
                .into(currencyRatesViewHolder.currencyFlag);
    }

    @Override
    public int getItemCount() {
        return currencyRates.size();
    }

    void updateCurrencyRates(RevolutCurrencies currencies) {

        List<RevolutCurrencyRate> rates = new ArrayList<>();
        rates.add(currencies.getBase());
        rates.addAll(currencies.getRates());

        final CurrencyRatesDiffCallback diffCallback = new CurrencyRatesDiffCallback(currencyRates, rates);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.currencyRates.clear();
        this.currencyRates.addAll(rates);

        diffResult.dispatchUpdatesTo(this);
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

    class CurrencyRatesDiffCallback extends DiffUtil.Callback {
        List<RevolutCurrencyRate> oldList;
        List<RevolutCurrencyRate> newList;

        CurrencyRatesDiffCallback(List<RevolutCurrencyRate> oldList, List<RevolutCurrencyRate> newList) {
            this.oldList = oldList;
            this.newList = newList;
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
            return Objects.equals(oldList.get(oldItemPosition), newList.get(newItemPosition));
        }
    }
}
