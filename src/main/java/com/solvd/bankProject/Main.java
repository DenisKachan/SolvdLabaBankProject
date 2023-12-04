package com.solvd.bankProject;

import com.solvd.bankProject.consoleScanner.CreationObjectsFromConsole;

public class Main {
    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            Reflection reflection = new Reflection();
            reflection.invokeCertainMethod();
        }
    }
}
