package Clients;

public class ClientsIndividuals {

    private String name;
    private String surname;
    private int accountIdNumber;
    private double totalAccountBalance;
    private double amountOfMoneyForOperation;
    private int termsOfCooperationInMonth;

    public ClientsIndividuals(String name, String surname, int accountIdNumber, double totalAccountBalance,
                              int termsOfCooperationInMonth) {
        this.name = name;
        this.surname = surname;
        this.accountIdNumber = accountIdNumber;
        this.totalAccountBalance = totalAccountBalance;
        this.termsOfCooperationInMonth = termsOfCooperationInMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
