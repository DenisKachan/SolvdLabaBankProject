package StructureOfTheBank;

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

    public final void toApproveCredit(ClientsIndividuals clientsIndividuals) {
        if (clientsIndividuals.getAge() <= 25 || clientsIndividuals.getCreditDelays() >= 2
                || clientsIndividuals.getAmountOfMonthlyIncome() <= 1000
                || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
            System.out.println("Credit for a client individual can't be approved!");
        } else {
            System.out.println("Credit for the client individual is approved!");
        }
    }

    public final void toApproveCredit(Companies companies) {
        if (Year.now().getValue() - companies.getYearOfFoundation() < 5
                || companies.getCreditDelays() > 0 || companies.getAmountOfMonthlyIncome() <= 5000
                || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
            System.out.println("Credit for a company can't be approved!");
        } else {
            System.out.println("Credit for the company is approved!");
        }
    }
}
