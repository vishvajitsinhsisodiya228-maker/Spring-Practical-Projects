package tiffin;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TiffinSpringApp {
    public static void main(String[] args) {
        String configFile = args.length == 0 ? "beans.xml" : args[0];
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configFile)) {
            OrderService service = context.getBean("orderService", OrderService.class);
            System.out.println("Annapurna Tiffin Service | configuration: " + configFile);
            service.generateBill(180);
            service.generateBill(250);
            service.generateBill(450);
        }
    }
}

/*
Run default beans.xml:
mvn exec:java -Dexec.mainClass=tiffin.TiffinSpringApp
Run festival configuration:
mvn exec:java -Dexec.mainClass=tiffin.TiffinSpringApp -Dexec.args="beans-festival.xml"

With beans.xml (5% GST, ₹30 delivery below ₹300):
₹180 => GST ₹9, delivery ₹30, total ₹219
₹250 => GST ₹12.50, delivery ₹30, total ₹292.50
₹450 => GST ₹22.50, delivery ₹0, total ₹472.50

With beans-festival.xml (no tax, free delivery):
₹180 => total ₹180
₹250 => total ₹250
₹450 => total ₹450
*/
