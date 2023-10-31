package StructureOfTheBank;

public class HRDepartment extends BaseDepartment {

    private int totalAmountOfEmployees;

    public HRDepartment(String address, int numberOfEmployees, int neededAmountOfEmployees) {
        super(address, numberOfEmployees, neededAmountOfEmployees);
    }

    public int getAmountOfEmployees() {
        return totalAmountOfEmployees;
    }

    public void setAmountOfEmployees(int amountOfEmployees) {
        this.totalAmountOfEmployees = amountOfEmployees;
    }
}
