package com.example.remote;

import com.example.common.utils.DateUtil;
import com.example.domain.RevolutCurrencies;
import com.example.domain.RevolutCurrencyRate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class CurrenciesMapper {

    private CurrenciesMapper() {}

    static RevolutCurrencies mapRsp(CurrenciesRsp response) throws RemoteException {

        Date date;
        try {
            date = DateUtil.dateFromStr(response.getDate());
        } catch (ParseException e) {
            throw new RemoteException(e);
        }

        if (response.getBase() == null || date == null) {
            throw new RemoteException("An error while parsing response.");
        }

        List<RevolutCurrencyRate> rates = new ArrayList<>();

        for (HashMap.Entry<String, Double> rate : response.getRates().entrySet()) {
            RevolutCurrencyRate currencyRate = new RevolutCurrencyRate(rate.getKey(), response.getBase(), rate.getValue());
            rates.add(currencyRate);
        }

        RevolutCurrencyRate base = new RevolutCurrencyRate(response.getBase(), response.getBase(), 1);

        return new RevolutCurrencies(base, date, rates);
    }
}
