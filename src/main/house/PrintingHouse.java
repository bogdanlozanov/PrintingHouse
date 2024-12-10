package main.house;

import main.edition.Edition;
import main.employee.Employee;
import main.machine.PrintingMachine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class PrintingHouse {
    private Set<Employee> employees;
    private Set<PrintingMachine> machines;
    private Set<Edition> editions;

    private double revenueThreshold;
    private double discountPercentage;

    public PrintingHouse(double revenueThreshold, double discountPercentage) {
        this.employees = new HashSet<>();
        this.machines = new HashSet<>();
        this.editions = new HashSet<>();
        this.revenueThreshold = revenueThreshold;
        this.discountPercentage = discountPercentage;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
    }

    /**
     * Calculates the total revenue from all editions.
     *
     * @return Total revenue charged to customers.
     */
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Edition edition : editions) {
            // Assume there's a price per copy defined in the Edition class
            double pricePerCopy = edition.getPricePerCopy();
            double totalEditionRevenue = pricePerCopy * edition.getNumberOfCopies();

            // Apply bulk discount if applicable
            if (edition.getNumberOfCopies() > 1000) {
                totalEditionRevenue -= totalEditionRevenue * discountPercentage / 100;
            }

            totalRevenue += totalEditionRevenue;
        }
        return totalRevenue;
    }

    /**
     * Calculates the total expenses for employees and materials (e.g., paper costs).
     *
     * @param totalRevenue Current revenue to determine manager bonuses.
     * @return Total expenses.
     */
    public double calculateTotalExpenses(double totalRevenue) {
        // Employee salaries
        double employeeSalaries = employees.stream()
                .mapToDouble(e -> e.calculateSalary(totalRevenue, revenueThreshold))
                .sum();

        // Paper costs for all editions
        double paperCosts = editions.stream()
                .mapToDouble(e -> e.getNumberOfCopies() * e.getPaperType().getBasePrice() * e.getPageSize().getMultiplier())
                .sum();

        // Total expenses = salaries + paper costs
        return employeeSalaries + paperCosts;
    }

    /**
     * Generates a report and writes it to a file.
     *
     * @param filename The name of the file.
     */
    public void generateReport(String filename) {
        double totalRevenue = calculateTotalRevenue();
        double totalExpenses = calculateTotalExpenses(totalRevenue);
        double profit = totalRevenue - totalExpenses;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.printf("Total Revenue: %.2f%n", totalRevenue);
            writer.printf("Total Expenses: %.2f%n", totalExpenses);
            writer.printf("Profit: %.2f%n", profit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
