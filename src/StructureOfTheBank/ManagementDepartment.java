package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.BaseClient;
import Interfaces.Countable;
import Interfaces.ICurrency;
import Interfaces.IStartBalance;
import Interfaces.Showing;

public class ManagementDepartment implements IStartBalance, Countable, ICurrency, Showing {

    private static boolean riskOfBankruptcyOfTheBank;

    public boolean isRiskOfBankruptcy() {
        return riskOfBankruptcyOfTheBank;
    }

    public void setRiskOfBankruptcy(boolean riskOfBankruptcy) {
        riskOfBankruptcyOfTheBank = riskOfBankruptcy;
    }

    public static boolean checkTheRiskOfBankruptcyOfTheBank() {
        CurrentAccountOfTheBank.getInstance();
        if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()
                <= (START_CASH_BALANCE + START_NON_CASH_BALANCE) / 2) {
            System.out.println("There is a risk of bankruptcy - the current bank funds are less than they should be!");
            riskOfBankruptcyOfTheBank = true;
            return true;
        } else {
            System.out.println("There is no risk of bankruptcy!");
            riskOfBankruptcyOfTheBank = false;
            return false;
        }
    }

    @Override
    public void amountOfCreatedEntities() {
        System.out.println(BaseClient.getAmountOfClients());
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
        System.out.println(CurrentAccountOfTheBank.getInstance());
    }
}
