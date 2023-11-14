package ClientsPropertyAndHistory;

import java.util.Objects;
import java.util.Random;

public class CreditRequestsHistory {

    private String status;

    private int serialNumber;

    public CreditRequestsHistory() {
        Random randomCreditOperationNumber = new Random();
        serialNumber = randomCreditOperationNumber.nextInt(10000);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditRequestsHistory that)) return false;
        return getSerialNumber() == that.getSerialNumber() && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getSerialNumber());
    }

    @Override
    public String toString() {
        return "CreditRequestsHistory{" +
                "Status='" + status + '\'' +
                ", Serial Number=" + serialNumber +
                '}';
    }
}
