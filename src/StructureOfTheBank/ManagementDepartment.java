package StructureOfTheBank;

public class ManagementDepartment extends BaseDepartment {

    private boolean riskOfBankruptcyOfTheBank;

    public ManagementDepartment(String address, int numberOfEmployees, int neededAmountOfEmployees) {
        super(address, numberOfEmployees, neededAmountOfEmployees);
    }

    public boolean isRiskOfBankruptcy() {
        return riskOfBankruptcyOfTheBank;
    }

    public void setRiskOfBankruptcy(boolean riskOfBankruptcy) {
        this.riskOfBankruptcyOfTheBank = riskOfBankruptcy;
    }
}
