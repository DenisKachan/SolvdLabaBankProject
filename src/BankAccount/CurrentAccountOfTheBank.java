package BankAccount;


import interfaces.IStartBalance;

public final class CurrentAccountOfTheBank implements IStartBalance {

    private static double currentCashBalance;
    private static double currentNonCashBalance;

    private CurrentAccountOfTheBank(double cash, double nonCash) {
        currentCashBalance = cash;
        currentNonCashBalance = nonCash;
    }

    private static CurrentAccountOfTheBank instance;

    public static CurrentAccountOfTheBank getInstance() {
        if (instance == null) {
            currentCashBalance = START_CASH_BALANCE;
            currentNonCashBalance = START_NON_CASH_BALANCE;
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
    }

    public void decreaseCurrentCashBalance(double moneyForOperation) {
        currentCashBalance -= moneyForOperation;
    }

    public double getCurrentNonCashBalance() {
        return currentNonCashBalance;
    }

    public void increaseCurrentNonCashBalance(double moneyForOperation) {
        currentNonCashBalance += moneyForOperation;
    }

    public void decreaseCurrentNonCashBalance(double moneyForOperation) {
        currentNonCashBalance -= moneyForOperation;
    }

    public double getCurrentBankBalance() {
        return currentCashBalance + currentNonCashBalance;
    }
}
