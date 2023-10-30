package EmployeesOfTheBank;

public class HRManager extends BaseEmployee {

    private int numberOfHiredSpecialists;

    public HRManager(String name, String surname, double amountOfSalary, int workExperienceInMonth) {
        super(name, surname, amountOfSalary, workExperienceInMonth);
    }

    public int getNumberOfHiredSpecialists() {
        return numberOfHiredSpecialists;
    }

    public void setNumberOfHiredSpecialists(int numberOfHiredSpecialists) {
        this.numberOfHiredSpecialists = numberOfHiredSpecialists;
    }
}
