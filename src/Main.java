import ClientsOfTheBank.BaseClient;
import ClientsOfTheBank.ClientsIndividuals;
import ConsoleScanner.CreationObjectsFromConsole;
import Exceptions.TotalAccountBalanceException;
import StructureOfTheBank.ATM;
import StructureOfTheBank.CollectionService;
import StructureOfTheBank.ManagementDepartment;

public class Main {

    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            ClientsIndividuals clientsIndividuals1 = creationObjectsFromConsole.createClientIndividualFromConsole();
            ClientsIndividuals clientsIndividuals = new ClientsIndividuals();
            ATM atm = new ATM();

        }
    }
}
