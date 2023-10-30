package ClientsOfTheBank;

public class Companies extends BaseClient {

    private String address;

    public Companies(String name, int accountIdNumber, double totalAccountBalance,
                     double amountOfMoneyForOperation, int termsOfCooperationInMonth) {
        super(name, accountIdNumber, totalAccountBalance, amountOfMoneyForOperation, termsOfCooperationInMonth);
    }

    public Companies(String name, int accountIdNumber, double totalAccountBalance, double amountOfMoneyForOperation,
                     int termsOfCooperationInMonth, String address) {
        super(name, accountIdNumber, totalAccountBalance, amountOfMoneyForOperation, termsOfCooperationInMonth);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
