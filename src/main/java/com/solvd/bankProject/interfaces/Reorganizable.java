package com.solvd.bankProject.interfaces;

import com.solvd.bankProject.exceptions.*;

public interface Reorganizable<T> {

    T reorganizeIntoAnotherForm() throws AccountIdNumberException, YearOfFoundationException,
            CreditDelaysException, AmountOfMonthlyIncomeException, TotalAccountBalanceException, AgeException;
}
