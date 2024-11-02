package main.paper;

public class Paper {
    private PaperType type;
    private PaperSize size;
    private double pricePerSheet;

    public Paper(PaperType type, PaperSize size) {
        this.type = type;
        this.size = size;
        this.pricePerSheet = type.getBasePrice() * size.getMultiplier();
    }

    public double getPricePerSheet() {
        return pricePerSheet;
    }
}
