package com.solvd.bankProject.enums;

import com.solvd.bankProject.bankAccount.CurrentAccountOfTheBank;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public enum Commission {

    COMMISSION_FOR_CLIENTS_INDIVIDUALS("Amount of commission for the clients individuals", 0.01),
    COMMISSION_FOR_COMPANIES("Amount of commission for the companies", 0.02);

    private final String typeOfCommission;
    private double amountOfCommission;

    static {
        if (CurrentAccountOfTheBank.getInstance().getCurrentBankBalance()
                <= (StartBalance.START_CASH_BALANCE.getCurrentValue() + StartBalance.START_NON_CASH_BALANCE.getCurrentValue()) / 3) {
            COMMISSION_FOR_COMPANIES.setAmountOfCommission(0.1);
            COMMISSION_FOR_CLIENTS_INDIVIDUALS.setAmountOfCommission(0.2);
        }
    }

    Commission(String typeOfCommission, double amountOfCommission) {
        this.typeOfCommission = typeOfCommission;
        this.amountOfCommission = amountOfCommission;
    }

    public void setAmountOfCommission(double amountOfCommission) {
        this.amountOfCommission = amountOfCommission;
    }

    public void showCurrentCommission() {
        log.info("You current commission is {}", amountOfCommission);
    }
}
