package EmployeesOfTheBank;

public class Management extends BaseEmployee {

    private String areaOfResponsibility;

    public Management(String name, String surname, double amountOfSalary, int workExperienceInMonth) {
        super(name, surname, amountOfSalary, workExperienceInMonth);
    }

    public String getAreaOfResponsibility() {
        return areaOfResponsibility;
    }

    public void setAreaOfResponsibility(String areaOfResponsibility) {
        this.areaOfResponsibility = areaOfResponsibility;
    }
}
