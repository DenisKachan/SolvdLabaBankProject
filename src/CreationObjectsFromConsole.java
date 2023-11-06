import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.ClientsIndividuals;
import ClientsOfTheBank.Companies;
import StructureOfTheBank.ATM;

import java.util.Scanner;

public class CreationObjectsFromConsole {

    public ClientsIndividuals createClientIndividualFromConsole() {
        ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
        Scanner nameOfTheClient = new Scanner(System.in);
        System.out.println("Enter the name of the client");
        clientsIndividuals.setName(nameOfTheClient.nextLine());
        Scanner idOfTheClient = new Scanner(System.in);
        System.out.println("Enter the ID number of the client");
        clientsIndividuals.setAccountIdNumber(idOfTheClient.nextInt());
        Scanner depositOfTheClient = new Scanner(System.in);
        System.out.println("Enter the balance of the client");
        double balance = depositOfTheClient.nextDouble();
        clientsIndividuals.setTotalAccountBalance(balance);
        Scanner creditDelaysOfTheClient = new Scanner(System.in);
        System.out.println("How many credit delays are in the history of the client?");
        clientsIndividuals.setCreditDelays(creditDelaysOfTheClient.nextInt());
        Scanner monthlyIncomeOfTheClient = new Scanner(System.in);
        System.out.println("Enter the amount of monthly income of the client");
        clientsIndividuals.setAmountOfMonthlyIncome(monthlyIncomeOfTheClient.nextDouble());
        Scanner ageOfTheClient = new Scanner(System.in);
        System.out.println("Enter the age of the client");
        clientsIndividuals.setAge(ageOfTheClient.nextInt());
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return clientsIndividuals;
    }

    public Companies createCompanyFromConsole() {
        Companies companies = new Companies();
        Scanner nameOfTheClient = new Scanner(System.in);
        System.out.println("Enter the name of the client");
        companies.setName(nameOfTheClient.nextLine());
        Scanner idOfTheClient = new Scanner(System.in);
        System.out.println("Enter the ID number of the client");
        companies.setAccountIdNumber(idOfTheClient.nextInt());
        Scanner depositOfTheClient = new Scanner(System.in);
        System.out.println("Enter the balance of the client");
        double balance = depositOfTheClient.nextDouble();
        companies.setTotalAccountBalance(balance);
        Scanner creditDelaysOfTheClient = new Scanner(System.in);
        System.out.println("How many credit delays are in the history of the client?");
        companies.setCreditDelays(creditDelaysOfTheClient.nextInt());
        Scanner monthlyIncomeOfTheClient = new Scanner(System.in);
        System.out.println("Enter the amount of monthly income of the client");
        companies.setAmountOfMonthlyIncome(monthlyIncomeOfTheClient.nextDouble());
        Scanner yearOfFoundationOfTheCompany = new Scanner(System.in);
        System.out.println("Enter the year of foundation of the company");
        companies.setYearOfFoundation(yearOfFoundationOfTheCompany.nextInt());
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(balance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(balance / 2);
        return companies;
    }

    public ATM createATMFromConsole() {
        ATM atm = new ATM();
        Scanner addressOfTheATM = new Scanner(System.in);
        System.out.println("Enter the address of the ATM");
        String atmAddress = addressOfTheATM.nextLine();
        Scanner currentBalanceOfTheATM = new Scanner(System.in);
        System.out.println("Enter the balance of the ATM");
        double atmBalance = currentBalanceOfTheATM.nextDouble();
        if (atmBalance <= CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()) {
            atm.setAddress(atmAddress);
            atm.setCurrentBalance(atmBalance);
            return atm;
        } else {
            System.out.println("The start balance of the ATM can not be larger than the balance of the bank");
            return null;
        }
    }
}
