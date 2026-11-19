package tiffin;

/** Tasks 1 and 3: manual dependency creation/injection, without Spring. */
public class TiffinBillApp {
    public static void main(String[] args) {
        System.out.println("Annapurna Tiffin Service - GST bills");
        OrderService gstService = new OrderService(new GstTaxCalculator());
        gstService.generateBill(180);
        gstService.generateBill(250);

        System.out.println("Annapurna Tiffin Service - No-tax bills");
        OrderService noTaxService = new OrderService(new NoTaxCalculator());
        noTaxService.generateBill(180);
        noTaxService.generateBill(250);
    }
}

/*
Expected four item/tax totals:
GST 5% on ₹180: GST ₹9.00, total before delivery ₹189.00
GST 5% on ₹250: GST ₹12.50, total before delivery ₹262.50
NoTax on ₹180: GST ₹0.00, total before delivery ₹180.00
NoTax on ₹250: GST ₹0.00, total before delivery ₹250.00
*/
