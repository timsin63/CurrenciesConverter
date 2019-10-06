package com.example.revoluttest.currencies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.RevolutCurrencyRate;
import com.example.revoluttest.R;

import java.util.List;

public class RateValueAdapterDelegate extends CurrencyAdapterDelegate {

    private Context context;
    private OnItemClickListener onItemClickListener;

    RateValueAdapterDelegate(Context context) {
        this.context = context;
    }

    @Override
    protected boolean isForViewType(@NonNull List<RevolutCurrencyRate> items, int position) {
        return position > 0;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_currency_rate, parent, false);

        return new RateViewHolder(context, itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<RevolutCurrencyRate> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        super.onBindViewHolder(items, position, holder, payloads);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    protected void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        holder.itemView.setOnClickListener(null);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
