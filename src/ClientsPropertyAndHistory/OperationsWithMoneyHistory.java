package ClientsPropertyAndHistory;

import java.util.Objects;

public class OperationsWithMoneyHistory {

    private String nameOfTheOperation;

    private double amountOfMoney;

    public OperationsWithMoneyHistory(String nameOfTheOperation, double amountOfMoney) {
        this.nameOfTheOperation = nameOfTheOperation;
        this.amountOfMoney = amountOfMoney;
    }

    public String getNameOfTheOperation() {
        return nameOfTheOperation;
    }

    public void setNameOfTheOperation(String nameOfTheOperation) {
        this.nameOfTheOperation = nameOfTheOperation;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
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
