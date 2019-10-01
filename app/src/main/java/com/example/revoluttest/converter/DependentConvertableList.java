package com.example.revoluttest.converter;

import java.util.List;

public interface DependentConvertableList<T> {
    void chooseItem(T chosenItem);

    void setChosenCount(double value);

    double getChosenCount();

    void updateList(List<T> newList);

    void updateList(T base, List<T> newList);

    List<T> getConvertedValues();
}
