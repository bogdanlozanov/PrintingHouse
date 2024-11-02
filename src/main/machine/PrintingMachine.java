package main.machine;

import main.edition.Edition;
import exceptions.MachineOverloadException;
import exceptions.UnsupportedPrintTypeException;

import java.util.ArrayList;
import java.util.List;

public class PrintingMachine {
    private int maxPaperCapacity;
    private boolean isColor;
    private int pagesPerMinute;
    private List<Edition> printedEditions = new ArrayList<>();
    private int totalPrintedPages;

    public PrintingMachine(int maxPaperCapacity, boolean isColor, int pagesPerMinute) {
        this.maxPaperCapacity = maxPaperCapacity;
        this.isColor = isColor;
        this.pagesPerMinute = pagesPerMinute;
    }

    public void loadPaper(int sheets) throws MachineOverloadException {
        if (sheets > maxPaperCapacity) {
            throw new MachineOverloadException("Exceeded maximum paper capacity of the machine.");
        }
    }

    public void printEdition(Edition edition, boolean isColorPrint) throws UnsupportedPrintTypeException {
        if (isColorPrint && !isColor) {
            throw new UnsupportedPrintTypeException("This machine does not support color printing.");
        }
        printedEditions.add(edition);
        totalPrintedPages += edition.getPrintCost(null); // You might need to refine based on paper
    }

    public int getTotalPrintedPages() {
        return totalPrintedPages;
    }
}
