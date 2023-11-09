package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ConsoleScanner.CreationObjectsFromConsole;
import Exceptions.YearOfFoundationException;
import StructureOfTheBank.CreditDepartment;

import java.time.Year;
import java.util.Objects;

public class Companies extends BaseClient {

    private int yearOfFoundation;

    public Companies() {
        super();
        this.yearOfFoundation = Year.now().getValue();
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(int yearOfFoundation) throws YearOfFoundationException {
        if (yearOfFoundation> Year.now().getValue()){throw new YearOfFoundationException("The year of foundation is not appropriate");}
        else {this.yearOfFoundation = yearOfFoundation;}
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
    public void toTopUpBalance(){
            System.out.println("Enter the amount of money to top the balance of the company");
            double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
            totalAccountBalance += amountOfMoneyForOperation;
            CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(amountOfMoneyForOperation);
            System.out.println("Your current balance is " + getTotalAccountBalance());
    }

    @Override
    public void toAskForACredit() {
        CreditDepartment creditDepartment = new CreditDepartment();
        creditDepartment.toApproveCredit(this);
    }

    @Override
    public void resetToDefaultValues() {
        super.resetToDefaultValues();
        this.yearOfFoundation = 0;
    }
}
