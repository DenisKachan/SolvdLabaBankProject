package ConsoleScanner;

import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.ClientsIndividuals;
import ClientsOfTheBank.Companies;
import Exceptions.*;
import LoggerInstance.Loggers;
import StructureOfTheBank.ATM;


import java.util.Scanner;

public final class CreationObjectsFromConsole {

    public static final Scanner scanner = new Scanner(System.in);

    public ATM createATMFromConsole() {
        ATM atm = new ATM();
        System.out.println("Enter the address of the ATM");
        atm.setAddress(scanner.next());
        System.out.println("Enter the balance of the ATM");
        try {
            atm.setCurrentBalance(scanner.nextDouble());
        } catch (TotalAccountBalanceException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        return atm;
    }

    public ClientsIndividuals createClientIndividualFromConsole() {
        ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
        System.out.println("Enter the name of the client");
        clientsIndividuals.setName(scanner.next());
        System.out.println("Enter the ID number of the client");
        try {
            clientsIndividuals.setAccountIdNumber(scanner.nextInt());
        } catch (AccountIdNumberException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        System.out.println("Enter the balance of the client");
        double balance = scanner.nextDouble();
        try {
            clientsIndividuals.setTotalAccountBalance(balance);
        } catch (TotalAccountBalanceException e) {
            Loggers.LOGGER.error(e.getMessage());
            balance = 0;
        }
        System.out.println("How many credit delays are in the history of the client?");
        try {
            clientsIndividuals.setCreditDelays(scanner.nextInt());
        } catch (CreditDelaysException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        System.out.println("Enter the amount of monthly income of the client");
        try {
            clientsIndividuals.setAmountOfMonthlyIncome(scanner.nextDouble());
        } catch (AmountOfMonthlyIncomeException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        System.out.println("Enter the age of the client");
        try {
            clientsIndividuals.setAge(scanner.nextInt());
        } catch (AgeException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return clientsIndividuals;
    }

    public Companies createCompanyFromConsole() {
        Companies companies = new Companies();
        System.out.println("Enter the name of the client");
        companies.setName(scanner.next());
        System.out.println("Enter the ID number of the client");
        try {
            companies.setAccountIdNumber(scanner.nextInt());
        } catch (AccountIdNumberException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        System.out.println("Enter the balance of the client");
        double balance = scanner.nextDouble();
        try {
            companies.setTotalAccountBalance(balance);
        } catch (TotalAccountBalanceException e) {
            Loggers.LOGGER.error(e.getMessage());
            balance = 0;
        }
        System.out.println("How many credit delays are in the history of the client?");
        try {
            companies.setCreditDelays(scanner.nextInt());
        } catch (CreditDelaysException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        System.out.println("Enter the amount of monthly income of the client");
        try {
            companies.setAmountOfMonthlyIncome(scanner.nextDouble());
        } catch (AmountOfMonthlyIncomeException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        System.out.println("Enter the year of foundation od the company");
        try {
            companies.setYearOfFoundation(scanner.nextInt());
        } catch (YearOfFoundationException e) {
            Loggers.LOGGER.error(e.getMessage());
        }
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return companies;
    }
}
