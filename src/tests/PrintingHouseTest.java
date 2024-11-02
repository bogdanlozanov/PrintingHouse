package tests;

import main.edition.Book;
import main.edition.Edition;
import main.employee.Manager;
import main.house.PrintingHouse;
import main.paper.PaperSize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrintingHouseTest {

    @Test
    public void testCalculateRevenue() {
        PrintingHouse printingHouse = new PrintingHouse(50000, 10);
        Edition book = new Book("Sample Book", 1500, PaperSize.A4);
        printingHouse.addEdition(book);
        printingHouse.calculateRevenue();
        assertTrue(printingHouse.getTotalRevenue() > 0);
    }

    @Test
    public void testEmployeeSalaryCalculation() {
        // Using the static base salary from the Employee class for consistency
        Manager manager = new Manager("Alex", 0.1); // Only need the name and commission rate now
        double salary = manager.calculateSalary(60000, 50000); // Revenue is over threshold, so should include commission
        assertEquals(4400, salary); // BASE_SALARY + 10% commission (4000 + 400)
    }
}
