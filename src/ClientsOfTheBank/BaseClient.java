package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ConsoleScanner.CreationObjectsFromConsole;
import Exceptions.*;
import Interfaces.ICurrency;
import Interfaces.Resettable;
import Interfaces.Showing;
import StructureOfTheBank.ATM;

import java.util.Objects;
import java.util.Scanner;

public abstract class BaseClient implements Showing, Resettable, ICurrency {

    protected String name;
    protected int accountIdNumber;
    protected double totalAccountBalance;
    protected final static double commissionFee;
    private int creditDelays;
    private double amountOfMonthlyIncome;
    private static int amountOfClients;
    private static double financialFlows;

    static {
        commissionFee = 0.01;
    }

    public BaseClient() {
        this.name = "Unknown";
        this.accountIdNumber = 1;
        this.totalAccountBalance = 0;
        this.creditDelays = 0;
        this.amountOfMonthlyIncome = 0;
        amountOfClients++;
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(totalAccountBalance / 2);
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(totalAccountBalance / 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseClient that = (BaseClient) o;
        return accountIdNumber == that.accountIdNumber && Double.compare(that.totalAccountBalance, totalAccountBalance)
                == 0 && creditDelays == that.creditDelays && Double.compare(that.amountOfMonthlyIncome, amountOfMonthlyIncome)
                == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accountIdNumber, totalAccountBalance, commissionFee, creditDelays, amountOfMonthlyIncome);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException {
        if (name.equals("")){throw new NameException("Your name is wrong");} else {
            this.name = name;
        }
    }

    public int getAccountIdNumber() {
        return accountIdNumber;
    }

    public void setAccountIdNumber(int accountIdNumber) throws AccountIdNumberException {
        if (accountIdNumber <= 0){throw new AccountIdNumberException("Your account ID number is wrong");} else {
            this.accountIdNumber = accountIdNumber;
        }
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    public double getTotalAccountBalance() {
        return totalAccountBalance;
    }

    public void setTotalAccountBalance(double totalAccountBalance) throws TotalAccountBalanceException {
        if (totalAccountBalance<0){throw new TotalAccountBalanceException("Something wrong with the amount of balance");}
        else {  this.totalAccountBalance = totalAccountBalance;}
    }

    public int getCreditDelays() {
        return creditDelays;
    }

    public void setCreditDelays(int creditDelays) throws CreditDelaysException {
        if (creditDelays<0){throw new CreditDelaysException("The number of credit delays is wrong");}
        else {this.creditDelays = creditDelays;}
    }

    public double getAmountOfMonthlyIncome() {
        return amountOfMonthlyIncome;
    }

    public void setAmountOfMonthlyIncome(double amountOfMonthlyIncome) throws AmountOfMonthlyIncomeException {
        if (amountOfMonthlyIncome<0){throw new AmountOfMonthlyIncomeException("The amount of monthly income is wrong");}
        else {this.amountOfMonthlyIncome = amountOfMonthlyIncome;}
    }

    public static int getAmountOfClients() {
        return amountOfClients;
    }

    public static double getFinancialFlows() {
        return financialFlows;
    }

    public abstract void toTopUpBalance() throws TotalAccountBalanceException;

    public abstract void toAskForACredit();

    @Override
    public void showBalance() {
        System.out.println(this.totalAccountBalance);
    }

    @Override
    public void showFullInformation() {
        System.out.println(this);
    }

    public void withdrawCash() {
            System.out.println("Confirm the amount of money to withdraw from the balance");
            double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
            CurrentAccountOfTheBank.getInstance();
            if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentCashBalance()
                    && amountOfMoneyForOperation <= totalAccountBalance) {
                totalAccountBalance -= amountOfMoneyForOperation;
                CurrentAccountOfTheBank.getInstance();
                CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoneyForOperation);
                financialFlows += amountOfMoneyForOperation;
                System.out.println("Your current balance is " + totalAccountBalance);
            } else {
                if (amountOfMoneyForOperation > totalAccountBalance) {
                    System.out.println("This operation can not be performed - not enough money in your account");
                } else {
                    System.out.println("This operation can not be performed");
                }
            }
    }

