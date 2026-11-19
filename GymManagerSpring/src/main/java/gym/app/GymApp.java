package gym.app;
import gym.config.AppConfig;
import gym.dao.MemberDao;
import gym.model.Member;
import gym.service.MembershipService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class GymApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            MembershipService service=context.getBean(MembershipService.class);
            JdbcTemplate jdbc=context.getBean(JdbcTemplate.class);
            MemberDao dao=context.getBean(MemberDao.class);

            System.out.println("Gym fee quotes:");
            service.printQuote("Gold",1);
            service.printQuote("Silver",4);
            service.printQuote("Platinum",6);
            try { service.printQuote("Diamond",1); }
            catch (IllegalArgumentException e) { System.out.println("Invalid plan"); }

            service.printInvoice("Rohan","Gold",3);
            service.printInvoice("Meera","Silver",1);

            // In-memory registry exercise: five fictional members.
            String[][] people={
                {"Aarav Shah","9876500001","Gold","3"},
                {"Diya Patel","9876500002","Silver","1"},
                {"Kabir Mehta","9876500003","Platinum","6"},
                {"Isha Desai","9876500004","Gold","4"},
                {"Vivaan Rao","9876500005","Silver","2"}
            };
            for(String[] p:people) service.join(p[0],p[1],p[2],Integer.parseInt(p[3]));
            System.out.println("In-memory member roster:");
            service.roster().forEach(System.out::println);
            System.out.println("Registry lookup:");
            Member registered=service.findRegisteredPhone("9876500001");
            System.out.println(registered==null?"Member not found":registered);
            System.out.println(service.findRegisteredPhone("9000000000")==null?"Member not found":"Unexpected match");

            // JDBC persistence practical. This starts by showing the current table count.
            Integer count=jdbc.queryForObject("SELECT COUNT(*) FROM members",Integer.class);
            System.out.println("Current database row count: "+count);

            // Save five records to MySQL with prepared-statement placeholders.
            for(String[] p:people) {
                service.save(new Member(0,p[0],p[1],p[2],Integer.parseInt(p[3]),
                        service.calculateFee(p[2],Integer.parseInt(p[3]))));
            }
            System.out.println("Members read back from MySQL:");
            service.findAllFromDatabase().forEach(System.out::println);

            System.out.println("Phone lookup:");
            Member found=service.findByPhone("9876500001");
            System.out.println(found==null?"Member not found":found);
            System.out.println(service.findByPhone("9999999999")==null?"Member not found":"Unexpected match");

            // Update first member, delete second member, then repeat the delete.
            Member first=service.findByPhone("9876500001");
            if(first!=null) System.out.println("Changed rows: "+service.changePlan(first.getMemberId(),"Platinum",6));
            Member second=service.findByPhone("9876500002");
            if(second!=null) {
                System.out.println("Deleted rows: "+service.deleteById(second.getMemberId()));
                System.out.println("Deleted same ID again: "+service.deleteById(second.getMemberId()));
            }
            System.out.println("Final database table:");
            service.findAllFromDatabase().forEach(System.out::println);
        }
    }
}

/*
Typical console flow:
- Quote output: Gold 1, Silver 4, Platinum 6; Diamond -> Invalid plan
- Rohan and Meera invoices with registration fee and 18% GST
- Five in-memory members and one found / one Member not found
- Current database row count (0 on a fresh schema)
- Five rows read from MySQL
- One member's plan changed, another deleted; second delete returns 0
- Final members table printed

Exact row IDs and row count depend on the state of gym_db. For a fresh run,
create the database and table using sql/gym_db.sql before launching.
*/
