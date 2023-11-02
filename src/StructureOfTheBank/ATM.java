package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;

import java.util.Scanner;

public class ATM {

    private String address;
    private double currentBalance;

    public ATM() {
        Scanner addressOfTheATM = new Scanner(System.in);
        System.out.println("Enter the address of the ATM");
        String atmAddress = addressOfTheATM.nextLine();
        Scanner currentBalanceOfTheATM = new Scanner(System.in);
        System.out.println("Enter the balance of the ATM");
        double atmBalance = currentBalanceOfTheATM.nextDouble();
        if (atmBalance <= CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()) {
            this.address = atmAddress;
            this.currentBalance = atmBalance;
        } else {
            System.out.println("The start balance of the ATM can not be larger than the balance of the bank");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean checkTheAbilityToWithdrawMoney(double moneyForOperation) {
        return currentBalance >= moneyForOperation;
    }

    public void callTheCollectionService(double moneyForOperation) {
        CollectionService collectionService = new CollectionService(moneyForOperation);
        setCurrentBalance(getCurrentBalance() + collectionService.convertNonCashIntoCash(moneyForOperation));
    }
}
