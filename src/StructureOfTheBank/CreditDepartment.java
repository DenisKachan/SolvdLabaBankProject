package StructureOfTheBank;

public class CreditDepartment extends BaseDepartment {

    private boolean abilityToProvideALoan;

    public CreditDepartment(String address, int numberOfEmployees, int neededAmountOfEmployees) {
        super(address, numberOfEmployees, neededAmountOfEmployees);
    }

    public boolean isAbilityToProvideALoan() {
        return abilityToProvideALoan;
    }

    public void setAbilityToProvideALoan(boolean abilityToProvideALoan) {
        this.abilityToProvideALoan = abilityToProvideALoan;
    }
}
