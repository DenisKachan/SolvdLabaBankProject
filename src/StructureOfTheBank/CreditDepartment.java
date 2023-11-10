package StructureOfTheBank;

import ClientsOfTheBank.ClientsIndividuals;
import ClientsOfTheBank.Companies;
import LoggerInstance.Loggers;

import java.time.Year;
import java.util.Random;

public class CreditDepartment {

    private int abilityOfCreditDepartmentToProvideACredit;

    public int getAbilityOfCreditDepartmentToProvideACredit() {
        Random randomAbilityToProvideALoan = new Random();
        abilityOfCreditDepartmentToProvideACredit = randomAbilityToProvideALoan.nextInt(100);
        Loggers.LOGGER.info("Random percentage of the of the credit department to provide a credit is established in the amount of {}",
                abilityOfCreditDepartmentToProvideACredit);
        return abilityOfCreditDepartmentToProvideACredit;
    }

    public void setAbilityOfCreditDepartmentToProvideACredit(int abilityToProvideALoan) {
        this.abilityOfCreditDepartmentToProvideACredit = abilityToProvideALoan;
    }

    public final void toApproveCredit(ClientsIndividuals clientsIndividuals) {
        Loggers.LOGGER.info("Credit department is checking the client's data");
        if (clientsIndividuals.getAge() <= 25 || clientsIndividuals.getCreditDelays() >= 2
                || clientsIndividuals.getAmountOfMonthlyIncome() <= 1000
                || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
            Loggers.LOGGER.info("Credit for a client individual can't be approved!");
        } else {
            Loggers.LOGGER.info("Credit for the client individual is approved!");
        }
    }

    public final void toApproveCredit(Companies companies) {
        Loggers.LOGGER.info("Credit department is checking the client's data");
        if (Year.now().getValue() - companies.getYearOfFoundation() < 5
                || companies.getCreditDelays() > 0 || companies.getAmountOfMonthlyIncome() <= 5000
                || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
            Loggers.LOGGER.info("Credit for a company can't be approved!");
        } else {
            Loggers.LOGGER.info("Credit for a company is approved!");
        }
    }
}
