package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.BaseClient;
import Interfaces.Countable;
import Interfaces.ICurrency;
import Interfaces.IStartBalance;
import Interfaces.Showing;
import LoggerInstance.Loggers;

public class ManagementDepartment implements IStartBalance, Countable, ICurrency, Showing {

    private static boolean riskOfBankruptcyOfTheBank;

    public boolean isRiskOfBankruptcy() {
        return riskOfBankruptcyOfTheBank;
    }

    public void setRiskOfBankruptcy(boolean riskOfBankruptcy) {
        riskOfBankruptcyOfTheBank = riskOfBankruptcy;
    }

    public static boolean checkTheRiskOfBankruptcyOfTheBank() {
        Loggers.LOGGER.info("The management department is checking the risk of bankruptcy of the bank");
        CurrentAccountOfTheBank.getInstance();
        if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()
                <= (START_CASH_BALANCE + START_NON_CASH_BALANCE) / 2) {
            Loggers.LOGGER.info("There is a risk of bankruptcy - the current bank funds are less than they should be!");
            riskOfBankruptcyOfTheBank = true;
            return true;
        } else {
            Loggers.LOGGER.info("There is no risk of bankruptcy!");
            riskOfBankruptcyOfTheBank = false;
            return false;
        }
    }

    @Override
    public void amountOfCreatedEntities() {
        Loggers.LOGGER.info("The total amount of Clients is {}", BaseClient.getAmountOfClients());
    }

    @Override
    public void amountOfFinancialFlows() {
        System.out.println(BaseClient.getFinancialFlows());
    }

    public static void showCurrentBalanceInDifferentCurrency() {
        System.out.println("The current balance in dollars is "
                + CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / DOLLAR_CURRENCY);
        System.out.println("The current balance in euro is "
                + CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / EURO_CURRENCY);
    }

    @Override
    public void showBalance() {
        System.out.println(CurrentAccountOfTheBank.getInstance().getCurrentBankBalance());
    }

    @Override
    public void showFullInformation() {
        Loggers.LOGGER.info("Total amount of financial flows for this ATM is {}", BaseClient.getFinancialFlows());
    }
}
