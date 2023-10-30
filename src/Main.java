import BankAccount.CurrentAccount;
import StructureOfTheBank.*;
import ClientsOfTheBank.ClientsIndividuals;
import ClientsOfTheBank.Companies;
import EmployeesOfTheBank.*;

public class Main {

    public static void main(String[] args) {
        CurrentAccount currentAccount = new CurrentAccount(9999.99);
        Accounting accountingOffice = new Accounting("Minsk", 30, 30);
        ATM atmMachine = new ATM("Gomel", 50);
        CreditDepartment creditDepartmentOffice = new CreditDepartment("Minsk", 50,
                55);
        HRDepartment hrDepartmentOffice = new HRDepartment("Minsk", 10, 11);
        ManagementDepartment managementDepartmentOffice = new ManagementDepartment("Minsk", 20,
                20);
        ClientsIndividuals firstClient = new ClientsIndividuals("Jack", "Grealish",
                19790, 25.5, 5, 7);
        Companies clientCompany = new Companies("Solvd", 656546, 100,
                30, 30);
        Accountant accountant = new Accountant("Jane", "Brown", 100, 15);
        Cashier cashier = new Cashier("Sam", "Smith", 90, 13);
        CreditManager creditManager = new CreditManager("Pavel", "Smith", 120, 115);
        HRManager hrManager = new HRManager("Erling", "Wood", 100, 14);
        Management manager = new Management("Travis", "Perkins", 140, 30);
    }
}
