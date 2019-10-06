package com.example.revoluttest.currencies.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revoluttest.R;
import com.example.revoluttest.flags.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.Locale;

class RateViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView currencyFlag;
    private TextView currencyCode;
    private TextView currencyName;
    private TextView currencyValue;

    RateViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);

        this.context = context;

        currencyFlag = itemView.findViewById(R.id.country_flag);
        currencyCode = itemView.findViewById(R.id.currency_code);
        currencyName = itemView.findViewById(R.id.currency_name);
        currencyValue = itemView.findViewById(R.id.currency_value);
    }

    TextView getCurrencyCode() {
        return currencyCode;
    }

    TextView getCurrencyName() {
        return currencyName;
    }

    TextView getCurrencyValue() {
        return currencyValue;
    }

    void setCurrencyCode(CharSequence code) {
        currencyCode.setText(code);
    }

    void setCurrencyName(CharSequence name) {
        currencyName.setText(name);
    }

    void setCurrencyValue(double value) {
        currencyValue.setText(String.format(Locale.UK,"%.2f", value));
    }

    void setCurrencyFlagImage(int resId) {
        Picasso.with(context)
                .load(resId)
                .transform(new CircleTransformation())
                .into(currencyFlag);
    }
}
