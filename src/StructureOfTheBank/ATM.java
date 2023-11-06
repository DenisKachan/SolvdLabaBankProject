package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import interfaces.Countable;
import interfaces.Resettable;
import interfaces.Showing;

public class ATM implements Showing, Resettable, Countable {

    private String address;
    private double currentBalance;
    private static int amountOfCreatedATMs;
    private static double financialFlowsThroughTheATM;


    public ATM() {
        CurrentAccountOfTheBank.getInstance();
        if (currentBalance <= CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()) {
            this.address = "Unknown";
            this.currentBalance = 0;
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

    public static int getAmountOfCreatedATMs() {
        return amountOfCreatedATMs;
    }

    public static void setAmountOfCreatedATMs(int amountOfCreatedATMs) {
        ATM.amountOfCreatedATMs = amountOfCreatedATMs;
    }

    public static double getFinancialFlowsThroughTheATM() {
        return financialFlowsThroughTheATM;
    }

    public static void setFinancialFlowsThroughTheATM(double financialFlowsThroughTheATM) {
        ATM.financialFlowsThroughTheATM = financialFlowsThroughTheATM;
    }

    public void setCurrentBalance(double currentBalance) {
        CurrentAccountOfTheBank.getInstance();
        if (currentBalance <= CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()) {
            this.currentBalance = currentBalance;
        } else {
            System.out.println("The start balance of the ATM can not be larger than the balance of the bank");
        }
    }

    @Override
    public void showBalance() {
        System.out.println(this.currentBalance);
    }

    @Override
    public void showFullInformation() {
        System.out.println(this);
    }

    @Override
    public void resetToDefaultValues() {
        this.address = "Unknown";
        this.currentBalance = 0;
    }

    public boolean checkTheAbilityToWithdrawMoney(double moneyForOperation) {
        return currentBalance >= moneyForOperation;
    }

    public final void callTheCollectionService(double moneyForOperation) {
        CollectionService collectionService = new CollectionService(moneyForOperation);
        setCurrentBalance(getCurrentBalance() + collectionService.convertMoney(moneyForOperation));
    }

    @Override
    public void amountOfCreatedEntities() {
        System.out.println(amountOfCreatedATMs);

    }

    @Override
    public void amountOfFinancialFlows() {
        System.out.println(financialFlowsThroughTheATM);
    }
}
