package BankAccount;

public class CurrentAccount {

    private double totalBankBalance;

    public CurrentAccount(double currentBankBalance) {
        this.totalBankBalance = currentBankBalance;
    }

    public double getCurrentBankBalance() {
        return totalBankBalance;
    }

    public void setCurrentBankBalance(double currentBankBalance) {
        this.totalBankBalance = currentBankBalance;
    }
}
