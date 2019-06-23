package com.example.revoluttest.currencies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder> {


    @NonNull
    @Override
    public CurrenciesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrenciesViewHolder currenciesViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    class CurrenciesViewHolder extends RecyclerView.ViewHolder {
        public CurrenciesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
