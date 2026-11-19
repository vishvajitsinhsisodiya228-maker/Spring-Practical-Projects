package tiffin;
public class DeliveryPolicy {
    private double deliveryFee;
    private double freeAbove;
    public double getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; }
    public double getFreeAbove() { return freeAbove; }
    public void setFreeAbove(double freeAbove) { this.freeAbove = freeAbove; }
    public double getFeeFor(double itemTotal) { return itemTotal >= freeAbove ? 0.0 : deliveryFee; }
}
