package BankStructure;

public class Accounting {

    private String address;
    private int numberOfEmployees;
    private int neededAmountOfEmployees;

    public Accounting(String address, int numberOfEmployees, int neededAmountOfEmployees) {
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.neededAmountOfEmployees = neededAmountOfEmployees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getNeededAmountOfEmployees() {
        return neededAmountOfEmployees;
    }

    public void setNeededAmountOfEmployees(int neededAmountOfEmployees) {
        this.neededAmountOfEmployees = neededAmountOfEmployees;
    }
}
