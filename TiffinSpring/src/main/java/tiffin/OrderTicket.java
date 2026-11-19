package tiffin;
public class OrderTicket {
    private static int nextNumber = 1001;
    private final int ticketNumber = nextNumber++;
    public int getTicketNumber() { return ticketNumber; }
}
