package main.employee;

public class Manager extends Employee {
    private double commissionRate;

    public Manager(String name, double commissionRate) {
        super(name);
        this.commissionRate = commissionRate;
    }

    public double calculateSalary(double totalRevenue, double revenueThreshold) {
        double base = BASE_SALARY;
        return totalRevenue > revenueThreshold ? base + base * commissionRate : base;
    }
}
