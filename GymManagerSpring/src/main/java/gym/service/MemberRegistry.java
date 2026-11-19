package gym.service;
import gym.model.Member;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class MemberRegistry {
    private final List<Member> members = new ArrayList<>();
    public void add(Member member){members.add(member);}
    public Member findByPhone(String phone){return members.stream().filter(m->m.getPhone().equals(phone)).findFirst().orElse(null);}
    public List<Member> findAll(){return new ArrayList<>(members);}
}
