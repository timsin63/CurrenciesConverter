package com.example.revoluttest.currencies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.revoluttest.R;
import com.example.revoluttest.flags.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.Locale;

class CurrencyRatesViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView currencyFlag;
    private TextView currencyCode;
    private TextView currencyName;
    private TextView currencyValue;

    static final int ITEM_TYPE_BASE = 0;
    static final int ITEM_TYPE_RATE = 1;

    CurrencyRatesViewHolder(Context context, @NonNull View itemView) {
        super(itemView);

        this.context = context;

        currencyFlag = itemView.findViewById(R.id.country_flag);
        currencyCode = itemView.findViewById(R.id.currency_code);
        currencyName = itemView.findViewById(R.id.currency_name);
        currencyValue = itemView.findViewById(R.id.currency_value);
//        currencyValue.setFilters(new InputFilter[] {textInputFilter});
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

    private InputFilter textInputFilter = (source, start, end, dest, dstart, dend) -> {
        try {
            double input = Double.parseDouble(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));

            if (input > 0) {
                source = String.valueOf(0);
                return "";
            }

        } catch (NumberFormatException ignored) {
            source = String.valueOf(0);
        }
        return "";
    };
}
