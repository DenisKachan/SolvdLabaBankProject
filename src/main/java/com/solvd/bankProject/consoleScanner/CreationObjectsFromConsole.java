package com.solvd.bankProject.consoleScanner;

import com.solvd.bankProject.bankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.clientsOfTheBank.ClientsIndividuals;
import com.solvd.bankProject.clientsOfTheBank.Companies;
import com.solvd.bankProject.exceptions.*;
import com.solvd.bankProject.structureOfTheBank.ATM;
import lombok.extern.log4j.Log4j2;


import java.util.Scanner;

@Log4j2
public final class CreationObjectsFromConsole {

    public static final Scanner scanner = new Scanner(System.in);

    public ATM createATMFromConsole() {
        ATM atm = new ATM();
        log.info("Enter the address of the ATM");
        atm.setAddress(scanner.next());
        log.info("Enter the balance of the ATM");
        try {
            atm.setCurrentBalance(scanner.nextDouble());
        } catch (TotalAccountBalanceException e) {
            log.error(e.getMessage());
        }
        return atm;
    }

    public ClientsIndividuals createClientIndividualFromConsole() {
        ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
        log.info("Enter the name of the client");
        clientsIndividuals.setName(scanner.next());
        log.info("Enter the ID number of the client");
        try {
            clientsIndividuals.setAccountIdNumber(scanner.nextInt());
        } catch (AccountIdNumberException e) {
            log.error(e.getMessage());
        }
        log.info("Enter the balance of the client");
        double balance = scanner.nextDouble();
        try {
            clientsIndividuals.setTotalAccountBalance(balance);
        } catch (TotalAccountBalanceException e) {
            log.error(e.getMessage());
            balance = 0;
        }
        log.info("How many credit delays are in the history of the client?");
        try {
            clientsIndividuals.setCreditDelays(scanner.nextInt());
        } catch (CreditDelaysException e) {
            log.error(e.getMessage());
        }
        log.info("Enter the amount of monthly income of the client");
        try {
            clientsIndividuals.setAmountOfMonthlyIncome(scanner.nextDouble());
        } catch (AmountOfMonthlyIncomeException e) {
            log.error(e.getMessage());
        }
        log.info("Enter the age of the client");
        try {
            clientsIndividuals.setAge(scanner.nextInt());
        } catch (AgeException e) {
            log.error(e.getMessage());
        }
        clientsIndividuals.setCreditCardData(clientsIndividuals.getAccountIdNumber(),
                clientsIndividuals.getTotalAccountBalance());
        log.info("Client got a credit card - {}", clientsIndividuals.getCreditCard());
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return clientsIndividuals;
    }

    public Companies createCompanyFromConsole() {
        Companies companies = new Companies();
        log.info("Enter the name of the client");
        companies.setName(scanner.next());
        log.info("Enter the ID number of the client");
        try {
            companies.setAccountIdNumber(scanner.nextInt());
        } catch (AccountIdNumberException e) {
            log.error(e.getMessage());
        }
        log.info("Enter the balance of the client");
        double balance = scanner.nextDouble();
        try {
            companies.setTotalAccountBalance(balance);
        } catch (TotalAccountBalanceException e) {
            log.error(e.getMessage());
            balance = 0;
        }
        log.info("How many credit delays are in the history of the client?");
        try {
            companies.setCreditDelays(scanner.nextInt());
        } catch (CreditDelaysException e) {
            log.error(e.getMessage());
        }
        log.info("Enter the amount of monthly income of the client");
        try {
            companies.setAmountOfMonthlyIncome(scanner.nextDouble());
        } catch (AmountOfMonthlyIncomeException e) {
            log.error(e.getMessage());
        }
        log.info("Enter the year of foundation od the company");
        try {
            companies.setYearOfFoundation(scanner.nextInt());
        } catch (YearOfFoundationException e) {
            log.error(e.getMessage());
        }
        companies.setCreditCardData(companies.getAccountIdNumber(),
                companies.getTotalAccountBalance());
        log.info("Client got a credit card - {}", companies.getCreditCard());
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return companies;
    }
}
