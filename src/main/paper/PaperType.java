package main.paper;

public enum PaperType {
    REGULAR(0.10),
    GLOSSY(0.15),
    NEWSPAPER(0.05);

    private final double basePrice;

    PaperType(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
