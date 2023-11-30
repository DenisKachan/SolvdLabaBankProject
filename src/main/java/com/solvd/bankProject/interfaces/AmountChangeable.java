package com.solvd.bankProject.interfaces;

@FunctionalInterface
public interface AmountChangeable<T> {

    T changeAmount(T t);
}
