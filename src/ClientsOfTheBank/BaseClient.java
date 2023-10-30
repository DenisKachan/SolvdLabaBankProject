package ClientsOfTheBank;

public class BaseClient {

    private String name;
    private int accountIdNumber;
    private double totalAccountBalance;
    private double amountOfMoneyForOperation;
    private int termsOfCooperationInMonth;

    public BaseClient(String name, int accountIdNumber, double totalAccountBalance,
                      double amountOfMoneyForOperation, int termsOfCooperationInMonth) {
        this.name = name;
        this.accountIdNumber = accountIdNumber;
        this.totalAccountBalance = totalAccountBalance;
        this.amountOfMoneyForOperation = amountOfMoneyForOperation;
        this.termsOfCooperationInMonth = termsOfCooperationInMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountIdNumber() {
        return accountIdNumber;
    }

    public void setAccountIdNumber(int accountIdNumber) {
        this.accountIdNumber = accountIdNumber;
    }

    public double getTotalAccountBalance() {
        return totalAccountBalance;
    }

    public void setTotalAccountBalance(double totalAccountBalance) {
        this.totalAccountBalance = totalAccountBalance;
    }

    public double getAmountOfMoneyForOperation() {
        return amountOfMoneyForOperation;
    }

    public void setAmountOfMoneyForOperation(double amountOfMoneyForOperation) {
        this.amountOfMoneyForOperation = amountOfMoneyForOperation;
    }

    public int getTermsOfCooperationInMonth() {
        return termsOfCooperationInMonth;
    }

    public void setTermsOfCooperationInMonth(int termsOfCooperationInMonth) {
        this.termsOfCooperationInMonth = termsOfCooperationInMonth;
    }
}
