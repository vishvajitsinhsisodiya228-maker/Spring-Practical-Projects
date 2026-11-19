package tiffin;
public class GstTaxCalculator implements TaxCalculator {
    @Override public double calculateTax(double itemTotal) { return itemTotal * 0.05; }
}
