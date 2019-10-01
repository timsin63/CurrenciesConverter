package com.example.revoluttest.currencies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.R;
import com.example.revoluttest.converter.DependentConvertableList;
import com.example.revoluttest.converter.RateList;
import com.example.revoluttest.flags.CurrencyFlagsProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

class CurrencyRatesAdapter extends RecyclerView.Adapter<CurrencyRatesViewHolder> {

    private Context context;

    private boolean skipTextChange = false;

    private DependentConvertableList<RevolutCurrencyRate> currencyRates;
    private List<RevolutCurrencyRate> adapterRates = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private CurrencyTextChangeListener currencyValueListener = new CurrencyTextChangeListener();

    static final String KEY_CODE = "key_currency_code";
    static final String KEY_NAME = "key_currency_name";
    static final String KEY_VALUE = "key_currency_value";

    CurrencyRatesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyRatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int inflateViewId = viewType == CurrencyRatesViewHolder.ITEM_TYPE_BASE ? R.layout.i_currency_base : R.layout.i_currency_rate;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(inflateViewId, parent, false);

        CurrencyRatesViewHolder viewHolder = new CurrencyRatesViewHolder(context, itemView);

        itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            RevolutCurrencyRate chosenRate = adapterRates.get(position);

            currencyRates.chooseItem(chosenRate);
            skipTextChange = true;
            applyChanges();

            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked();
            }
        });

        if (viewType == CurrencyRatesViewHolder.ITEM_TYPE_BASE) {
            currencyValueListener.applyTextChangeListener(viewHolder.getCurrencyValue());
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRatesViewHolder currencyRatesViewHolder, int position) {
        RevolutCurrencyRate currencyRate = adapterRates.get(position);

        currencyRatesViewHolder.setCurrencyCode(currencyRate.getCode());
        currencyRatesViewHolder.setCurrencyName(currencyRate.getName());
        if (getItemViewType(position) == CurrencyRatesViewHolder.ITEM_TYPE_BASE) {
            skipTextChange = true;
            currencyRatesViewHolder.setCurrencyValue(currencyRates.getChosenCount());
        } else {
            currencyRatesViewHolder.setCurrencyValue(currencyRate.getValue());
        }
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
                    currencyRatesViewHolder.setCurrencyCode(code);

                    int flagResource = CurrencyFlagsProvider.getInstance().getCurrencyFlag(code);
                    currencyRatesViewHolder.setCurrencyFlagImage(flagResource);
                }

                String name = bundle.getString(KEY_NAME);
                if (name != null) {
                    currencyRatesViewHolder.setCurrencyName(name);
                }

                if (getItemViewType(position) == CurrencyRatesViewHolder.ITEM_TYPE_RATE) {
                    double value = bundle.getDouble(KEY_VALUE);
                    if (value != 0) {
                        currencyRatesViewHolder.setCurrencyValue(value);
                    }
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? CurrencyRatesViewHolder.ITEM_TYPE_BASE : CurrencyRatesViewHolder.ITEM_TYPE_RATE;
    }


    @Override
    public int getItemCount() {
        return adapterRates.size();
    }

    private Disposable currencyValueObserver = currencyValueListener
            .getCurrencyValueRelay()
            .subscribe(value -> {
                if (skipTextChange) {
                    skipTextChange = false;
                    return;
                }

                currencyRates.setChosenCount(value);
                applyChanges();
            });

    public void updateCurrencyRates(RevolutCurrencyRate base, final List<RevolutCurrencyRate> newCurrencyRates) {
        if (currencyRates == null) {
            currencyRates = new RateList(base);
        }

        currencyRates.updateList(base, newCurrencyRates);

        applyChanges();
    }

    private void applyChanges() {
        List<RevolutCurrencyRate> convertedList = new ArrayList<>(currencyRates.getConvertedValues());

        final CurrencyRatesDiffCallback diffCallback = new CurrencyRatesDiffCallback(adapterRates, convertedList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);

        adapterRates = new ArrayList<>(convertedList);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClicked();
    }
}
