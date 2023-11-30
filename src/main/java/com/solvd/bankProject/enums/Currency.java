package com.solvd.bankProject.enums;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Getter
@Log4j2
public enum Currency {

    DOLLAR("Dollar", 3.2),
    EURO("Euro", 3.5),
    YEN("Yen", 2.1),
    POUND("Pound", 4.2);

    private final String nameOfTheCurrency;
    private double currentValue;

    Currency(String nameOfTheCurrency, double currentValue) {
        this.nameOfTheCurrency = nameOfTheCurrency;
        this.currentValue = currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public void showTheResultOfPossibleConvert(double amountOfMoney) {
        double result = amountOfMoney / currentValue;
        log.info("You may get {} of {}", result, nameOfTheCurrency);
    }
}
