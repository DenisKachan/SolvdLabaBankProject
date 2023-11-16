package com.solvd.bankProject.ClientsOfTheBank;

import com.solvd.bankProject.BankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.ClientsPropertyAndHistory.OperationsWithMoneyHistory;
import com.solvd.bankProject.ConsoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.Exceptions.AgeException;
import com.solvd.bankProject.StructureOfTheBank.CreditDepartment;
import com.solvd.bankProject.StructureOfTheBank.ManagementDepartment;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Getter
@Log4j2
public class ClientsIndividuals extends BaseClient {

    private int age;

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
        totalAccountBalance += amountOfMoneyForOperation;
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() + amountOfMoneyForOperation);
        OperationsWithMoneyHistory operations = new OperationsWithMoneyHistory("Top up balance in the amount of ",
                amountOfMoneyForOperation);
        clientsOperations.addToTheEndOfTheList(operations);
        log.info("Your current balance is {}", totalAccountBalance);
    }

    @Override
    public void toAskForACredit() {
        log.info("Try to ask for a credit");
        CreditDepartment creditDepartment = new CreditDepartment();
        creditDepartment.toApproveCredit(this);
    }

    @Override
    public void resetToDefaultValues() {
        super.resetToDefaultValues();
        this.age = 0;
    }
}
