package tiffin;
public class NoTaxCalculator implements TaxCalculator {
    @Override public double calculateTax(double itemTotal) { return 0.0; }
}
