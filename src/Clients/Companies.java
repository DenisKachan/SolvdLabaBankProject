package Clients;

public class Companies {

    private String name;
    private int tinNumber;
    private double totalAccountBalance;
    private double amountOfMoneyForOperation;
    private int termsOfCooperationInMonth;

    public Companies(String name, int tinNumber, double totalAccountBalance,
                     int termsOfCooperationInMonth) {
        this.name = name;
        this.tinNumber = tinNumber;
        this.totalAccountBalance = totalAccountBalance;
        this.termsOfCooperationInMonth = termsOfCooperationInMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(int tinNumber) {
        this.tinNumber = tinNumber;
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
