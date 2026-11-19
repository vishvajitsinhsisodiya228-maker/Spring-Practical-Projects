package tiffin;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TrialPackBill {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            OrderService service = context.getBean("orderService", OrderService.class);
            for (String id : new String[]{"basicPlan", "standardPlan", "premiumPlan"}) {
                TiffinPlan plan = context.getBean(id, TiffinPlan.class);
                System.out.println(plan.getName() + " 2-day trial:");
                service.generateBill(plan.getPricePerDay() * 2);
            }
        }
    }
}
