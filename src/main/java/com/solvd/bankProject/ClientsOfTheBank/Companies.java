package com.solvd.bankProject.ClientsOfTheBank;

import com.solvd.bankProject.BankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.ClientsPropertyAndHistory.OperationsWithMoneyHistory;
import com.solvd.bankProject.ConsoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.Exceptions.YearOfFoundationException;
import com.solvd.bankProject.StructureOfTheBank.CreditDepartment;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.time.Year;
import java.util.Objects;

@Getter
@Log4j2
public class Companies extends BaseClient {

    private int yearOfFoundation;

    public Companies() {
        super();
        this.yearOfFoundation = Year.now().getValue();
    }

    public void setYearOfFoundation(int yearOfFoundation) throws YearOfFoundationException {
        if (yearOfFoundation > Year.now().getValue()) {
            throw new YearOfFoundationException("The year of foundation is not appropriate");
        } else {
            this.yearOfFoundation = yearOfFoundation;
            log.info("Year of foundation got a value of {}", yearOfFoundation);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companies companies = (Companies) o;
        return yearOfFoundation == companies.yearOfFoundation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearOfFoundation);
    }

    @Override
    public String toString() {
        return "Companies{" +
                "Name='" + name + '\'' +
                ", Account ID Number=" + accountIdNumber +
                ", Total Account Balance=" + totalAccountBalance +
                ", Credit Delays=" + getCreditDelays() +
                ", Amount Of Monthly Income=" + getAmountOfMonthlyIncome() +
                ", Year Of Foundation=" + yearOfFoundation +
                '}';
    }

    @Override
    public void toTopUpBalance() {
        log.info("Enter the amount of money to top the balance of the company");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Try to top up balance in the amount of {}", amountOfMoneyForOperation);
        totalAccountBalance += amountOfMoneyForOperation;
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(amountOfMoneyForOperation);
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
        this.yearOfFoundation = 0;
    }
}
