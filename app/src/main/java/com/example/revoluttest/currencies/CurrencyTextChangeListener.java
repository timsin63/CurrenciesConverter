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

    private TextWatcher textChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            float value = s.length() > 0 ? Float.parseFloat(s.toString()) : 0f;

            currencyValueRelay.accept(value);
        }

        @Override
        public void afterTextChanged(Editable s) {
//            if (s.length() == 0) {
//                s.append('0');
//            } else {
//                if (s.charAt(0) == '0') {
//                    s.delete(0, 0);
//                }
//            }
        }
    };



    public Relay<Float> getCurrencyValueRelay() {
        return currencyValueRelay;
    }
}
