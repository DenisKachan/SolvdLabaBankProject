package com.solvd.bankProject.ClientsOfTheBank;

import com.solvd.bankProject.BankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.ClientsPropertyAndHistory.CreditCard;
import com.solvd.bankProject.ClientsPropertyAndHistory.CreditRequestsHistory;
import com.solvd.bankProject.ClientsPropertyAndHistory.OperationsWithMoneyHistory;
import com.solvd.bankProject.ConsoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.CustomLinkedList.LinkedListForEntities;
import com.solvd.bankProject.Exceptions.AccountIdNumberException;
import com.solvd.bankProject.Exceptions.AmountOfMonthlyIncomeException;
import com.solvd.bankProject.Exceptions.CreditDelaysException;
import com.solvd.bankProject.Exceptions.TotalAccountBalanceException;
import com.solvd.bankProject.Interfaces.ICurrency;
import com.solvd.bankProject.Interfaces.Resettable;
import com.solvd.bankProject.Interfaces.Showing;
import com.solvd.bankProject.StructureOfTheBank.ATM;
import com.solvd.bankProject.StructureOfTheBank.ManagementDepartment;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public abstract class BaseClient implements Showing, Resettable, ICurrency {

    @Getter
    protected String name;
    @Getter
    protected int accountIdNumber;
    @Getter
    protected double totalAccountBalance;
    protected final static double commissionFee;
    @Getter
    private int creditDelays;
    @Getter
    private double amountOfMonthlyIncome;
    @Getter
    private static int amountOfClients;
    @Getter
    private static double financialFlows;

    public LinkedListForEntities<OperationsWithMoneyHistory> clientsOperations = new LinkedListForEntities<>();

    public LinkedListForEntities<CreditRequestsHistory> creditRequestsHistories = new LinkedListForEntities<>();


    @Getter
    private CreditCard creditCard;

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
        creditCard = new CreditCard(getAccountIdNumber(), getTotalAccountBalance());
        ManagementDepartment.cardClientsIndividuals.put(this.creditCard, this);
        log.info("Client with default values was created");
        log.info("Total amount of clients was increased by one");
        log.info("Client got a new credit card");
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

    public void setName(String name) {
        this.name = name;
        log.info("Name got a value of {}", name);
    }


    public void setAccountIdNumber(int accountIdNumber) throws AccountIdNumberException {
        if (accountIdNumber <= 0) {
            throw new AccountIdNumberException("Your account ID number is wrong");
        } else {
            this.accountIdNumber = accountIdNumber;
            log.info("Account ID number got a value of {}", accountIdNumber);
        }
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    public void setTotalAccountBalance(double totalAccountBalance) throws TotalAccountBalanceException {
        if (totalAccountBalance < 0) {
            throw new TotalAccountBalanceException("Something wrong with the amount of balance");
        } else {
            this.totalAccountBalance = totalAccountBalance;
            log.info("Account balance got a value of {}", totalAccountBalance);
        }
    }

    public void setCreditDelays(int creditDelays) throws CreditDelaysException {
        if (creditDelays < 0) {
            throw new CreditDelaysException("The number of credit delays is wrong");
        } else {
            this.creditDelays = creditDelays;
            log.info("Number of credit delays got a value of {}", creditDelays);
        }
    }

    public void setAmountOfMonthlyIncome(double amountOfMonthlyIncome) throws AmountOfMonthlyIncomeException {
        if (amountOfMonthlyIncome < 0) {
            throw new AmountOfMonthlyIncomeException("The amount of monthly income is wrong");
        } else {
            this.amountOfMonthlyIncome = amountOfMonthlyIncome;
            log.info("Amount of monthly income got a value of {}", amountOfMonthlyIncome);
        }
    }

    public void setCreditCardData(int idNumber, double creditCardBalance) {
        creditCard.setIdNumber(idNumber);
        creditCard.setCreditCardBalance(creditCardBalance);
    }

    public abstract void toTopUpBalance() throws TotalAccountBalanceException;

    public abstract void toAskForACredit();

    @Override
    public void showBalance() {
        log.info("Total balance of the client is {}", this.totalAccountBalance);
    }

    @Override
    public void showFullInformation() {
        log.info("Full information about the client is the following {}", this);
    }

    public void withdrawCash() {
        log.info("Confirm the amount of money to withdraw from the balance");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Withdraw cash from bank account in the amount of {}", amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance();
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentCashBalance()
                && amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance -= amountOfMoneyForOperation;
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoneyForOperation);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Withdraw cash from department in the amount of",
                    amountOfMoneyForOperation);
            clientsOperations.addToTheEndOfTheList(operationsWithMoneyHistory);
            log.info("Your current balance is {}", totalAccountBalance);
        } else {
            if (amountOfMoneyForOperation > totalAccountBalance) {
                log.info("This operation can not be performed - not enough money in your account");
            } else {
                log.info("This operation can not be performed");
            }
        }
    }

    public void withdrawCash(ATM atm) throws TotalAccountBalanceException {
        log.info("Enter the amount of money to withdraw from the balance in the ATM");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Try to withdraw cash from the ATM");
        if (atm.checkTheAbilityToWithdrawMoney(amountOfMoneyForOperation)) {
            withdrawCash();
            atm.setCurrentBalance(atm.getCurrentBalance() - amountOfMoneyForOperation);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Withdraw cash from ATM in the amount of",
                    amountOfMoneyForOperation);
            clientsOperations.addToTheEndOfTheList(operationsWithMoneyHistory);
        } else {
            log.info("This operation can not be performed, please wait for the collection service");
            atm.callTheCollectionService(amountOfMoneyForOperation);
        }
    }

    public void transferMoney(BaseClient newClient) throws TotalAccountBalanceException {
        log.info("Enter the amount of money to transfer to the other client of the bank");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Transfer money in the amount of {} to the {}", amountOfMoneyForOperation, newClient);
        if (amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance -= amountOfMoneyForOperation;
            newClient.setTotalAccountBalance(newClient.getTotalAccountBalance() + amountOfMoneyForOperation);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Transfer money to the other client in the amount of ",
                    amountOfMoneyForOperation);
            clientsOperations.addToTheEndOfTheList(operationsWithMoneyHistory);
            log.info("Successful! Your current balance is {}", totalAccountBalance);
        } else {
            log.info("This operation can not be performed");
        }
    }

    public void transferMoney() {
        log.info("Enter the amount of money to transfer to the client of the other bank");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Transfer money in the amount of {} with the commission}", amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance();
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()
                && amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance = totalAccountBalance - amountOfMoneyForOperation * (1 + commissionFee);
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(amountOfMoneyForOperation
                    * (1 - commissionFee));
            log.info("Your current balance is {}", totalAccountBalance);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Transfer money to a non bank client in the amount of ",
                    amountOfMoneyForOperation);
            clientsOperations.addToTheEndOfTheList(operationsWithMoneyHistory);
        } else {
            if (amountOfMoneyForOperation > totalAccountBalance) {
                log.info("This operation can not be performed - not enough money in your account");
            } else {
                log.info("This operation can not be performed");
            }
        }
    }

    public void toTopUpBalanceThroughATM(ATM atm) throws TotalAccountBalanceException {
        log.info("Enter the amount of money to top up through ATM");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Top up the balance of the client in the amount of {} through the ATM - {}",
                amountOfMoneyForOperation, atm);
        totalAccountBalance += amountOfMoneyForOperation;
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        atm.setCurrentBalance(atm.getCurrentBalance() + amountOfMoneyForOperation);
        getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() + amountOfMoneyForOperation);
        financialFlows += amountOfMoneyForOperation;
        OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Top up money through the ATM in the amount of ",
                amountOfMoneyForOperation);
        clientsOperations.addToTheEndOfTheList(operationsWithMoneyHistory);
        log.info("Your current balance is {}", totalAccountBalance);
    }

    @Override
    public void resetToDefaultValues() {
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(totalAccountBalance / 2);
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(totalAccountBalance / 2);
        this.name = "Unknown";
        this.accountIdNumber = 0;
        this.totalAccountBalance = 0;
        this.creditDelays = 0;
        this.amountOfMonthlyIncome = 0;
        log.info("All data of the client was reset to default values");
    }

    public void exchangeMoney() {
        log.info("Enter the amount of rubles you want to exchange");
        double amountOfMoney = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Enter the name of the currency you want to get");
        String currency = CreationObjectsFromConsole.scanner.next();
        String wantedCurrency = currency.toLowerCase();
        log.info("Try to exchange {} rubles to the {}", amountOfMoney, wantedCurrency);
        double foreignCurrency;
        if (amountOfMoney <= getTotalAccountBalance()) {
            switch (wantedCurrency) {
                case "euro" -> {
                    foreignCurrency = amountOfMoney / EURO_CURRENCY;
                    log.info("You will get {} euro", foreignCurrency);
                    totalAccountBalance -= amountOfMoney;
                    getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
                case "dollar" -> {
                    foreignCurrency = amountOfMoney / DOLLAR_CURRENCY;
                    log.info("You will get {} dollars", foreignCurrency);
                    totalAccountBalance -= amountOfMoney;
                    getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
            }
        } else {
            log.info("You have no so much money in your account");
        }
    }

    public void showOperationsHistory() {
        log.info(clientsOperations);
    }

    public void showCreditRequestsHistory() {
        log.info(creditRequestsHistories);
    }
}
