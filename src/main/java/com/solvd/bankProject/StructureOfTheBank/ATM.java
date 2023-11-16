package com.solvd.bankProject.StructureOfTheBank;

import com.solvd.bankProject.BankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.Exceptions.TotalAccountBalanceException;
import com.solvd.bankProject.Interfaces.Countable;
import com.solvd.bankProject.Interfaces.Resettable;
import com.solvd.bankProject.Interfaces.Showing;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Log4j2
public class ATM implements Showing, Resettable, Countable {

    private String address;
    private double currentBalance;
    @Getter
    private static int amountOfCreatedATMs;
    private double financialFlowsThroughTheATM;

    public static Set<ATM> atms = new HashSet<>();


    public ATM() {
        this.address = "Unknown";
        this.currentBalance = 0;
        amountOfCreatedATMs++;
        atms.add(this);
        log.info("ATM with default values was created");
        log.info("Total amount of ATMs was increased by one");
        log.info("The set of ATMs got a new entity");
    }

    public Set<ATM> getAtms() {
        return atms;
    }

    public void setAtms(Set<ATM> atms) {
        ATM.atms = atms;
    }

    public void setAddress(String address) {
        this.address = address;
        log.info("Address got a value of {}", address);
    }

    public static void setAmountOfCreatedATMs(int amountOfCreatedATMs) {
        ATM.amountOfCreatedATMs = amountOfCreatedATMs;
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
            log.info("Current balance of the ATM got a value of {}", currentBalance);
        }
    }

    @Override
    public void showBalance() {
        log.info("Total balance of the ATM is {}", this.currentBalance);
    }

    @Override
    public void showFullInformation() {
        log.info("Full information about the ATM is the following {}", this);
    }

    @Override
    public void resetToDefaultValues() {
        this.address = "Unknown";
        this.currentBalance = 0;
        log.info("All data of the ATM was reset to default values");
    }

    public boolean checkTheAbilityToWithdrawMoney(double moneyForOperation) {
        return currentBalance >= moneyForOperation;
    }

    public final void callTheCollectionService(double moneyForOperation) {
        CollectionService collectionService = new CollectionService(moneyForOperation);
        currentBalance += collectionService.convertMoney(moneyForOperation);
        log.info("Call the collection service for the amount of money of {}", moneyForOperation);
    }

    @Override
    public void amountOfCreatedEntities() {
        log.info("The total amount of ATMs is {}", amountOfCreatedATMs);
    }

    @Override
    public void amountOfFinancialFlows() {
        log.info("Total amount of financial flows for this ATM is {}", financialFlowsThroughTheATM);
    }

    public void showAllATMsOfTheBank() {
        log.info("The set of ATMs is the following - {}", atms);
    }
}
