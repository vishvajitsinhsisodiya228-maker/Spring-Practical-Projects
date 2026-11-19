package gym.dao;
import gym.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired public MemberDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    public int save(Member m) {
        return jdbcTemplate.update("INSERT INTO members(name, phone, plan, months, fee) VALUES (?, ?, ?, ?, ?)",
                m.getName(),m.getPhone(),m.getPlan(),m.getMonths(),m.getFee());
    }
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT member_id, name, phone, plan, months, fee FROM members ORDER BY member_id",
                new BeanPropertyRowMapper<>(Member.class));
    }
    public Member findByPhone(String phone) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT member_id, name, phone, plan, months, fee FROM members WHERE phone = ?",
                new BeanPropertyRowMapper<>(Member.class), phone);
        } catch (EmptyResultDataAccessException e) { return null; }
    }
    public Member findById(int id) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT member_id, name, phone, plan, months, fee FROM members WHERE member_id = ?",
                new BeanPropertyRowMapper<>(Member.class), id);
        } catch (EmptyResultDataAccessException e) { return null; }
    }
    public int changePlan(int memberId,String plan,int months,double fee) {
        return jdbcTemplate.update("UPDATE members SET plan = ?, months = ?, fee = ? WHERE member_id = ?",
                plan,months,fee,memberId);
    }
    public int deleteById(int memberId) {
        return jdbcTemplate.update("DELETE FROM members WHERE member_id = ?",memberId);
    }
}
