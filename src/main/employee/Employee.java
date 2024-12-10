package main.employee;

public class Employee {
    private String name;
    private double commissionRate; // Applicable only for managers
    private boolean isManager;

    private static final double BASE_SALARY = 4000; // Consistent base salary for all employees

    public Employee(String name, boolean isManager, double commissionRate) {
        this.name = name;
        this.isManager = isManager;
        this.commissionRate = commissionRate;
    }

    /**
     * Calculates the salary of the employee.
     *
     * @param totalRevenue      The total revenue of the printing house.
     * @param revenueThreshold  The threshold above which managers earn commission.
     * @return the calculated salary.
     */
    public double calculateSalary(double totalRevenue, double revenueThreshold) {
        if (isManager && totalRevenue > revenueThreshold) {
            return BASE_SALARY + (BASE_SALARY * commissionRate); // Add commission
        }
        return BASE_SALARY; // Base salary for all other cases
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public boolean isManager() {
        return isManager;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}