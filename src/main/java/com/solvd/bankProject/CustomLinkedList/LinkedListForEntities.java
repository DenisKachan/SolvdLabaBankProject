package com.solvd.bankProject.CustomLinkedList;


import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public class LinkedListForEntities<T> {

    Nodes<T> instance;
    private int length = 0;

    public LinkedListForEntities() {
        this.instance = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListForEntities<?> that)) return false;
        return length == that.length && Objects.equals(instance, that.instance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instance, length);
    }

    public T getByIndex(int index) {
        T data = null;
        Nodes<T> temp = instance;
        int nodeFinder = 0;
        if (instance == null)
            log.info("This list is empty");
        if (index > length)
            log.info("There is no such position in the list");
        if (index == 0)
            data = instance.getValue();
        else {
            while (temp != null) {
                if (nodeFinder == index) {
                    data = temp.getValue();
                    break;
                }
                temp = temp.next;
                nodeFinder++;
            }
        }
        return data;
    }

    public void addToTheEndOfTheList(T value) {
        Nodes<T> temp = new Nodes<>(value);
        if (this.instance == null) {
            instance = temp;
        } else {
            Nodes<T> node = instance;
            while (node.next != null) {
                node = node.next;
            }
            node.next = temp;
        }
        length++;
    }

    public void addToTheEstablishedPosition(int index, T data) {
        if (index > length) {
            log.info("There is no such position in the list");
            return;
        }
        if (index == 0) {
            Nodes<T> temp = instance;
            instance = new Nodes<>(data);
            instance.next = temp;
            return;
        }
        Nodes<T> temp = instance;
        Nodes<T> prev = new Nodes<>(null);
        while (index > 0) {
            prev = temp;
            temp = temp.next;
            index--;
        }
        prev.next = new Nodes<>(data);
        prev.next.next = temp;
    }

    public void setValueToTheEstablishedPositionAndRemovePrevious(int index, T data) {
        if (index > length) {
            log.info("There is no such position in the list");
            return;
        }
        if (index == 0) {
            Nodes<T> temp = instance;
            instance = new Nodes<>(data);
            instance.next = temp.next;
        }
        Nodes<T> temp = instance;
        Nodes<T> prev = new Nodes<>(null);
        while (index > 0) {
            prev = temp;
            temp = temp.next;
            index--;
        }
        prev.next = new Nodes<>(data);
        prev.next.next = temp.next;
    }

    public void removeACertainValue(T object) {
        Nodes<T> prev = new Nodes<>(null);
        prev.next = instance;
        Nodes<T> next = instance.next;
        Nodes<T> temp = instance;
        boolean exists = false;
        if (instance.value == object) {
            instance = instance.next;
            exists = true;
        }
        while (temp.next != null) {
            if (String.valueOf(temp.value).equals(
                    String.valueOf(object))) {
                prev.next = next;
                exists = true;
                break;
            }
            prev = temp;
            temp = temp.next;
            next = temp.next;
        }
        if (!exists
                && String.valueOf(temp.value).equals(
                String.valueOf(object))) {
            prev.next = null;
            exists = true;
        }
        if (exists) {
            length--;
        } else {
            log.info("There is no such value in the list");
        }
    }

    public void removeByIndex(int index) {
        if (index > length) {
            log.info("There is no such position in the list");
            return;
        }
        Nodes<T> prev = new Nodes<>(null);
        prev.next = instance;
        Nodes<T> next = instance.next;
        Nodes<T> temp = instance;
        boolean exists = false;
        if (index == 0) {
            instance = next;
            exists = true;
        }
        if (index > 0) {
            while (index > 0) {
                prev = temp;
                temp = temp.next;
                index--;
            }
            exists = true;
            prev.next = temp.next;
        }
        if (exists) {
            length--;
        } else {
            log.info("There is no such value in the list");
        }
    }

    public void clear() {
        instance = null;
        length = 0;
    }

    public boolean isEmpty() {
        return instance == null;
    }

    public int size() {
        return this.length;
    }

    public String toString() {
        StringBuilder S = new StringBuilder("{ ");
        Nodes<T> nodes = instance;
        if (nodes == null)
            return S + " }";
        while (nodes.next != null) {
            S.append(String.valueOf(nodes.value)).append(" -> ");
            nodes = nodes.next;
        }
        S.append(String.valueOf(nodes.value));
        return S + " }";
    }
}
