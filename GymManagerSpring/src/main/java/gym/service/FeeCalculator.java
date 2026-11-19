package gym.service;
import org.springframework.stereotype.Component;
@Component
public class FeeCalculator {
    public double totalFee(String plan, int months) {
        if (months < 1) throw new IllegalArgumentException("Months must be at least 1");
        double monthly;
        switch (plan == null ? "" : plan.trim().toLowerCase()) {
            case "silver": monthly=800; break;
            case "gold": monthly=1200; break;
            case "platinum": monthly=1800; break;
            default: throw new IllegalArgumentException("Invalid plan");
        }
        double total=monthly*months;
        if (months>=6) total*=0.90;
        else if (months>=3) total*=0.95;
        return total;
    }
}
