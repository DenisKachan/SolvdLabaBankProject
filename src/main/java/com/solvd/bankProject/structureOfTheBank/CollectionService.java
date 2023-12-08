package com.solvd.bankProject.structureOfTheBank;

import com.solvd.bankProject.bankAccount.CurrentAccountOfTheBank;
import com.solvd.bankProject.exceptions.LackOfNonCashAfterConvertingException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public class CollectionService {

    private volatile double amountOfTheTransportedCash;

    public CollectionService(double amountOfTheTransportedCash) {
        this.amountOfTheTransportedCash = amountOfTheTransportedCash;
        ManagementDepartment.collectionServiceCalls.add(amountOfTheTransportedCash);
        log.info("Collection service is crated with the amount of transported cash of {}",
                amountOfTheTransportedCash);
    }

    public void setAmountOfTheTransportedCash(double amountOfTheTransportedCash) {
        this.amountOfTheTransportedCash = amountOfTheTransportedCash;
    }

    public double convertMoney(double neededAmountOfMoney) {
        synchronized (this) {
            log.info("The collection service is trying to convert non cash into cash");
            if (neededAmountOfMoney <= CurrentAccountOfTheBank.getInstance().getCurrentNonCashBalance()) {
                CurrentAccountOfTheBank.getInstance().decreaseCurrentNonCashBalance(neededAmountOfMoney);
                if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance() == 0) {
                    try {
                        throw new LackOfNonCashAfterConvertingException("The amount of non cash is critically low");
                    } catch (LackOfNonCashAfterConvertingException e) {
                        log.error(e.getMessage());
                    }
                }
                CurrentAccountOfTheBank.getInstance().increaseCurrentCashBalance(neededAmountOfMoney);
                amountOfTheTransportedCash = neededAmountOfMoney;
                log.info("The balance of the ATM will be increased soon. Try again!");
            } else {
                log.info("The amount of money is to large to withdraw");
            }
            return 0;
        }
    }
}
