package com.solvd.bankProject.clientsPropertyAndHistory;

import lombok.Getter;

import java.util.Objects;
import java.util.Random;

@Getter
public class CreditCard {

    private int idNumber;

    private double creditCardBalance;

    private int creditCardNumber;

    public CreditCard(int idNumber, double creditCardBalance) {
        Random randomCreditCardNumber = new Random();
        creditCardNumber = randomCreditCardNumber.nextInt(10000);
        this.idNumber = idNumber;
        this.creditCardBalance = creditCardBalance;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setCreditCardBalance(double creditCardBalance) {
        this.creditCardBalance = creditCardBalance;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard that)) return false;
        return getIdNumber() == that.getIdNumber() && Double.compare(getCreditCardBalance(),
                that.getCreditCardBalance()) == 0 && getCreditCardNumber() == that.getCreditCardNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdNumber(), getCreditCardBalance(), getCreditCardNumber());
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "ID Number=" + idNumber +
                ", Credit card balance=" + creditCardBalance +
                ", Credit card number=" + creditCardNumber +
                '}';
    }
}