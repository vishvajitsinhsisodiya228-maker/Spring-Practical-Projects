package tiffin;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class PlanListApp {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            for (String id : new String[]{"basicPlan", "standardPlan", "premiumPlan"}) {
                TiffinPlan plan = context.getBean(id, TiffinPlan.class);
                System.out.printf("%s: ₹%.2f per day%n", plan.getName(), plan.getPricePerDay());
            }
        }
    }
}
