package main.edition;

import main.paper.PaperSize;
import main.paper.PaperType;

public class Edition {
    private String title;
    private int numberOfCopies;
    private PaperSize pageSize;
    private PaperType paperType;
    private EditionType editionType;
    private double pricePerCopy; // Price charged to customers per copy

    public Edition(String title, int numberOfCopies, PaperSize pageSize, PaperType paperType, EditionType editionType, double pricePerCopy) {
        this.title = title;
        this.numberOfCopies = numberOfCopies;
        this.pageSize = pageSize;
        this.paperType = paperType;
        this.editionType = editionType;
        this.pricePerCopy = pricePerCopy;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public PaperSize getPageSize() {
        return pageSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public double getPricePerCopy() {
        return pricePerCopy;
    }

    // Calculate print cost for internal use
    public double getPrintCost() {
        double basePrice = paperType.getBasePrice(); // Cost of paper
        double sizeMultiplier = pageSize.getMultiplier(); // Size adjustment
        return basePrice * sizeMultiplier * numberOfCopies;
    }
}
