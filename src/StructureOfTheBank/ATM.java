package StructureOfTheBank;

public class ATM {

    private String address;
    private int currentBalance;
    private int incomingCash;
    private int outgoingCash;

    public ATM(String address, int currentBalance) {
        this.address = address;
        this.currentBalance = currentBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getIncomingCash() {
        return incomingCash;
    }

    public void setIncomingCash(int incomingCash) {
        this.incomingCash = incomingCash;
    }

    public int getOutgoingCash() {
        return outgoingCash;
    }

    public void setOutgoingCash(int outgoingCash) {
        this.outgoingCash = outgoingCash;
    }
}
