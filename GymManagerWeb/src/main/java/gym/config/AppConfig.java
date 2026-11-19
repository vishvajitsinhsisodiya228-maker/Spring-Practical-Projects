package gym.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages={"gym.service","gym.dao"})
public class AppConfig {
    @Bean public DataSource dataSource(){
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/gym_db?useSSL=false&serverTimezone=Asia/Kolkata");
        ds.setUsername("root"); ds.setPassword("");
        return ds;
    }
    @Bean public JdbcTemplate jdbcTemplate(DataSource dataSource){return new JdbcTemplate(dataSource);}
}
