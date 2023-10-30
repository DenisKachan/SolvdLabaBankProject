package ClientsOfTheBank;

public class ClientsIndividuals extends BaseClient {

    private String surname;

    public ClientsIndividuals(String name, String surname, int accountIdNumber, double totalAccountBalance,
                              double amountOfMoneyForOperation, int termsOfCooperationInMonth) {
        super(name, accountIdNumber, totalAccountBalance, amountOfMoneyForOperation, termsOfCooperationInMonth);
    }

    public ClientsIndividuals(String name, int accountIdNumber, double totalAccountBalance,
                              double amountOfMoneyForOperation,
                              int termsOfCooperationInMonth, String surname) {
        super(name, accountIdNumber, totalAccountBalance, amountOfMoneyForOperation, termsOfCooperationInMonth);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
