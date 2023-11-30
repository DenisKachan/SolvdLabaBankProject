package com.solvd.bankProject.bankAccount;


import com.solvd.bankProject.enums.StartBalance;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class CurrentAccountOfTheBank {

    private static double currentCashBalance;
    private static double currentNonCashBalance;

    private CurrentAccountOfTheBank(double cash, double nonCash) {
        currentCashBalance = cash;
        currentNonCashBalance = nonCash;
    }

    static {
        currentCashBalance = StartBalance.START_CASH_BALANCE.getCurrentValue();
        currentNonCashBalance = StartBalance.START_NON_CASH_BALANCE.getCurrentValue();
    }

    private static CurrentAccountOfTheBank instance;

    public static CurrentAccountOfTheBank getInstance() {
        if (instance == null) {
            instance = new CurrentAccountOfTheBank(currentCashBalance, currentNonCashBalance);
        }
        return instance;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Cash Balance='" + getCurrentCashBalance() + '\'' +
                ", Non Cash Balance=" + instance.getCurrentNonCashBalance() +
                ", Current Account Balance=" + getCurrentBankBalance() +
                '}';
    }

    public double getCurrentCashBalance() {
        return currentCashBalance;
    }

    public void increaseCurrentCashBalance(double moneyForOperation) {
        currentCashBalance += moneyForOperation;
        log.info("Cash balance is increased in {}", moneyForOperation);
    }

    public void decreaseCurrentCashBalance(double moneyForOperation) {
        currentCashBalance -= moneyForOperation;
        log.info("Cash balance is decreased in {}", moneyForOperation);
    }

    public double getCurrentNonCashBalance() {
        return currentNonCashBalance;
    }

    public void increaseCurrentNonCashBalance(double moneyForOperation) {
        currentNonCashBalance += moneyForOperation;
        log.info("Non cash balance is increased in {}", moneyForOperation);
    }

    public void decreaseCurrentNonCashBalance(double moneyForOperation) {
        currentNonCashBalance -= moneyForOperation;
        log.info("Non cash balance is decreased in {}", moneyForOperation);
    }

    public double getCurrentBankBalance() {
        return currentCashBalance + currentNonCashBalance;
    }
}
