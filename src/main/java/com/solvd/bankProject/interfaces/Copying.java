package com.solvd.bankProject.interfaces;

import com.solvd.bankProject.exceptions.*;

@FunctionalInterface
public interface Copying<T> {

    T copyValues() throws AgeException, CreditDelaysException, TotalAccountBalanceException,
            AccountIdNumberException, AmountOfMonthlyIncomeException;
}
