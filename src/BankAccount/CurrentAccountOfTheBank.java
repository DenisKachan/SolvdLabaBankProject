package BankAccount;


public class CurrentAccountOfTheBank {

    private static double currentCashBalance;
    private static double currentNonCashBalance;
    private static double startCashBalance = 50000;
    private static double startNonCashBalance = 50100;

    private CurrentAccountOfTheBank(double cash, double nonCash) {
        currentCashBalance = cash;
        currentNonCashBalance = nonCash;
    }

    private static CurrentAccountOfTheBank instance;

    public static CurrentAccountOfTheBank getInstance() {
        if (instance == null) {
            currentCashBalance = startCashBalance;
            currentNonCashBalance = startNonCashBalance;
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
        return "Cash Balance='" + instance.getCurrentCashBalance() + '\'' +
                ", Non Cash Balance=" + instance.getCurrentNonCashBalance() +
                ", Current Account Balance=" + getCurrentBankBalance() +
                '}';
    }

    public static double getStartCashBalance() {
        return startCashBalance;
    }

    public static void setStartCashBalance(double startCashBalance) {
        CurrentAccountOfTheBank.startCashBalance = startCashBalance;
    }

    public static double getStartNonCashBalance() {
        return startNonCashBalance;
    }

    public static void setStartNonCashBalance(double startNonCashBalance) {
        CurrentAccountOfTheBank.startNonCashBalance = startNonCashBalance;
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
