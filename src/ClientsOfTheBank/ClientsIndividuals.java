package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import StructureOfTheBank.CreditDepartment;

import java.util.Objects;
import java.util.Scanner;

public class ClientsIndividuals extends BaseClient {

    private int age;

    public ClientsIndividuals() {
        super();
        this.age = 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        Scanner amountOfMoney = new Scanner(System.in);
        System.out.println("Enter the amount of money to top up the balance");
        double amountOfMoneyForOperation = amountOfMoney.nextDouble();
        setTotalAccountBalance(getTotalAccountBalance() + amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
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
        this.age = 0;
    }
}
