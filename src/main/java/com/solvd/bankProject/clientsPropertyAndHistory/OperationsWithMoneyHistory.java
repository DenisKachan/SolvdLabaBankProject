package com.solvd.bankProject.clientsPropertyAndHistory;

import lombok.Getter;

import java.util.Objects;

@Getter
public class OperationsWithMoneyHistory {

    private String nameOfTheOperation;

    private volatile double amountOfMoney;

    public OperationsWithMoneyHistory(String nameOfTheOperation, double amountOfMoney) {
        this.nameOfTheOperation = nameOfTheOperation;
        this.amountOfMoney = amountOfMoney;
    }

    public void setNameOfTheOperation(String nameOfTheOperation) {
        this.nameOfTheOperation = nameOfTheOperation;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperationsWithMoneyHistory that)) return false;
        return Double.compare(getAmountOfMoney(), that.getAmountOfMoney()) == 0
                && Objects.equals(getNameOfTheOperation(), that.getNameOfTheOperation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameOfTheOperation(), getAmountOfMoney());
    }

    @Override
    public String toString() {
        return "Operations{" +
                "nameOfTheOperation='" + nameOfTheOperation + '\'' +
                ", amountOfMoney=" + amountOfMoney +
                '}';
    }
}
