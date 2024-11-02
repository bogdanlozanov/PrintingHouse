package main.edition;

import main.paper.PaperSize;
import main.paper.PaperType;

public abstract class Edition {
    protected String title;
    protected int numberOfCopies;
    protected PaperSize pageSize;

    public Edition(String title, int numberOfCopies, PaperSize pageSize) {
        this.title = title;
        this.numberOfCopies = numberOfCopies;
        this.pageSize = pageSize;
    }

    public abstract double getPrintCost(PaperType paperType);

    // Add this getter method
    public int getNumberOfCopies() {
        return numberOfCopies;
    }
}
