package com.example.revoluttest.currencies.adapter;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public class SelectedValueViewHolder extends RateViewHolder {
    SelectedValueViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(context, itemView);

        getCurrencyValue().setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }
}
