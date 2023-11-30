package com.solvd.bankProject.enums;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public enum StartBalance {

    START_CASH_BALANCE("Start cash balance of the bank", 50000),
    START_NON_CASH_BALANCE("Start non cash balance of the bank", 50100);

    private final String typeOfTheBalance;
    private final double currentValue;

    StartBalance(String typeOfTheBalance, double currentValue) {
        this.typeOfTheBalance = typeOfTheBalance;
        this.currentValue = currentValue;
    }

    public void showStartBalance() {
        log.info("The bank was founded with the cash balance in the amount of {} and non cash balance in the amount of {}",
                START_CASH_BALANCE.getCurrentValue(), START_NON_CASH_BALANCE.getCurrentValue());
    }
}
