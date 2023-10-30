package EmployeesOfTheBank;

public class CreditManager extends BaseEmployee {

    private int numberOfSuccessfulDeals;

    public CreditManager(String name, String surname, double amountOfSalary, int workExperienceInMonth) {
        super(name, surname, amountOfSalary, workExperienceInMonth);
    }

    public int getNumberOfSuccessfulDeals() {
        return numberOfSuccessfulDeals;
    }

    public void setNumberOfSuccessfulDeals(int numberOfSuccessfulDeals) {
        this.numberOfSuccessfulDeals = numberOfSuccessfulDeals;
    }
}
