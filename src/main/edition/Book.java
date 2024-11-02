package main.edition;

import main.paper.PaperSize;
import main.paper.PaperType;

public class Book extends Edition {
    private static final int numberOfPages = 100; // Example page count

    public Book(String title, int numberOfCopies, PaperSize pageSize) {
        super(title, numberOfCopies, pageSize);
    }

    @Override
    public double getPrintCost(PaperType paperType) {
        double costPerPage = paperType.getBasePrice() * pageSize.getMultiplier();
        return costPerPage * numberOfPages * numberOfCopies;
    }
}
