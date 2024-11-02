package main.paper;

public enum PaperSize {
    A5(1),
    A4(1.5),
    A3(2),
    A2(2.5),
    A1(3);

    private final double multiplier;

    PaperSize(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
