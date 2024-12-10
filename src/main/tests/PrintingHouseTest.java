package main.tests;

import main.edition.Edition;
import main.edition.EditionType;
import main.house.PrintingHouse;
import main.paper.PaperSize;
import main.paper.PaperType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrintingHouseTest {

    @Test
    public void testRevenueCalculationWithMultipleEditions() {
        PrintingHouse printingHouse = new PrintingHouse(50000, 10); // Revenue threshold: $50000, discount: 10%

        // Adding Editions with pricePerCopy
        Edition magazine = new Edition("Tech Monthly", 500, PaperSize.A4, PaperType.GLOSSY, EditionType.MAGAZINE, 7.00); // Price per copy: $7.00
        Edition newspaper = new Edition("Daily News", 1000, PaperSize.A3, PaperType.NEWSPAPER, EditionType.NEWSPAPER, 3.00); // Price per copy: $3.00

        printingHouse.addEdition(magazine);
        printingHouse.addEdition(newspaper);

        // Calculate revenue
        double revenue = printingHouse.calculateTotalRevenue();

        // Expected revenue calculation
        double expectedMagazineRevenue = 500 * 7.00; // No discount for 500 copies
        double expectedNewspaperRevenue = 1000 * 3.00; // No discount for exactly 1000 copies
        double expectedRevenue = expectedMagazineRevenue + expectedNewspaperRevenue;

        assertEquals(expectedRevenue, revenue, 0.01, "The calculated revenue should match the expected revenue.");
    }

    @Test
    public void testRevenueCalculationWithDiscount() {
        PrintingHouse printingHouse = new PrintingHouse(50000, 10); // Revenue threshold: $50000, discount: 10%

        // Adding Editions with pricePerCopy
        Edition bulkMagazine = new Edition("Bulk Tech Monthly", 2000, PaperSize.A4, PaperType.GLOSSY, EditionType.MAGAZINE, 7.00); // Price per copy: $7.00
        Edition regularNewspaper = new Edition("Daily News", 800, PaperSize.A3, PaperType.NEWSPAPER, EditionType.NEWSPAPER, 3.00); // Price per copy: $3.00

        printingHouse.addEdition(bulkMagazine);
        printingHouse.addEdition(regularNewspaper);

        // Calculate revenue
        double revenue = printingHouse.calculateTotalRevenue();

        // Expected revenue calculation
        double bulkMagazineTotalRevenue = 2000 * 7.00;
        double bulkMagazineDiscountedRevenue = bulkMagazineTotalRevenue - (bulkMagazineTotalRevenue * 10 / 100); // 10% discount applied
        double regularNewspaperRevenue = 800 * 3.00; // No discount for 800 copies
        double expectedRevenue = bulkMagazineDiscountedRevenue + regularNewspaperRevenue;

        assertEquals(expectedRevenue, revenue, 0.01, "The calculated revenue with discounts should match the expected revenue.");
    }
}
