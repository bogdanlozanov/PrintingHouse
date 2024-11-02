package main.house;

import main.edition.Edition;
import main.employee.Employee;
import main.machine.PrintingMachine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {
    private List<Employee> employees = new ArrayList<>();
    private List<PrintingMachine> machines = new ArrayList<>();
    private List<Edition> editions = new ArrayList<>();
    private double totalRevenue = 0;
    private double totalExpenses = 0;
    private double revenueThreshold;
    private double discountPercentage;

    public PrintingHouse(double revenueThreshold, double discountPercentage) {
        this.revenueThreshold = revenueThreshold;
        this.discountPercentage = discountPercentage;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        totalExpenses += employee.calculateSalary();
    }

    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
    }

    public void calculateRevenue() {
        for (Edition edition : editions) {
            double printCost = edition.getPrintCost(main.paper.PaperType.REGULAR);
            if (edition.getNumberOfCopies() > 1000) {  // Example discount threshold
                printCost -= printCost * discountPercentage / 100;
            }
            totalRevenue += printCost;
        }
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void generateReport(String filename) {
        double profit = totalRevenue - totalExpenses;
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Total Revenue: " + totalRevenue);
            writer.println("Total Expenses: " + totalExpenses);
            writer.println("Profit: " + profit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
