package com.example.revoluttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.revoluttest.currencies.CurrenciesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        openCurrenciesFragment();
    }


    private void openCurrenciesFragment() {
        Fragment currenciesFragment = getSupportFragmentManager().findFragmentByTag(CurrenciesFragment.TAG);

        if (currenciesFragment == null) {
            currenciesFragment = new CurrenciesFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.f_currencies_container, currenciesFragment, CurrenciesFragment.TAG)
                .addToBackStack(null)
                .commit();
    }
}
