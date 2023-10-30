package EmployeesOfTheBank;

public class Cashier extends BaseEmployee {

    private int termsOfWorkAtTheSamePlaceInMonth;

    public Cashier(String name, String surname, double amountOfSalary, int workExperienceInMonth) {
        super(name, surname, amountOfSalary, workExperienceInMonth);
    }

    public int getTermsOfWorkAtTheSamePlaceInMonth() {
        return termsOfWorkAtTheSamePlaceInMonth;
    }

    public void setTermsOfWorkAtTheSamePlaceInMonth(int termsOfWorkAtTheSamePlaceInMonth) {
        this.termsOfWorkAtTheSamePlaceInMonth = termsOfWorkAtTheSamePlaceInMonth;
    }
}
