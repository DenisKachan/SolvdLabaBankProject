package com.solvd.bankProject.clientsOfTheBank;

import com.solvd.bankProject.bankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.exceptions.*;
import com.solvd.bankProject.interfaces.Copying;
import com.solvd.bankProject.structureOfTheBank.CreditDepartment;
import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Locale;
import java.util.Objects;

@Getter
@Log4j2
public class ClientsIndividuals extends BaseClient{

    private int age;

    Copying<ClientsIndividuals> copyAll = () -> {
        ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
        clientsIndividuals.setName(this.name);
        clientsIndividuals.setAge(this.age);
        clientsIndividuals.setCreditDelays(this.getCreditDelays());
        clientsIndividuals.setAmountOfMonthlyIncome(this.getAmountOfMonthlyIncome());
        clientsIndividuals.setAccountIdNumber(this.accountIdNumber);
        clientsIndividuals.setTotalAccountBalance(this.totalAccountBalance);
        clientsIndividuals.setCreditCardData(this.getCreditCard().getIdNumber(), this.getCreditCard().getCreditCardBalance());
        return clientsIndividuals;
    };

    Copying<ClientsIndividuals> copyName = () -> {
        ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
        clientsIndividuals.setName(this.name);
        return clientsIndividuals;
    };

    public ClientsIndividuals() {
        super();
        this.age = 1;
        ManagementDepartment.clientsIndividualsLinkedListForEntities.addToTheEndOfTheList(this);
    }

    public void setAge(int age) throws AgeException {
        if (age <= 0) {
            throw new AgeException("The age number is wrong");
        } else {
            this.age = age;
            log.info("Age got a value of {}", age);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsIndividuals that = (ClientsIndividuals) o;
        return age == that.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return "ClientsIndividuals{" +
                "Name='" + name + '\'' +
                ", Account ID Number=" + accountIdNumber +
                ", Total Account Balance=" + totalAccountBalance +
                ", Credit Delays=" + getCreditDelays() +
                ", Amount Of Monthly Income=" + getAmountOfMonthlyIncome() +
                ", Age=" + age +
                '}';
    }

    @Override
    public void toTopUpBalance() {
        log.info("Enter the amount of money to top up the balance");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Try to top up balance in the amount of {}", amountOfMoneyForOperation);
        increaseBalanceAfterAction.changeAmount(amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        addToListOfOperations.addInstance("Top up balance", amountOfMoneyForOperation);
        increaseFinancialFlows.apply(amountOfMoneyForOperation);
        log.info("Your current balance is {}", totalAccountBalance);
    }

    @Override
    public void toAskForACredit() {
        log.info("Try to ask for a credit");
        CreditDepartment creditDepartment = new CreditDepartment();
        creditDepartment.toApproveCredit(this);
    }


    public ClientsIndividuals copyThisClient() throws TotalAccountBalanceException, AgeException,
            AmountOfMonthlyIncomeException, CreditDelaysException, AccountIdNumberException {
        ClientsIndividuals clientsIndividuals = null;
        log.info("Enter what you want to copy from this client");
        String word = CreationObjectsFromConsole.scanner.next().toLowerCase(Locale.ROOT);
        switch (word) {
            case "all" -> {
                clientsIndividuals = copyAll.copyValues();
            }
            case "name" -> {
                clientsIndividuals = copyName.copyValues();
            }
        }
        return clientsIndividuals;
    }
}
