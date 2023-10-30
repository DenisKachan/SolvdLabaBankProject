package StructureOfTheBank;

public class Accounting extends BaseDepartment {

    private double salaryBalance;

    public Accounting(String address, int numberOfEmployees, int neededAmountOfEmployees) {
        super(address, numberOfEmployees, neededAmountOfEmployees);
    }

    public double getSalaryBalance() {
        return salaryBalance;
    }

    public void setSalaryBalance(double salaryBalance) {
        this.salaryBalance = salaryBalance;
    }
}
