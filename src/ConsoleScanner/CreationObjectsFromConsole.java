package ConsoleScanner;

import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.ClientsIndividuals;
import ClientsOfTheBank.Companies;
import Exceptions.*;
import StructureOfTheBank.ATM;

import java.util.Scanner;

public final class CreationObjectsFromConsole {

    public static final Scanner scanner = new Scanner(System.in);

    public ATM createATMFromConsole() throws AddressException, TotalAccountBalanceException {
        ATM atm = new ATM();
        System.out.println("Enter the address of the ATM");
        try {
            atm.setAddress(scanner.next());
        } catch (AddressException e){
            System.out.println("The address of the ATM must include some symbols, it was changed to default value, you can change it later");
            atm.setAddress("Unknown");
        }
        System.out.println("Enter the balance of the ATM");
        try {atm.setCurrentBalance(scanner.nextDouble());
        } catch (TotalAccountBalanceException e){
            System.out.println("The balance of the ATM can't be less than 0, it was changed to default value, you can change it later");
            atm.setCurrentBalance(0);
        }
        return atm;
    }

    public ClientsIndividuals createClientIndividualFromConsole() throws NameException, AccountIdNumberException,
            TotalAccountBalanceException, AmountOfMonthlyIncomeException, CreditDelaysException, AgeException {
            ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
            System.out.println("Enter the name of the client");
            try {
                clientsIndividuals.setName(scanner.next());
            } catch (NameException e) {
                System.out.println("Your name must include some symbols, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the ID number of the client");
            try {
                clientsIndividuals.setAccountIdNumber(scanner.nextInt());
            } catch (AccountIdNumberException e) {
                System.out.println("Your account ID number can't be equal or less then 0, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the balance of the client");
            double balance = scanner.nextDouble();
            try {
                clientsIndividuals.setTotalAccountBalance(balance);
            } catch (TotalAccountBalanceException e) {
                System.out.println("Your start balance can't be less then 0, it was changed to default value, you can change it later");
                clientsIndividuals.setTotalAccountBalance(0);
                balance = 0;
            }
            System.out.println("How many credit delays are in the history of the client?");
            try {
                clientsIndividuals.setCreditDelays(scanner.nextInt());
            } catch (CreditDelaysException e) {
                System.out.println("The number of credit delays can't be less then 0, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the amount of monthly income of the client");
            try {
                clientsIndividuals.setAmountOfMonthlyIncome(scanner.nextDouble());
            } catch (AmountOfMonthlyIncomeException e) {
                System.out.println("The amount of monthly income can't be less then 0, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the age of the client");
            try {
                clientsIndividuals.setAge(scanner.nextInt());
            } catch (AgeException e) {
                System.out.println("The age can't be less or equal then 0, it was changed to default value, you can change it later");
            }
            CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
            CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return clientsIndividuals;
    }

    public Companies createCompanyFromConsole() throws NameException, AccountIdNumberException,
            TotalAccountBalanceException, AmountOfMonthlyIncomeException, CreditDelaysException, YearOfFoundationException {
            Companies companies = new Companies();
            System.out.println("Enter the name of the client");
            try {
                companies.setName(scanner.next());
            } catch (NameException e) {
                System.out.println("Your name must include some symbols, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the ID number of the client");
            try {
                companies.setAccountIdNumber(scanner.nextInt());
            } catch (AccountIdNumberException e) {
                System.out.println("Your account ID number can't be equal or less then 0, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the balance of the client");
            double balance = scanner.nextDouble();
            try {
                companies.setTotalAccountBalance(balance);
            } catch (TotalAccountBalanceException e) {
                System.out.println("Your start balance can't be less then 0, it was changed to default value, you can change it later");
                balance = 0;
            }
            System.out.println("How many credit delays are in the history of the client?");
            try {
                companies.setCreditDelays(scanner.nextInt());
            } catch (CreditDelaysException e) {
                System.out.println("The number of credit delays can't be less then 0, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the amount of monthly income of the client");
            try {
                companies.setAmountOfMonthlyIncome(scanner.nextDouble());
            } catch (AmountOfMonthlyIncomeException e) {
                System.out.println("The amount of monthly income can't be less then 0, it was changed to default value, you can change it later");
            }
            System.out.println("Enter the year of foundation od the company");
            try {
                companies.setYearOfFoundation(scanner.nextInt());
            } catch (YearOfFoundationException e) {
                System.out.println("The year of foundation can't be greater than the current year, it was changed to default value, you can change it later");
            }
            CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
            CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return companies;
    }
}