    public void withdrawCash(ATM atm) throws TotalAccountBalanceException {
            System.out.println("Enter the amount of money to withdraw from the balance in the ATM");
            double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
            if (atm.checkTheAbilityToWithdrawMoney(amountOfMoneyForOperation)) {
                withdrawCash();
                atm.setCurrentBalance(atm.getCurrentBalance() - amountOfMoneyForOperation);
                financialFlows += amountOfMoneyForOperation;
            } else {
                System.out.println("This operation can not be performed, please wait for the collection service");
                atm.callTheCollectionService(amountOfMoneyForOperation);
            }
    }

    public void transferMoney(BaseClient newClient) throws TotalAccountBalanceException {
            System.out.println("Enter the amount of money to transfer to the other client of the bank");
            double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
            if (amountOfMoneyForOperation <= totalAccountBalance) {
                totalAccountBalance -= amountOfMoneyForOperation;
                newClient.setTotalAccountBalance(newClient.getTotalAccountBalance() + amountOfMoneyForOperation);
                financialFlows += amountOfMoneyForOperation;
                System.out.println("Successful! Your current balance is " + totalAccountBalance);
            } else {
                System.out.println("This operation can not be performed");
            }
    }

    public void transferMoney() {
            System.out.println("Enter the amount of money to transfer to the client of the other bank");
            double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
            CurrentAccountOfTheBank.getInstance();
            if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()
                    && amountOfMoneyForOperation <= totalAccountBalance) {
                totalAccountBalance = totalAccountBalance - amountOfMoneyForOperation * (1 + commissionFee);
                CurrentAccountOfTheBank.getInstance();
                CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(amountOfMoneyForOperation
                        * (1 - commissionFee));
                System.out.println("Your current balance is " + totalAccountBalance);
                financialFlows += amountOfMoneyForOperation;
            } else {
                if (amountOfMoneyForOperation > totalAccountBalance) {
                    System.out.println("This operation can not be performed - not enough money in your account");
                } else {
                    System.out.println("This operation can not be performed");
                }
            }
    }

    public void toTopUpBalanceThroughATM(ATM atm) throws TotalAccountBalanceException {
            System.out.println("Enter the amount of money to top up through ATM");
            double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
            totalAccountBalance += amountOfMoneyForOperation;
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
            atm.setCurrentBalance(atm.getCurrentBalance() + amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            System.out.println("Your current balance is " + getTotalAccountBalance()
                    + ", The current balance of the ATM is " + atm.getCurrentBalance());
    }

    @Override
    public void resetToDefaultValues() {
        this.name = "Unknown";
        this.accountIdNumber = 0;
        this.totalAccountBalance = 0;
        this.creditDelays = 0;
        this.amountOfMonthlyIncome = 0;
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(totalAccountBalance / 2);
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(totalAccountBalance / 2);
    }

    public double exchangeMoney(){
            System.out.println("Enter the amount of rubles you want to exchange");
            double amountOfMoney = CreationObjectsFromConsole.scanner.nextDouble();
            Scanner currencyName = new Scanner(System.in);
            System.out.println("Enter the name of the currency you want to get");
            String currency = currencyName.nextLine();
            String wantedCurrency = currency.toLowerCase();
            double foreignCurrency;
            if (amountOfMoney <= getTotalAccountBalance()) {
                switch (wantedCurrency) {
                    case "euro" -> {
                        foreignCurrency = amountOfMoney / EURO_CURRENCY;
                        System.out.println("You will get " + foreignCurrency + " euro");
                        totalAccountBalance -= amountOfMoney;
                        CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                    }
                    case "dollar" -> {
                        foreignCurrency = amountOfMoney / DOLLAR_CURRENCY;
                        System.out.println("You will get " + foreignCurrency + " dollars");
                        totalAccountBalance -= amountOfMoney;
                        CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                    }
                }
            } else {
                System.out.println("You have no so much money in your account");
            }
            return amountOfMoney;
        }
}
