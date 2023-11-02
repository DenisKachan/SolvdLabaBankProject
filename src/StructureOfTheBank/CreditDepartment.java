package StructureOfTheBank;

import ClientsOfTheBank.BaseClient;
import ClientsOfTheBank.ClientsIndividuals;
import ClientsOfTheBank.Companies;

import java.time.Year;
import java.util.Random;

public class CreditDepartment {

    private int abilityOfCreditDepartmentToProvideACredit;

    public int getAbilityOfCreditDepartmentToProvideACredit() {
        Random randomAbilityToProvideALoan = new Random();
        abilityOfCreditDepartmentToProvideACredit = randomAbilityToProvideALoan.nextInt(100);
        return abilityOfCreditDepartmentToProvideACredit;
    }

    public void setAbilityOfCreditDepartmentToProvideACredit(int abilityToProvideALoan) {
        this.abilityOfCreditDepartmentToProvideACredit = abilityToProvideALoan;
    }

    public void toApproveACredit(BaseClient baseClient) {
        if ((baseClient.getClass() == ClientsIndividuals.class)) {
            if (((ClientsIndividuals) baseClient).getAge() <= 25 || baseClient.getCreditDelays() >= 2
                    || baseClient.getAmountOfMonthlyIncome() <= 1000
                    || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
                System.out.println("Credit for a client individual can't be approved!");
            } else {
                System.out.println("Credit for the client individual is approved!");
            }
        } else {
            if (Year.now().getValue() - ((Companies) baseClient).getYearOfFoundation() < 5
                    || baseClient.getCreditDelays() > 0 || baseClient.getAmountOfMonthlyIncome() <= 5000
                    || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
                System.out.println("Credit for a company can't be approved!");
            } else {
                System.out.println("Credit for the company is approved!");
            }
        }
    }
}
