package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import ClientsOfTheBank.BaseClient;
import ClientsPropertyAndHistory.CreditCard;
import ConsoleScanner.CreationObjectsFromConsole;
import Interfaces.Countable;
import Interfaces.ICurrency;
import Interfaces.IStartBalance;
import Interfaces.Showing;
import LoggerInstance.Loggers;

import java.util.*;

public class ManagementDepartment implements IStartBalance, Countable, ICurrency, Showing {

    private static boolean riskOfBankruptcyOfTheBank;

    public boolean isRiskOfBankruptcy() {
        return riskOfBankruptcyOfTheBank;
    }

    public void setRiskOfBankruptcy(boolean riskOfBankruptcy) {
        riskOfBankruptcyOfTheBank = riskOfBankruptcy;
    }

    public static Map<CreditCard, BaseClient> cardClientsIndividuals = new HashMap<>();

    public static List<Double> collectionServiceCalls = new ArrayList<>();


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
        Loggers.LOGGER.info("The current balance in dollars is {}",
                CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / DOLLAR_CURRENCY);
        Loggers.LOGGER.info("The current balance in euro is {}",
                CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / EURO_CURRENCY);
    }

    @Override
    public void showBalance() {
        Loggers.LOGGER.info("Total bank balance is {}",CurrentAccountOfTheBank.getInstance().getCurrentBankBalance());
    }

    @Override
    public void showFullInformation() {
        Loggers.LOGGER.info("Total amount of financial flows of the bank is {}", BaseClient.getFinancialFlows());
    }

    public void findAHolderOfACard(){
        Loggers.LOGGER.info("Enter the Credit card number");
        int cardNumber = CreationObjectsFromConsole.scanner.nextInt();
        for (Map.Entry<CreditCard, BaseClient> entry : cardClientsIndividuals.entrySet()) {
            CreditCard key = entry.getKey();
            BaseClient value = entry.getValue();
            if (key.getCreditCardNumber() == cardNumber){
                Loggers.LOGGER.info("The holder of the card is - {}",value);
                break;
            }
            else {Loggers.LOGGER.info("There is no client with this card number");}
        }
    }

    public void showAverageAmountOfTransportedCash(){
        double a = 0;
        for (Double collectionServiceCall : collectionServiceCalls) {
            a += collectionServiceCall;
        }
        Loggers.LOGGER.info("Average amount of transported cash is - {}",a/collectionServiceCalls.size());
    }
}
