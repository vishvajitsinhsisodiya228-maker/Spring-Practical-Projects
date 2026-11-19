package tiffin;

/** Final version: setter injection supports XML byType autowiring. */
public class OrderService {
    private TaxCalculator taxCalculator;
    private DeliveryPolicy deliveryPolicy;

    public OrderService() { }
    public OrderService(TaxCalculator taxCalculator) { this.taxCalculator = taxCalculator; }

    public void setTaxCalculator(TaxCalculator taxCalculator) { this.taxCalculator = taxCalculator; }
    public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) { this.deliveryPolicy = deliveryPolicy; }

    public void generateBill(double itemTotal) {
        if (itemTotal <= 0) {
            System.out.println("Invalid order");
            return;
        }
        if (taxCalculator == null) throw new IllegalStateException("TaxCalculator is not configured");
        double tax = taxCalculator.calculateTax(itemTotal);
        double delivery = deliveryPolicy == null ? 0.0 : deliveryPolicy.getFeeFor(itemTotal);
        System.out.printf("Item total: ₹%.2f%nGST: ₹%.2f%nDelivery: ₹%.2f%nGrand total: ₹%.2f%n",
                itemTotal, tax, delivery, itemTotal + tax + delivery);
        System.out.println("--------------------------------");
    }
}
