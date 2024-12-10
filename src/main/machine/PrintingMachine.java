package main.machine;

import main.edition.Edition;
import exceptions.MachineOverloadException;
import exceptions.UnsupportedPrintTypeException;

import java.util.ArrayList;
import java.util.List;

public class PrintingMachine {
    private int maxPaperCapacity;
    private boolean isColor;
    private int loadedPaper = 0;
    private List<Edition> printedEditions = new ArrayList<>();

    public PrintingMachine(int maxPaperCapacity, boolean isColor) {
        this.maxPaperCapacity = maxPaperCapacity;
        this.isColor = isColor;
    }

    /**
     * Loads paper into the machine.
     *
     * @param sheets Number of sheets to load.
     * @throws MachineOverloadException if the sheets exceed max capacity.
     */
    public void loadPaper(int sheets) throws MachineOverloadException {
        if (sheets > maxPaperCapacity - loadedPaper) {
            throw new MachineOverloadException("Exceeded maximum paper capacity of the machine.");
        }
        this.loadedPaper += sheets;
    }

    /**
     * Prints the given edition.
     *
     * @param edition      The edition to print.
     * @param isColorPrint Whether the print should be in color.
     * @throws UnsupportedPrintTypeException if the machine cannot handle color prints.
     */
    public void printEdition(Edition edition, boolean isColorPrint) throws UnsupportedPrintTypeException {
        if (isColorPrint && !isColor) {
            throw new UnsupportedPrintTypeException("This machine does not support color printing.");
        }
        // Calculate required pages, cast to int to match loadedPaper type
        int pagesRequired = (int) (edition.getPageSize().getMultiplier() * edition.getNumberOfCopies());
        if (pagesRequired > loadedPaper) {
            throw new UnsupportedPrintTypeException("Not enough paper to print this edition.");
        }
        loadedPaper -= pagesRequired;
        printedEditions.add(edition);
    }

    /**
     * Calculates the total number of printed pages.
     *
     * @return Total number of pages printed by this machine.
     */
    public int calculateTotalPrintedPages() {
        return printedEditions.stream()
                .mapToInt(e -> (int) (e.getNumberOfCopies() * e.getPageSize().getMultiplier()))
                .sum();
    }

    public boolean isColor() {
        return isColor;
    }

}
