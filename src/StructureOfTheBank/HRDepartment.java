package StructureOfTheBank;

public class HRDepartment extends BaseDepartment {

    private int amountOfEmployees;

    public HRDepartment(String address, int numberOfEmployees, int neededAmountOfEmployees) {
        super(address, numberOfEmployees, neededAmountOfEmployees);
    }

    public int getAmountOfEmployees() {
        return amountOfEmployees;
    }

    public void setAmountOfEmployees(int amountOfEmployees) {
        this.amountOfEmployees = amountOfEmployees;
    }
}
