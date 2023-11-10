package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ConsoleScanner.CreationObjectsFromConsole;
import Exceptions.AgeException;
import LoggerInstance.Loggers;
import StructureOfTheBank.CreditDepartment;

import java.util.Objects;

public class ClientsIndividuals extends BaseClient {

    private int age;

    public ClientsIndividuals() {
        super();
        this.age = 1;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeException {
        if (age <= 0) {
            throw new AgeException("The age number is wrong");
        } else {
            this.age = age;
            Loggers.LOGGER.info("Age got a value of {}", age);
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
        System.out.println("Enter the amount of money to top up the balance");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Try to top up balance in the amount of {}", amountOfMoneyForOperation);
        totalAccountBalance += amountOfMoneyForOperation;
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        Loggers.LOGGER.info("Your current balance is {}", totalAccountBalance);
    }

    @Override
    public void toAskForACredit() {
        Loggers.LOGGER.info("Try to ask for a credit");
        CreditDepartment creditDepartment = new CreditDepartment();
        creditDepartment.toApproveCredit(this);
    }

    @Override
    public void resetToDefaultValues() {
        super.resetToDefaultValues();
        this.age = 0;
    }
}
