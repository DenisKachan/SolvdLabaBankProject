package EmployeesOfTheBank;

public class BaseEmployee {

    private String name;
    private String surname;
    private double amountOfSalary;
    private int workExperienceInMonth;

    public BaseEmployee(String name, String surname, double amountOfSalary, int workExperienceInMonth) {
        this.name = name;
        this.surname = surname;
        this.amountOfSalary = amountOfSalary;
        this.workExperienceInMonth = workExperienceInMonth;
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

    public double getAmountOfSalary() {
        return amountOfSalary;
    }

    public void setAmountOfSalary(double amountOfSalary) {
        this.amountOfSalary = amountOfSalary;
    }

    public int getWorkExperienceInMonth() {
        return workExperienceInMonth;
    }

    public void setWorkExperienceInMonth(int workExperienceInMonth) {
        this.workExperienceInMonth = workExperienceInMonth;
    }
}
