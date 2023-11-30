package com.solvd.bankProject.clientsOfTheBank;

import com.solvd.bankProject.bankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.clientsPropertyAndHistory.CreditCard;
import com.solvd.bankProject.clientsPropertyAndHistory.CreditRequestsHistory;
import com.solvd.bankProject.clientsPropertyAndHistory.OperationsWithMoneyHistory;
import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.customLinkedList.LinkedListForEntities;
import com.solvd.bankProject.enums.Commission;
import com.solvd.bankProject.enums.Currency;
import com.solvd.bankProject.exceptions.AccountIdNumberException;
import com.solvd.bankProject.exceptions.AmountOfMonthlyIncomeException;
import com.solvd.bankProject.exceptions.CreditDelaysException;
import com.solvd.bankProject.exceptions.TotalAccountBalanceException;
import com.solvd.bankProject.interfaces.Adding;
import com.solvd.bankProject.interfaces.AmountChangeable;
import com.solvd.bankProject.interfaces.Showing;
import com.solvd.bankProject.structureOfTheBank.ATM;
import com.solvd.bankProject.structureOfTheBank.ManagementDepartment;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Locale;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@Log4j2
public abstract class BaseClient implements Showing {

    @Getter
    protected String name;
    @Getter
    protected int accountIdNumber;
    @Getter
    protected double totalAccountBalance;
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

    Adding<Double> addToListOfOperations = (String nameOfTheOperation, Double amountOfMoney) -> {
        OperationsWithMoneyHistory operationsWithMoneyHistory = new OperationsWithMoneyHistory(nameOfTheOperation,
                amountOfMoney);
        clientsOperations.addToTheEndOfTheList(operationsWithMoneyHistory);
    };

    AmountChangeable<Double> increaseBalanceAfterAction = (Double amountOfMoney) -> {
        totalAccountBalance += amountOfMoney;
        getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() + amountOfMoney);
        return amountOfMoney;
    };

    AmountChangeable<Double> decreaseBalanceAfterAction = (Double amountOfMoney) -> {
        totalAccountBalance -= amountOfMoney;
        getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoney);
        return amountOfMoney;
    };

    Predicate<Double> isEnoughMoneyOnAccount = x -> x <= totalAccountBalance;

    UnaryOperator<Double> increaseFinancialFlows = x -> financialFlows += x;

    BinaryOperator<Double> divide = (x, y) -> x / y;

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
        return Objects.hash(name, accountIdNumber, totalAccountBalance, creditDelays, amountOfMonthlyIncome);
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
                && isEnoughMoneyOnAccount.test(amountOfMoneyForOperation)) {
            decreaseBalanceAfterAction.changeAmount(amountOfMoneyForOperation);
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoneyForOperation);
            increaseFinancialFlows.apply(amountOfMoneyForOperation);
            addToListOfOperations.addInstance("Withdraw cash", amountOfMoneyForOperation);
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
            increaseFinancialFlows.apply(amountOfMoneyForOperation);
        } else {
            log.info("This operation can not be performed, please wait for the collection service");
            atm.callTheCollectionService(amountOfMoneyForOperation);
        }
    }

    public void transferMoney(BaseClient newClient) throws TotalAccountBalanceException {
        log.info("Enter the amount of money to transfer to the other client of the bank");
        double amountOfMoneyForOperation = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Transfer money in the amount of {} to the {}", amountOfMoneyForOperation, newClient);
        if (isEnoughMoneyOnAccount.test(amountOfMoneyForOperation)) {
            decreaseBalanceAfterAction.changeAmount(amountOfMoneyForOperation);
            newClient.setTotalAccountBalance(newClient.getTotalAccountBalance() + amountOfMoneyForOperation);
            increaseFinancialFlows.apply(amountOfMoneyForOperation);
            addToListOfOperations.addInstance("Transfer money to the other client of the bank", amountOfMoneyForOperation);
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
        Commission commissionFee;
        if (this.getClass().equals(ClientsIndividuals.class)) {
            commissionFee = Commission.COMMISSION_FOR_CLIENTS_INDIVIDUALS;
        } else {
            commissionFee = Commission.COMMISSION_FOR_COMPANIES;
        }
        if (amountOfMoneyForOperation <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()
                && isEnoughMoneyOnAccount.test(amountOfMoneyForOperation)) {
            totalAccountBalance = totalAccountBalance - amountOfMoneyForOperation * (1 + commissionFee.getAmountOfCommission());
            CurrentAccountOfTheBank.getInstance();
            CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(amountOfMoneyForOperation
                    * (1 - commissionFee.getAmountOfCommission()));
            log.info("Your current balance is {}", totalAccountBalance);
            getCreditCard().setCreditCardBalance(getCreditCard().getCreditCardBalance() - amountOfMoneyForOperation);
            increaseFinancialFlows.apply(amountOfMoneyForOperation);
            addToListOfOperations.addInstance("Transfer money to the client of the other bank", amountOfMoneyForOperation);
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
        increaseBalanceAfterAction.changeAmount(amountOfMoneyForOperation);
        CurrentAccountOfTheBank.getInstance();
        CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(amountOfMoneyForOperation);
        atm.setCurrentBalance(atm.getCurrentBalance() + amountOfMoneyForOperation);
        increaseFinancialFlows.apply(amountOfMoneyForOperation);
        addToListOfOperations.addInstance("Top up money through the ATM", amountOfMoneyForOperation);
        log.info("Your current balance is {}", totalAccountBalance);
    }

    public void exchangeMoney() {
        log.info("Enter the amount of rubles you want to exchange");
        double amountOfMoney = CreationObjectsFromConsole.scanner.nextDouble();
        log.info("Enter the name of the currency you want to get");
        String currency = CreationObjectsFromConsole.scanner.next();
        Currency wantedCurrency = Currency.valueOf(currency.toUpperCase(Locale.ROOT));
        log.info("Try to exchange {} rubles to the {}", amountOfMoney, currency);
        double foreignCurrency;
        if (isEnoughMoneyOnAccount.test(amountOfMoney)) {
            switch (wantedCurrency) {
                case EURO -> {
                    foreignCurrency = divide.apply(amountOfMoney, Currency.EURO.getCurrentValue());
                    log.info("You will get {} euro", foreignCurrency);
                    decreaseBalanceAfterAction.changeAmount(amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
                case DOLLAR -> {
                    foreignCurrency = divide.apply(amountOfMoney, Currency.DOLLAR.getCurrentValue());
                    log.info("You will get {} dollars", foreignCurrency);
                    decreaseBalanceAfterAction.changeAmount(amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
                case YEN -> {
                    foreignCurrency = divide.apply(amountOfMoney, Currency.YEN.getCurrentValue());
                    log.info("You will get {} yens", foreignCurrency);
                    decreaseBalanceAfterAction.changeAmount(amountOfMoney);
                    CurrentAccountOfTheBank.getInstance().decreaseCurrentCashBalance(amountOfMoney);
                }
                case POUND -> {
                    foreignCurrency = divide.apply(amountOfMoney, Currency.POUND.getCurrentValue());
                    log.info("You will get {} pounds", foreignCurrency);
                    decreaseBalanceAfterAction.changeAmount(amountOfMoney);
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
