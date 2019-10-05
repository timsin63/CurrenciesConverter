package com.example.revoluttest.currencies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.R;
import com.example.revoluttest.currencies.CurrencyTextChangeListener;
import com.jakewharton.rxrelay2.Relay;

import java.util.List;

import io.reactivex.disposables.Disposable;

class SelectedValueAdapterDelegate extends CurrencyAdapterDelegate {

    private Context context;
    private CurrencyTextChangeListener currencyValueListener = new CurrencyTextChangeListener();
    private Relay<Float> currencyValueObserver = currencyValueListener.getCurrencyValueRelay();
    private Disposable textChangeListenerDisposable;
    private SelectedValueChangeListener selectedValueChangeListener;

    SelectedValueAdapterDelegate(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(@NonNull List<RevolutCurrencyRate> items, int position) {
        return position == 0;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_currency_base, parent, false);

        SelectedValueViewHolder viewHolder = new SelectedValueViewHolder(context, itemView);
        currencyValueListener.applyTextChangeListener(viewHolder.getCurrencyValue());

        textChangeListenerDisposable = currencyValueObserver.subscribe(val -> {
            if (selectedValueChangeListener != null) {
                selectedValueChangeListener.onSelectedValueChanged(val);
            }
        });

        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull List<RevolutCurrencyRate> items, int position,
                                    @NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {

        SelectedValueViewHolder holder = (SelectedValueViewHolder) viewHolder;
        currencyValueListener.removeTextChangeListener(holder.getCurrencyValue());
        super.onBindViewHolder(items, position, viewHolder, payloads);
        currencyValueListener.applyTextChangeListener(holder.getCurrencyValue());
    }


    @Override
    protected void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);

        SelectedValueViewHolder viewHolder = (SelectedValueViewHolder) holder;

        if (context != null && viewHolder.getCurrencyValue() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewHolder.getCurrencyValue().getWindowToken(), 0);
        }
    }

    void setSelectedValueChangeListener(SelectedValueChangeListener listener) {
        this.selectedValueChangeListener = listener;
    }

    interface SelectedValueChangeListener {
        void onSelectedValueChanged(double value);
    }
}
