package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.BaseClient;
import Exceptions.TotalAccountBalanceException;
import Interfaces.Countable;
import Interfaces.Resettable;
import Interfaces.Showing;
import LoggerInstance.Loggers;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ATM implements Showing, Resettable, Countable {

    private String address;
    private double currentBalance;
    private static int amountOfCreatedATMs;
    private double financialFlowsThroughTheATM;

    public static Set<ATM> atms = new HashSet<>();


    public ATM() {
        this.address = "Unknown";
        this.currentBalance = 0;
        amountOfCreatedATMs++;
        atms.add(this);
        Loggers.LOGGER.info("ATM with default values was created");
        Loggers.LOGGER.info("Total amount of ATMs was increased by one");
        Loggers.LOGGER.info("The set of ATMs got a new entity");
    }

    public Set<ATM> getAtms() {
        return atms;
    }

    public void setAtms(Set<ATM> atms) {
        ATM.atms = atms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        Loggers.LOGGER.info("Address got a value of {}", address);

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

    public double getFinancialFlowsThroughTheATM() {
        return financialFlowsThroughTheATM;
    }

    public void setFinancialFlowsThroughTheATM(double financialFlowsThroughTheATM) {
        this.financialFlowsThroughTheATM = financialFlowsThroughTheATM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ATM atm)) return false;
        return Double.compare(getCurrentBalance(), atm.getCurrentBalance()) == 0
                && Double.compare(getFinancialFlowsThroughTheATM(), atm.getFinancialFlowsThroughTheATM()) == 0
                && Objects.equals(getAddress(), atm.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddress(), getCurrentBalance(), getFinancialFlowsThroughTheATM());
    }

    @Override
    public String toString() {
        return "ATM{" +
                "address='" + address + '\'' +
                ", currentBalance=" + currentBalance +
                ", financialFlowsThroughTheATM=" + financialFlowsThroughTheATM +
                '}';
    }

    public void setCurrentBalance(double currentBalance) throws TotalAccountBalanceException {
        CurrentAccountOfTheBank.getInstance();
        if (currentBalance >= CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()) {
            throw new TotalAccountBalanceException("Something wrong with balance of the ATM");
        } else {
            this.currentBalance = currentBalance;
            Loggers.LOGGER.info("Current balance of the ATM got a value of {}", currentBalance);
        }
    }

    @Override
    public void showBalance() {
        Loggers.LOGGER.info("Total balance of the ATM is {}", this.currentBalance);
    }

    @Override
    public void showFullInformation() {
        Loggers.LOGGER.info("Full information about the ATM is the following {}", this);
    }

    @Override
    public void resetToDefaultValues() {
        this.address = "Unknown";
        this.currentBalance = 0;
        Loggers.LOGGER.info("All data of the ATM was reset to default values");
    }

    public boolean checkTheAbilityToWithdrawMoney(double moneyForOperation) {
        return currentBalance >= moneyForOperation;
    }

    public final void callTheCollectionService(double moneyForOperation) {
        CollectionService collectionService = new CollectionService(moneyForOperation);
        currentBalance += collectionService.convertMoney(moneyForOperation);
        Loggers.LOGGER.info("Call the collection service for the amount of money of {}", moneyForOperation);
    }

    @Override
    public void amountOfCreatedEntities() {
        Loggers.LOGGER.info("The total amount of ATMs is {}", amountOfCreatedATMs);
    }

    @Override
    public void amountOfFinancialFlows() {
        Loggers.LOGGER.info("Total amount of financial flows for this ATM is {}", financialFlowsThroughTheATM);
    }

    public void showAllATMsOfTheBank(){
        Loggers.LOGGER.info("The set of ATMs is the following - {}", atms);
    }
}
