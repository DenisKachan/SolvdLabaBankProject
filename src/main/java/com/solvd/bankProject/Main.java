package com.solvd.bankProject;

import com.solvd.bankProject.ClientsOfTheBank.ClientsIndividuals;
import com.solvd.bankProject.ConsoleScanner.CreationObjectsFromConsole;

public class Main {
    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            ClientsIndividuals clientsIndividuals = creationObjectsFromConsole.createClientIndividualFromConsole();
        }
    }
}
