package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import Exceptions.LackOfNonCashAfterConvertingException;

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
            if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()==0){
                try {
                    throw new LackOfNonCashAfterConvertingException("The amount of non cash is critically low");
                } catch (LackOfNonCashAfterConvertingException e){
                    System.out.println("Bank balance must be popped up");
                }
            }
            CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(neededAmountOfMoney);
            amountOfTheTransportedCash = neededAmountOfMoney;
            System.out.println("The balance of the ATM will be increased soon. Try again!");
        } else {
            System.out.println("The amount of money is to large to withdraw");
        }
        return 0;
    }
}
