package com.solvd.bankProject.interfaces;

@FunctionalInterface
public interface Adding<T> {

    void addInstance(String name, T value);
}
