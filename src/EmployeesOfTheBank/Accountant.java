package EmployeesOfTheBank;

public class Accountant extends BaseEmployee {

    private int totalNumberOfFinancialOperations;

    public Accountant(String name, String surname, double amountOfSalary, int workExperienceInMonth) {
        super(name, surname, amountOfSalary, workExperienceInMonth);
    }

    public int getTotalNumberOfFinancialOperations() {
        return totalNumberOfFinancialOperations;
    }

    public void setTotalNumberOfFinancialOperations(int totalNumberOfFinancialOperations) {
        this.totalNumberOfFinancialOperations = totalNumberOfFinancialOperations;
    }
}
