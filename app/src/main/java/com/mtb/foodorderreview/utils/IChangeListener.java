package com.mtb.foodorderreview.utils;

public interface IChangeListener<E> {
    int getId();

    void dataChange(E obj);
}
