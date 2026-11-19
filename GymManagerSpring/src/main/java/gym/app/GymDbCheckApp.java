package gym.app;
import gym.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
public class GymDbCheckApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext c=new AnnotationConfigApplicationContext(AppConfig.class)) {
            Integer count=c.getBean(JdbcTemplate.class).queryForObject("SELECT COUNT(*) FROM members",Integer.class);
            System.out.println("Initial member row count: "+count);
        }
    }
}
