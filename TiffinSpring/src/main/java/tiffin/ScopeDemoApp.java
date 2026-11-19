package tiffin;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class ScopeDemoApp {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            OrderTicket a = context.getBean("ticket", OrderTicket.class);
            OrderTicket b = context.getBean("ticket", OrderTicket.class);
            OrderTicket c = context.getBean("ticket", OrderTicket.class);
            System.out.println(a.getTicketNumber());
            System.out.println(b.getTicketNumber());
            System.out.println(c.getTicketNumber());
            KitchenInfo k1 = context.getBean("kitchenInfo", KitchenInfo.class);
            KitchenInfo k2 = context.getBean("kitchenInfo", KitchenInfo.class);
            KitchenInfo k3 = context.getBean("kitchenInfo", KitchenInfo.class);
            System.out.println(k1 == k2 && k2 == k3);
        }
    }
}
