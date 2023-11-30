package com.solvd.bankProject;

import com.solvd.bankProject.clientsOfTheBank.BaseClient;
import com.solvd.bankProject.clientsOfTheBank.ClientsIndividuals;
import com.solvd.bankProject.clientsOfTheBank.Companies;
import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.enums.Currency;
import com.solvd.bankProject.exceptions.*;
import com.solvd.bankProject.structureOfTheBank.ATM;
import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;

public class Main {
    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            ClientsIndividuals clientsIndividuals = creationObjectsFromConsole.createClientIndividualFromConsole();
            ClientsIndividuals clientsIndividuals1 = clientsIndividuals.copyThisClient();
            clientsIndividuals1.withdrawCash();
            clientsIndividuals1.showOperationsHistory();
            clientsIndividuals1.toAskForACredit();
            clientsIndividuals1.showCreditRequestsHistory();

        } catch (TotalAccountBalanceException | AgeException | AmountOfMonthlyIncomeException
                 | CreditDelaysException |
                 AccountIdNumberException e) {
            throw new RuntimeException(e);
        }
    }
}
