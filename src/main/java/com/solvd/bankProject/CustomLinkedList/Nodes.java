package com.solvd.bankProject.CustomLinkedList;

import lombok.Getter;

public class Nodes<T> {

    @Getter
    T value;
    Nodes<T> next;

    public Nodes(T value) {
        this.value = value;
        this.next = null;
    }
}
