package ClientsOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ClientsPropertyAndHistory.CreditCard;
import ClientsPropertyAndHistory.CreditRequestsHistory;
import ClientsPropertyAndHistory.OperationsWithMoneyHistory;
import ConsoleScanner.CreationObjectsFromConsole;
import Exceptions.AccountIdNumberException;
import Exceptions.AmountOfMonthlyIncomeException;
import Exceptions.CreditDelaysException;
import Exceptions.TotalAccountBalanceException;
import Interfaces.ICurrency;
import Interfaces.Resettable;
import Interfaces.Showing;
import LoggerInstance.Loggers;
import StructureOfTheBank.ATM;
import StructureOfTheBank.ManagementDepartment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class BaseClient implements Showing, Resettable, ICurrency {

    protected String name;
    protected int accountIdNumber;
    protected double totalAccountBalance;
    protected final static double commissionFee;
    private int creditDelays;
    private double amountOfMonthlyIncome;
    private static int amountOfClients;
    private static double financialFlows;

    public List<OperationsWithMoneyHistory> clientsOperations = new ArrayList<>();
    public List<CreditRequestsHistory> creditRequestsHistories = new LinkedList<>();


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
        creditCard = new CreditCard(getAccountIdNumber(),getTotalAccountBalance());
        ManagementDepartment.cardClientsIndividuals.put(this.creditCard,this);
        Loggers.LOGGER.info("Client with default values was created");
        Loggers.LOGGER.info("Total amount of clients was increased by one");
        Loggers.LOGGER.info("Client got a new credit card");
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

    public void setName(String name) {
        this.name = name;
        Loggers.LOGGER.info("Name got a value of {}", name);
    }


    public int getAccountIdNumber() {
        return accountIdNumber;
    }

    public void setAccountIdNumber(int accountIdNumber) throws AccountIdNumberException {
        if (accountIdNumber <= 0) {
            throw new AccountIdNumberException("Your account ID number is wrong");
        } else {
            this.accountIdNumber = accountIdNumber;
            Loggers.LOGGER.info("Account ID number got a value of {}", accountIdNumber);
        }
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    public double getTotalAccountBalance() {
        return totalAccountBalance;
    }

    public void setTotalAccountBalance(double totalAccountBalance) throws TotalAccountBalanceException {
        if (totalAccountBalance < 0) {
            throw new TotalAccountBalanceException("Something wrong with the amount of balance");
        } else {
            this.totalAccountBalance = totalAccountBalance;
            Loggers.LOGGER.info("Account balance got a value of {}", totalAccountBalance);
        }
    }

    public int getCreditDelays() {
        return creditDelays;
    }

    public void setCreditDelays(int creditDelays) throws CreditDelaysException {
        if (creditDelays < 0) {
            throw new CreditDelaysException("The number of credit delays is wrong");
        } else {
            this.creditDelays = creditDelays;
            Loggers.LOGGER.info("Number of credit delays got a value of {}", creditDelays);
        }
    }

    public double getAmountOfMonthlyIncome() {
        return amountOfMonthlyIncome;
    }

    public void setAmountOfMonthlyIncome(double amountOfMonthlyIncome) throws AmountOfMonthlyIncomeException {
        if (amountOfMonthlyIncome < 0) {
            throw new AmountOfMonthlyIncomeException("The amount of monthly income is wrong");
        } else {
            this.amountOfMonthlyIncome = amountOfMonthlyIncome;
            Loggers.LOGGER.info("Amount of monthly income got a value of {}", amountOfMonthlyIncome);
        }
    }

    public static int getAmountOfClients() {
        return amountOfClients;
    }

    public static double getFinancialFlows() {
        return financialFlows;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCardData(int idNumber, double creditCardBalance) {
        creditCard.setIdNumber(idNumber);
        creditCard.setCreditCardBalance(creditCardBalance);
    }

    public List<CreditRequestsHistory> getCreditRequestsHistories() {
        return creditRequestsHistories;
    }

    public void setCreditRequestsHistories(List<CreditRequestsHistory> creditRequestsHistories) {
        this.creditRequestsHistories = creditRequestsHistories;
    }

    public abstract void toTopUpBalance() throws TotalAccountBalanceException;

    public abstract void toAskForACredit();

    @Override
    public void showBalance() {
        Loggers.LOGGER.info("Total balance of the client is {}", this.totalAccountBalance);
    }

    @Override
    public void showFullInformation() {
        Loggers.LOGGER.info("Full information about the client is the following {}", this);
    }

    public void withdrawCash() {
        Loggers.LOGGER.info("Confirm the amount of money to withdraw from the balance");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Withdraw cash from bank account in the amount of {}", amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance();
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentCashBalance()
                && amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance -= amountOfMoneyForOperation;
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoneyForOperation);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()-amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Withdraw cash from department in the amount of",
                    amountOfMoneyForOperation);
            clientsOperations.add(operationsWithMoneyHistory);
            Loggers.LOGGER.info("Your current balance is {}", totalAccountBalance);
        } else {
            if (amountOfMoneyForOperation > totalAccountBalance) {
                Loggers.LOGGER.info("This operation can not be performed - not enough money in your account");
            } else {
                Loggers.LOGGER.info("This operation can not be performed");
            }
        }
    }

    public void withdrawCash(ATM atm) throws TotalAccountBalanceException {
        Loggers.LOGGER.info("Enter the amount of money to withdraw from the balance in the ATM");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Try to withdraw cash from the ATM");
        if (atm.checkTheAbilityToWithdrawMoney(amountOfMoneyForOperation)) {
            withdrawCash();
            atm.setCurrentBalance(atm.getCurrentBalance() - amountOfMoneyForOperation);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()-amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Withdraw cash from ATM in the amount of",
                    amountOfMoneyForOperation);
            clientsOperations.add(operationsWithMoneyHistory);
        } else {
            Loggers.LOGGER.info("This operation can not be performed, please wait for the collection service");
            atm.callTheCollectionService(amountOfMoneyForOperation);
        }
    }

    public void transferMoney(BaseClient newClient) throws TotalAccountBalanceException {
        Loggers.LOGGER.info("Enter the amount of money to transfer to the other client of the bank");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Transfer money in the amount of {} to the {}", amountOfMoneyForOperation, newClient);
        if (amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance -= amountOfMoneyForOperation;
            newClient.setTotalAccountBalance(newClient.getTotalAccountBalance() + amountOfMoneyForOperation);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()-amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Transfer money to the other client in the amount of ",
                    amountOfMoneyForOperation);
            clientsOperations.add(operationsWithMoneyHistory);
            Loggers.LOGGER.info("Successful! Your current balance is {}", totalAccountBalance);
        } else {
            Loggers.LOGGER.info("This operation can not be performed");
        }
    }

    public void transferMoney() {
        Loggers.LOGGER.info("Enter the amount of money to transfer to the client of the other bank");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Transfer money in the amount of {} with the commission}", amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance();
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()
                && amountOfMoneyForOperation <= totalAccountBalance) {
            totalAccountBalance = totalAccountBalance - amountOfMoneyForOperation * (1 + commissionFee);
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(amountOfMoneyForOperation
                    * (1 - commissionFee));
            Loggers.LOGGER.info("Your current balance is {}", totalAccountBalance);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()-amountOfMoneyForOperation);
            financialFlows += amountOfMoneyForOperation;
            OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Transfer money to a non bank client in the amount of ",
                    amountOfMoneyForOperation);
            clientsOperations.add(operationsWithMoneyHistory);
        } else {
            if (amountOfMoneyForOperation > totalAccountBalance) {
                Loggers.LOGGER.info("This operation can not be performed - not enough money in your account");
            } else {
                Loggers.LOGGER.info("This operation can not be performed");
            }
        }
    }

    public void toTopUpBalanceThroughATM(ATM atm) throws TotalAccountBalanceException {
        Loggers.LOGGER.info("Enter the amount of money to top up through ATM");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Top up the balance of the client in the amount of {} through the ATM - {}",
                amountOfMoneyForOperation, atm);
        totalAccountBalance += amountOfMoneyForOperation;
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        atm.setCurrentBalance(atm.getCurrentBalance() + amountOfMoneyForOperation);
        getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()+amountOfMoneyForOperation);
        financialFlows += amountOfMoneyForOperation;
        OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory("Top up money through the ATM in the amount of ",
                amountOfMoneyForOperation);
        clientsOperations.add(operationsWithMoneyHistory);
        Loggers.LOGGER.info("Your current balance is {}", totalAccountBalance);
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
        Loggers.LOGGER.info("All data of the client was reset to default values");
    }

    public void exchangeMoney() {
        Loggers.LOGGER.info("Enter the amount of rubles you want to exchange");
        double amountOfMoney = CreationObjectsFromConsole.scanner.nextDouble();
        Loggers.LOGGER.info("Enter the name of the currency you want to get");
        String currency = CreationObjectsFromConsole.scanner.next();
        String wantedCurrency = currency.toLowerCase();
        Loggers.LOGGER.info("Try to exchange {} rubles to the {}", amountOfMoney, wantedCurrency);
        double foreignCurrency;
        if (amountOfMoney <= getTotalAccountBalance()) {
            switch (wantedCurrency) {
                case "euro" -> {
                    foreignCurrency = amountOfMoney / EURO_CURRENCY;
                    Loggers.LOGGER.info("You will get {} euro", foreignCurrency);
                    totalAccountBalance -= amountOfMoney;
                    getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()-amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
                case "dollar" -> {
                    foreignCurrency = amountOfMoney / DOLLAR_CURRENCY;
                    Loggers.LOGGER.info("You will get {} dollars", foreignCurrency);
                    totalAccountBalance -= amountOfMoney;
                    getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance()-amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
            }
        } else {
            Loggers.LOGGER.info("You have no so much money in your account");
        }
    }

    public void showOperationsHistory(){
        Loggers.LOGGER.info(clientsOperations);
    }

    public void showCreditRequestsHistory(){
        Loggers.LOGGER.info(creditRequestsHistories);
    }
}
