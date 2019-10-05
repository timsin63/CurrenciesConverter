package com.example.revoluttest.currencies;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

public class CurrencyTextChangeListener {

    private Relay<Float> currencyValueRelay = PublishRelay.create();

    public void applyTextChangeListener(TextView view) {
        view.addTextChangedListener(textChangeListener);
    }

    public void removeTextChangeListener(TextView view) {
        view.removeTextChangedListener(textChangeListener);
    }

    private TextWatcher textChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // empty method
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            float value = s.length() > 0 ? Float.parseFloat(s.toString()) : 0f;

            currencyValueRelay.accept(value);
        }

        @Override
        public void afterTextChanged(Editable s) {
            // empty method
        }
    };



    public Relay<Float> getCurrencyValueRelay() {
        return currencyValueRelay;
    }
}
