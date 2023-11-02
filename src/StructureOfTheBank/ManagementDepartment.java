package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;

public class ManagementDepartment {

    private boolean riskOfBankruptcyOfTheBank;

    public boolean isRiskOfBankruptcy() {
        return riskOfBankruptcyOfTheBank;
    }

    public void setRiskOfBankruptcy(boolean riskOfBankruptcy) {
        this.riskOfBankruptcyOfTheBank = riskOfBankruptcy;
    }

    public boolean checkTheRiskOfBankruptcyOfTheBank() {
        if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()
                <= (CurrentAccountOfTheBank.getStartCashBalance() + CurrentAccountOfTheBank.getStartNonCashBalance()) / 2) {
            System.out.println("There is a risk of bankruptcy - the current bank funds are less than they should be!");
            return true;
        } else {
            System.out.println("There is no risk of bankruptcy!");
            return false;
        }
    }
}
