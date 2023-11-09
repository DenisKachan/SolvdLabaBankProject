import ClientsOfTheBank.ClientsIndividuals;
import ConsoleScanner.CreationObjectsFromConsole;
import Exceptions.*;

public class Main {
    public static void main(String[] args) throws NameException, TotalAccountBalanceException,
            AmountOfMonthlyIncomeException, AgeException, CreditDelaysException, AccountIdNumberException {
        try (CreationObjectsFromConsole.scanner) {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            ClientsIndividuals clientsIndividuals = creationObjectsFromConsole.createClientIndividualFromConsole();
            clientsIndividuals.withdrawCash();
            clientsIndividuals.toTopUpBalance();
            clientsIndividuals.transferMoney();
        }
    }
}
