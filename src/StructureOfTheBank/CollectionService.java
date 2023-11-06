package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;

public class CollectionService {

    private double amountOfTheTransportedCash;

    public CollectionService(double amountOfTheTransportedCash) {
        this.amountOfTheTransportedCash = amountOfTheTransportedCash;
    }

    public double getAmountOfTheTransportedCash() {
        return amountOfTheTransportedCash;
    }

    public void setAmountOfTheTransportedCash(double amountOfTheTransportedCash) {
        this.amountOfTheTransportedCash = amountOfTheTransportedCash;
    }

    public double convertMoney(double neededAmountOfMoney) {
        if (neededAmountOfMoney <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()) {
            CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(neededAmountOfMoney);
            CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(neededAmountOfMoney);
            amountOfTheTransportedCash = neededAmountOfMoney;
            System.out.println("The balance of the ATM will be increased soon. Try again!");
        } else {
            System.out.println("The amount of money is to large to withdraw");
        }
        return 0;
    }
}
