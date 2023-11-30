package com.solvd.bankProject.structureOfTheBank;

import com.solvd.bankProject.bankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.clientsOfTheBank.BaseClient;
import com.solvd.bankProject.clientsOfTheBank.ClientsIndividuals;
import com.solvd.bankProject.clientsPropertyAndHistory.CreditCard;
import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;
import com.solvd.bankProject.customLinkedList.LinkedListForEntities;
import com.solvd.bankProject.enums.Currency;
import com.solvd.bankProject.enums.StartBalance;
import com.solvd.bankProject.interfaces.Countable;
import com.solvd.bankProject.interfaces.Showing;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2
public class ManagementDepartment implements Showing, Countable {

    private static boolean riskOfBankruptcyOfTheBank;

    public boolean isRiskOfBankruptcy() {
        return riskOfBankruptcyOfTheBank;
    }

    public void setRiskOfBankruptcy(boolean riskOfBankruptcy) {
        riskOfBankruptcyOfTheBank = riskOfBankruptcy;
    }

    public static Map<CreditCard, BaseClient> cardClientsIndividuals = new HashMap<>();

    public static List<Double> collectionServiceCalls = new ArrayList<>();

    public static LinkedListForEntities<ClientsIndividuals> clientsIndividualsLinkedListForEntities = new LinkedListForEntities<>();


    public static boolean checkTheRiskOfBankruptcyOfTheBank() {
        log.info("The management department is checking the risk of bankruptcy of the bank");
        CurrentAccountOfTheBank.getInstance();
        if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()
                <= (StartBalance.START_CASH_BALANCE.getCurrentValue()
                + StartBalance.START_NON_CASH_BALANCE.getCurrentValue()) / 2) {
            log.info("There is a risk of bankruptcy - the current bank funds are less than they should be!");
            riskOfBankruptcyOfTheBank = true;
            return true;
        } else {
            log.info("There is no risk of bankruptcy!");
            riskOfBankruptcyOfTheBank = false;
            return false;
        }
    }

    @Override
    public void amountOfCreatedEntities() {
        log.info("The total amount of Clients is {}", BaseClient.getAmountOfClients());
    }

    @Override
    public void amountOfFinancialFlows() {
        log.info(BaseClient.getFinancialFlows());
    }

    public static void showCurrentBalanceInDifferentCurrency() {
        log.info("The current balance in dollars is {}",
                CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / Currency.DOLLAR.getCurrentValue());
        log.info("The current balance in euros is {}",
                CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / Currency.EURO.getCurrentValue());
        log.info("The current balance in yens is {}",
                CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / Currency.YEN.getCurrentValue());
        log.info("The current balance in pounds is {}",
                CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() / Currency.POUND.getCurrentValue());
    }

    @Override
    public void showBalance() {
        log.info("Total bank balance is {}", CurrentAccountOfTheBank.getInstance().getCurrentBankBalance());
    }

    @Override
    public void showFullInformation() {
        log.info("Total amount of financial flows of the bank is {}", BaseClient.getFinancialFlows());
    }

    public void findAHolderOfACard() {
        log.info("Enter the Credit card number");
        int cardNumber = CreationObjectsFromConsole.scanner.nextInt();
        for (Map.Entry<CreditCard, BaseClient> entry : cardClientsIndividuals.entrySet()) {
            CreditCard key = entry.getKey();
            BaseClient value = entry.getValue();
            if (key.getCreditCardNumber() == cardNumber) {
                log.info("The holder of the card is - {}", value);
                break;
            } else {
                log.info("There is no client with this card number");
            }
        }
    }

    public void showAverageAmountOfTransportedCash() {
        double a = 0;
        for (Double collectionServiceCall : collectionServiceCalls) {
            a += collectionServiceCall;
        }
        log.info("Average amount of transported cash is - {}", a / collectionServiceCalls.size());
    }
}
