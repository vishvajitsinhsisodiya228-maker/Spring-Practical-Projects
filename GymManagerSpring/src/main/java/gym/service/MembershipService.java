package gym.service;
import gym.dao.MemberDao;
import gym.model.GymSettings;
import gym.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.DecimalFormat;
import java.util.List;

@Component
public class MembershipService {
    private final FeeCalculator feeCalculator;
    private final MemberRegistry registry;
    private final MemberDao memberDao;
    private final DecimalFormat rupeeFormat;
    private final GymSettings gymSettings;
    private int nextId=1;

    @Autowired
    public MembershipService(FeeCalculator feeCalculator, MemberRegistry registry, MemberDao memberDao,
                             DecimalFormat rupeeFormat, GymSettings gymSettings) {
        this.feeCalculator=feeCalculator; this.registry=registry; this.memberDao=memberDao;
        this.rupeeFormat=rupeeFormat; this.gymSettings=gymSettings;
    }
    public double calculateFee(String plan,int months){return feeCalculator.totalFee(plan,months);}
    public void printQuote(String plan,int months){
        System.out.printf("%s %d month(s): ₹%s%n",plan,months,rupeeFormat.format(calculateFee(plan,months)));
    }
    public void printInvoice(String memberName,String plan,int months) {
        double planFee=calculateFee(plan,months);
        double beforeTax=planFee+gymSettings.getRegistrationFee();
        double gst=beforeTax*gymSettings.getGstPercent()/100.0;
        System.out.println("Invoice - "+gymSettings.getGymName());
        System.out.println("Member: "+memberName+" | Plan: "+plan+" | Months: "+months);
        System.out.println("Plan fee: ₹"+rupeeFormat.format(planFee));
        System.out.println("Registration: ₹"+rupeeFormat.format(gymSettings.getRegistrationFee()));
        System.out.println("GST: ₹"+rupeeFormat.format(gst));
        System.out.println("Grand total: ₹"+rupeeFormat.format(beforeTax+gst));
    }
    public Member join(String name,String phone,String plan,int months) {
        Member m=new Member(nextId++,name,phone,plan,months,calculateFee(plan,months));
        registry.add(m); return m;
    }
    public List<Member> roster(){return registry.findAll();}
    public Member findRegisteredPhone(String phone){return registry.findByPhone(phone);}
    public int save(Member member){return memberDao.save(member);}
    public List<Member> findAllFromDatabase(){return memberDao.findAll();}
    public Member findByPhone(String phone){return memberDao.findByPhone(phone);}
    public int changePlan(int id,String plan,int months){return memberDao.changePlan(id,plan,months,calculateFee(plan,months));}
    public int deleteById(int id){return memberDao.deleteById(id);}
}
