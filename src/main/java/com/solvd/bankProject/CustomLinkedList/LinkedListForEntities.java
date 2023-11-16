package com.solvd.bankProject.CustomLinkedList;


import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public class LinkedListForEntities<T> {

    Nodes<T> head;
    private int length = 0;

    public LinkedListForEntities() {
        this.head = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListForEntities<?> that)) return false;
        return length == that.length && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, length);
    }

    public T getByIndex(int index) {
        T data = null;
        Nodes<T> temp = head;
        int nodeFinder = 0;
        if (head == null)
            log.info("This list is empty");
        if (index > length)
            log.info("There is no such position in the list");
        if (index == 0)
            data = head.getValue();
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
        if (this.head == null) {
            head = temp;
        } else {
            Nodes<T> node = head;
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
            Nodes<T> temp = head;
            head = new Nodes<>(data);
            head.next = temp;
            return;
        }

        Nodes<T> temp = head;
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
            Nodes<T> temp = head;
            head = new Nodes<>(data);
            head.next = temp.next;
        }
        Nodes<T> temp = head;
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
        prev.next = head;
        Nodes<T> next = head.next;
        Nodes<T> temp = head;
        boolean exists = false;
        if (head.value == object) {
            head = head.next;
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
        prev.next = head;
        Nodes<T> next = head.next;
        Nodes<T> temp = head;
        boolean exists = false;
        if (index == 0) {
            head = next;
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
        head = null;
        length = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return this.length;
    }

    public String toString() {
        StringBuilder S = new StringBuilder("{ ");
        Nodes<T> X = head;
        if (X == null)
            return S + " }";
        while (X.next != null) {
            S.append(String.valueOf(X.value)).append(" -> ");
            X = X.next;
        }
        S.append(String.valueOf(X.value));
        return S + " }";
    }
}
