package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import StructureOfTheBank.ATM;

import java.util.Objects;
import java.util.Scanner;

public class ClientsIndividuals extends BaseClient {

    private int age;

    public ClientsIndividuals() {
        super();
        Scanner ageOfTheClient = new Scanner(System.in);
        System.out.println("Enter the age of the client");
        this.age = ageOfTheClient.nextInt();
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

    public void toTopUpBalanceThroughATM(ATM atm) {
        Scanner amountOfMoney = new Scanner(System.in);
        System.out.println("Enter the amount of money to top up through ATM");
        double amountOfMoneyForOperation = amountOfMoney.nextDouble();
        setTotalAccountBalance(getTotalAccountBalance() + amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        atm.setCurrentBalance(atm.getCurrentBalance() + amountOfMoneyForOperation);
        System.out.println("Your current balance is " + getTotalAccountBalance()
                + ", The current balance of the ATM is " + atm.getCurrentBalance());
    }
}
