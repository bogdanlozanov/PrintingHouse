package main.employee;

public abstract class Employee {
    protected String name;
    protected static final double BASE_SALARY = 4000; // Set a single base salary for all employees

    public Employee(String name) {
        this.name = name;
    }

    public double calculateSalary() {
        return BASE_SALARY;
    }
}