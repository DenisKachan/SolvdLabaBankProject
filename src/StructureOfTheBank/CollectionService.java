package StructureOfTheBank;

import BankAccount.CurrentAccountOfTheBank;
import Exceptions.LackOfNonCashAfterConvertingException;
import LoggerInstance.Loggers;

import java.util.ArrayList;
import java.util.List;

public class CollectionService {

    private double amountOfTheTransportedCash;

    public CollectionService(double amountOfTheTransportedCash) {
        this.amountOfTheTransportedCash = amountOfTheTransportedCash;
        ManagementDepartment.collectionServiceCalls.add(amountOfTheTransportedCash);
        Loggers.LOGGER.info("Collection service is crated with the amount of transported cash of {}",
                amountOfTheTransportedCash);
    }

    public double getAmountOfTheTransportedCash() {
        return amountOfTheTransportedCash;
    }

    public void setAmountOfTheTransportedCash(double amountOfTheTransportedCash) {
        this.amountOfTheTransportedCash = amountOfTheTransportedCash;
    }

    public double convertMoney(double neededAmountOfMoney) {
        Loggers.LOGGER.info("The collection service is trying to convert non cash into cash");
        if (neededAmountOfMoney <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()) {
            CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(neededAmountOfMoney);
            if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() == 0) {
                try {
                    throw new LackOfNonCashAfterConvertingException("The amount of non cash is critically low");
                } catch (LackOfNonCashAfterConvertingException e) {
                    Loggers.LOGGER.error(e);
                }
            }
            CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(neededAmountOfMoney);
            amountOfTheTransportedCash = neededAmountOfMoney;
            Loggers.LOGGER.info("The balance of the ATM will be increased soon. Try again!");
        } else {
            Loggers.LOGGER.info("The amount of money is to large to withdraw");
        }
        return 0;
    }
}
