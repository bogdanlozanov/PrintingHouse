package main;

import exceptions.MachineOverloadException;
import exceptions.UnsupportedPrintTypeException;
import main.edition.Book;
import main.edition.Edition;
import main.employee.Manager;
import main.employee.Operator;
import main.house.PrintingHouse;
import main.machine.PrintingMachine;
import main.paper.PaperSize;

public class Main {
    public static void main(String[] args) {
        PrintingHouse printingHouse = new PrintingHouse(50000, 10);

        // Employees
        Operator operator = new Operator("John");
        Manager manager = new Manager("Sarah", 0.1);

        printingHouse.addEmployee(operator);
        printingHouse.addEmployee(manager);

        // Machines
        PrintingMachine colorMachine = new PrintingMachine(10000, true, 100);
        PrintingMachine bwMachine = new PrintingMachine(5000, false, 100); // Black-and-white machine
        printingHouse.addMachine(colorMachine);
        printingHouse.addMachine(bwMachine);

        // Editions
        Edition book = new Book("My Book", 1200, PaperSize.A4);
        printingHouse.addEdition(book);

        // Calculate and Report
        printingHouse.calculateRevenue();
        System.out.println("Total Revenue: " + printingHouse.getTotalRevenue());
        System.out.println("Total Expenses: " + printingHouse.getTotalExpenses());

        // Exception Scenarios
        try {
            bwMachine.printEdition(book, true); // Attempting to print in color on a black-and-white machine
        } catch (UnsupportedPrintTypeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            colorMachine.loadPaper(15000); // Exceeding max capacity to trigger MachineOverloadException
        } catch (MachineOverloadException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Generate Report
        printingHouse.generateReport("report.txt");
    }
}
