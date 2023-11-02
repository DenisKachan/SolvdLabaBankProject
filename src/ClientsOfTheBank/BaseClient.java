package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import StructureOfTheBank.ATM;
import StructureOfTheBank.CreditDepartment;

import java.util.Scanner;

public abstract class BaseClient {

    protected String name;
    protected int accountIdNumber;
    protected double totalAccountBalance;
    protected double commissionFee = 0.01;
    private int creditDelays;
    private double amountOfMonthlyIncome;

    public BaseClient() {
        Scanner nameOfTheClient = new Scanner(System.in);
        System.out.println("Enter the name of the client");
        this.name = nameOfTheClient.nextLine();
        Scanner idOfTheClient = new Scanner(System.in);
        System.out.println("Enter the ID number of the client");
        this.accountIdNumber = idOfTheClient.nextInt();
        Scanner depositOfTheClient = new Scanner(System.in);
        System.out.println("Enter the balance of the client");
        this.totalAccountBalance = depositOfTheClient.nextDouble();
        Scanner creditDelaysOfTheClient = new Scanner(System.in);
        System.out.println("How many credit delays are in the history of the client?");
        this.creditDelays = creditDelaysOfTheClient.nextInt();
        Scanner monthlyIncomeOfTheClient = new Scanner(System.in);
        System.out.println("Enter the amount of monthly income of the client");
        this.amountOfMonthlyIncome = monthlyIncomeOfTheClient.nextDouble();
        CurrentAccountOfTheBank.getInstance().increaseCurrentNonCashBalance(totalAccountBalance / 2);
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(totalAccountBalance / 2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected double getTotalAccountBalance() {
        return totalAccountBalance;
    }

    protected void setTotalAccountBalance(double totalAccountBalance) {
        this.totalAccountBalance = totalAccountBalance;
    }

    public int getCreditDelays() {
        return creditDelays;
    }

    public void setCreditDelays(int creditDelays) {
        this.creditDelays = creditDelays;
    }

    public double getAmountOfMonthlyIncome() {
        return amountOfMonthlyIncome;
    }

    public void setAmountOfMonthlyIncome(double amountOfMonthlyIncome) {
        this.amountOfMonthlyIncome = amountOfMonthlyIncome;
    }

    public abstract void toTopUpBalance();


    public void withdrawCashFromBankAccount() {
        Scanner amountOfMoney = new Scanner(System.in);
        System.out.println("Confirm the amount of money to withdraw from the balance");
        double amountOfMoneyForOperation = amountOfMoney.nextDouble();
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentCashBalance()
                && amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance -= amountOfMoneyForOperation;
            CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoneyForOperation);
            System.out.println("Your current balance is " + totalAccountBalance);
        } else {
            if (amountOfMoneyForOperation > totalAccountBalance) {
                System.out.println("This operation can not be performed - not enough money in your account");
            } else {
                System.out.println("This operation can not be performed");
            }
        }
    }

    public void transferNonCashFromBankAccountToTheOtherBankWithCommission() {
        Scanner amountOfMoney = new Scanner(System.in);
        System.out.println("Enter the amount of money to transfer to the client of the other bank");
        double amountOfMoneyForOperation = amountOfMoney.nextDouble();
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()
                && amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance = totalAccountBalance - amountOfMoneyForOperation * (1 + commissionFee);
            CurrentAccountOfTheBank.getInstance()
                    .decreaseCurrentNonCashBalance(amountOfMoneyForOperation * (1 - commissionFee));
            System.out.println("Your current balance is " + totalAccountBalance);
        } else {
            if (amountOfMoneyForOperation > totalAccountBalance) {
                System.out.println("This operation can not be performed - not enough money in your account");
            } else {
                System.out.println("This operation can not be performed");
            }
        }
    }

    public void withdrawCashFromATM(ATM atm) {
        Scanner amountOfMoney = new Scanner(System.in);
        System.out.println("Enter the amount of money to withdraw from the balance in the ATM");
        double amountOfMoneyForOperation = amountOfMoney.nextDouble();
        if (atm.checkTheAbilityToWithdrawMoney(amountOfMoneyForOperation)) {
            withdrawCashFromBankAccount();
            atm.setCurrentBalance(atm.getCurrentBalance() - amountOfMoneyForOperation);
        } else {
            System.out.println("This operation can not be performed, please wait for the collection service");
            atm.callTheCollectionService(amountOfMoneyForOperation);
        }
    }

    public void transferMoneyToTheOtherClientOfTheBank(BaseClient newClient) {
        Scanner amountOfMoney = new Scanner(System.in);
        System.out.println("Enter the amount of money to transfer to the other client of the bank");
        double amountOfMoneyForOperation = amountOfMoney.nextDouble();
        if (amountOfMoneyForOperation <= totalAccountBalance) {
            setTotalAccountBalance(totalAccountBalance - amountOfMoneyForOperation);
            newClient.setTotalAccountBalance(newClient.getTotalAccountBalance() + amountOfMoneyForOperation);
            System.out.println("Successful! Your current balance is " + totalAccountBalance);
        } else {
            System.out.println("This operation can not be performed");
        }
    }

    public void toAskForACredit() {
        CreditDepartment creditDepartment = new CreditDepartment();
        creditDepartment.toApproveACredit(this);
    }
}
