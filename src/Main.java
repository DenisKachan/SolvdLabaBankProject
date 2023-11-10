import ClientsOfTheBank.ClientsIndividuals;
import ConsoleScanner.CreationObjectsFromConsole;
import StructureOfTheBank.ManagementDepartment;

public class Main {

    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            ClientsIndividuals clientsIndividuals = creationObjectsFromConsole.createClientIndividualFromConsole();
            ManagementDepartment managementDepartment = new ManagementDepartment();
            managementDepartment.showBalance();
            managementDepartment.amountOfCreatedEntities();
            ClientsIndividuals clientsIndividuals1 = new ClientsIndividuals();
            managementDepartment.amountOfCreatedEntities();
            managementDepartment.showBalance();
            clientsIndividuals.exchangeMoney();
        }
    }
}
