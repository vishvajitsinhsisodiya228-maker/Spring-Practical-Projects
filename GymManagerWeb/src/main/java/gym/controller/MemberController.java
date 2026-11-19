package gym.controller;

import gym.dao.MemberDao;
import gym.model.Member;
import gym.service.FeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {
    private final MemberDao memberDao;
    private final FeeCalculator feeCalculator;

    @Autowired
    public MemberController(MemberDao memberDao, FeeCalculator feeCalculator) {
        this.memberDao=memberDao; this.feeCalculator=feeCalculator;
    }

    @RequestMapping(value="/addMember",method=RequestMethod.GET)
    public String addMemberForm(){return "addMember";}

    @RequestMapping(value="/addMember",method=RequestMethod.POST)
    public String addMember(@RequestParam("name") String name,
                            @RequestParam("phone") String phone,
                            @RequestParam("plan") String plan,
                            @RequestParam("months") int months,
                            ModelMap model) {
        String cleanName=name==null?"":name.trim();
        String error=validate(cleanName,phone,months);
        if(error==null && memberDao.findByPhone(phone.trim())!=null) error="Phone number already registered";
        if(error!=null) {
            model.addAttribute("error",error);
            model.addAttribute("name",cleanName);
            model.addAttribute("phone",phone);
            model.addAttribute("plan",plan);
            model.addAttribute("months",months);
            return "addMember";
        }
        double fee=feeCalculator.totalFee(plan,months);
        Member member=new Member(0,cleanName,phone.trim(),plan,months,fee);
        memberDao.save(member);
        model.addAttribute("memberName",cleanName);
        model.addAttribute("fee",fee);
        return "memberAdded";
    }

    @RequestMapping(value="/members",method=RequestMethod.GET)
    public String members(ModelMap model){model.addAttribute("members",memberDao.findAll());return "members";}

    @RequestMapping(value="/editMember",method=RequestMethod.GET)
    public String editMember(@RequestParam("id") int id,ModelMap model) {
        Member member=memberDao.findById(id);
        if(member==null){model.addAttribute("error","Member not found");return "editMember";}
        model.addAttribute("member",member); return "editMember";
    }

    @RequestMapping(value="/updateMember",method=RequestMethod.POST)
    public String updateMember(@RequestParam("memberId") int memberId,
                               @RequestParam("plan") String plan,
                               @RequestParam("months") int months) {
        if(months>=1 && months<=12 && isValidPlan(plan)) {
            double fee=feeCalculator.totalFee(plan,months);
            memberDao.changePlan(memberId,plan,months,fee);
        }
        return "redirect:/members";
    }

    @RequestMapping(value="/deleteMember",method=RequestMethod.POST)
    public String deleteMember(@RequestParam("id") int id) {
        int changed=memberDao.deleteById(id);
        return changed>0?"redirect:/members?msg=deleted":"redirect:/members?msg=notfound";
    }

    @RequestMapping(value="/searchMember",method=RequestMethod.GET)
    public String searchMember(@RequestParam(value="phone",required=false) String phone,ModelMap model) {
        String clean=phone==null?"":phone.trim();
        if(clean.isEmpty()) model.addAttribute("message","Please enter a phone number");
        else {
            Member member=memberDao.findByPhone(clean);
            if(member==null) model.addAttribute("message","No member found");
            else model.addAttribute("member",member);
        }
        return "searchResult";
    }

    private String validate(String name,String phone,int months) {
        if(name.isBlank()) return "Name must not be blank";
        if(phone==null || !phone.trim().matches("\\d{10}")) return "Phone must contain exactly 10 digits";
        if(months<1 || months>12) return "Months must be between 1 and 12";
        return null;
    }
    private boolean isValidPlan(String plan) {
        return plan!=null && (plan.equals("Silver") || plan.equals("Gold") || plan.equals("Platinum"));
    }
}
