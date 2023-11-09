package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import Exceptions.AddressException;
import Exceptions.LackOfNonCashAfterConvertingException;
import Exceptions.TotalAccountBalanceException;
import Interfaces.Countable;
import Interfaces.Resettable;
import Interfaces.Showing;

public class ATM implements Showing, Resettable, Countable {

    private String address;
    private double currentBalance;
    private static int amountOfCreatedATMs;
    private static double financialFlowsThroughTheATM;


    public ATM() {
            this.address = "Unknown";
            this.currentBalance = 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws AddressException {
        if (address.equals("")){throw new AddressException("The address is wrong");
        } else {this.address = address;}
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

    public void setCurrentBalance(double currentBalance) throws TotalAccountBalanceException {
        CurrentAccountOfTheBank.getInstance();
        if (currentBalance <= CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()) {
            throw new TotalAccountBalanceException("Something wrong with balance of the ATM");
        } else {
            this.currentBalance = currentBalance;
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
        currentBalance += collectionService.convertMoney(moneyForOperation);
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
