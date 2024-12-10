package main;

import exceptions.MachineOverloadException;
import exceptions.UnsupportedPrintTypeException;
import main.edition.Edition;
import main.edition.EditionType;
import main.house.PrintingHouse;
import main.machine.PrintingMachine;
import main.paper.PaperSize;
import main.paper.PaperType;
import main.employee.Employee;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        // Initialize the printing house with a reasonable revenue threshold and discount percentage
        PrintingHouse printingHouse = new PrintingHouse(10000, 5); // Lowered threshold, reduced discount to 5%

        // Add employees
        Employee operator = new Employee("John", false, 0); // Operator without commission
        Employee manager = new Employee("Sarah", true, 0.05); // Manager with 5% commission
        printingHouse.addEmployee(operator);
        printingHouse.addEmployee(manager);

        // Add printing machines
        PrintingMachine colorMachine = new PrintingMachine(10000, true); // Color printing machine
        PrintingMachine bwMachine = new PrintingMachine(5000, false);   // Black-and-white printing machine
        printingHouse.addMachine(colorMachine);
        printingHouse.addMachine(bwMachine);

        // Add editions with adjusted prices for better profit margins
        Edition magazine = new Edition("Tech Monthly", 1200, PaperSize.A4, PaperType.GLOSSY, EditionType.MAGAZINE, 7.00); // Increased price per copy: $7.00
        Edition newspaper = new Edition("Daily News", 800, PaperSize.A3, PaperType.NEWSPAPER, EditionType.NEWSPAPER, 3.00); // Increased price per copy: $3.00

        printingHouse.addEdition(magazine);
        printingHouse.addEdition(newspaper);

        // Calculate total revenue and expenses
        double totalRevenue = printingHouse.calculateTotalRevenue();
        double totalExpenses = printingHouse.calculateTotalExpenses(totalRevenue);
        double profit = totalRevenue - totalExpenses;

        // Format outputs to two decimal places for readability
        DecimalFormat df = new DecimalFormat("#.00");

        // Print results
        System.out.println("Total Revenue: " + df.format(totalRevenue));
        System.out.println("Total Expenses: " + df.format(totalExpenses));
        System.out.println("Profit: " + df.format(profit));

        // Handle exceptions for edge cases
        try {
            // Attempt to print a color edition on a black-and-white machine
            bwMachine.printEdition(magazine, true);
        } catch (UnsupportedPrintTypeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            // Exceed machine's paper capacity to trigger an overload exception
            colorMachine.loadPaper(15000);
        } catch (MachineOverloadException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Generate a report file
        String reportFileName = "report.txt";
        printingHouse.generateReport(reportFileName);
        System.out.println("Report generated: " + reportFileName);
    }
}
