package com.solvd.bankProject.structureOfTheBank;

import com.solvd.bankProject.clientsOfTheBank.BaseClient;
import com.solvd.bankProject.clientsOfTheBank.ClientsIndividuals;
import com.solvd.bankProject.clientsOfTheBank.Companies;
import com.solvd.bankProject.clientsPropertyAndHistory.CreditRequestsHistory;
import com.solvd.bankProject.interfaces.Adding;
import lombok.extern.log4j.Log4j2;

import java.time.Year;
import java.util.Random;

@Log4j2
public class CreditDepartment {

    private int abilityOfCreditDepartmentToProvideACredit;

    Adding<BaseClient> addToTheCreditHistory = (String result, BaseClient baseClient) -> {
        CreditRequestsHistory creditRequestsHistory = new CreditRequestsHistory();
        creditRequestsHistory.setStatus(result);
        baseClient.creditRequestsHistories.add(creditRequestsHistory);
        log.info("The id number of credit request is {}", creditRequestsHistory.getSerialNumber());
    };


    public int getAbilityOfCreditDepartmentToProvideACredit() {
        Random randomAbilityToProvideALoan = new Random();
        abilityOfCreditDepartmentToProvideACredit = randomAbilityToProvideALoan.nextInt(100);
        log.info("Random percentage of the of the credit department to provide a credit is established in the amount of {}",
                abilityOfCreditDepartmentToProvideACredit);
        return abilityOfCreditDepartmentToProvideACredit;
    }

    public void setAbilityOfCreditDepartmentToProvideACredit(int abilityToProvideALoan) {
        this.abilityOfCreditDepartmentToProvideACredit = abilityToProvideALoan;
    }

    public final void toApproveCredit(ClientsIndividuals clientsIndividuals) {
        log.info("Credit department is checking the client's data");
        if (clientsIndividuals.getAge() <= 25 || clientsIndividuals.getCreditDelays() >= 2
                || clientsIndividuals.getAmountOfMonthlyIncome() <= 1000
                || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
            addToTheCreditHistory.addInstance("failed", clientsIndividuals);
            log.info("Credit for a client individual can't be approved!");
        } else {
            log.info("Credit for the client individual is approved!");
            addToTheCreditHistory.addInstance("failed", clientsIndividuals);
        }
    }

    public final void toApproveCredit(Companies companies) {
        log.info("Credit department is checking the client's data");
        if (Year.now().getValue() - companies.getYearOfFoundation() < 5
                || companies.getCreditDelays() > 0 || companies.getAmountOfMonthlyIncome() <= 5000
                || getAbilityOfCreditDepartmentToProvideACredit() < 50) {
            log.info("Credit for a company can't be approved!");
            addToTheCreditHistory.addInstance("failed", companies);
        } else {
            log.info("Credit for a company is approved!");
            CreditRequestsHistory creditRequestsHistory = new CreditRequestsHistory();
            addToTheCreditHistory.addInstance("approved", companies);
        }
    }
}
