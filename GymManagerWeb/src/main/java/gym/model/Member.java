package gym.model;
public class Member {
    private int memberId;
    private String name;
    private String phone;
    private String plan;
    private int months;
    private double fee;
    public Member() { }
    public Member(int memberId, String name, String phone, String plan, int months, double fee) {
        this.memberId=memberId; this.name=name; this.phone=phone; this.plan=plan; this.months=months; this.fee=fee;
    }
    public int getMemberId(){return memberId;} public void setMemberId(int v){memberId=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getPlan(){return plan;} public void setPlan(String v){plan=v;}
    public int getMonths(){return months;} public void setMonths(int v){months=v;}
    public double getFee(){return fee;} public void setFee(double v){fee=v;}
    @Override public String toString(){return String.format("%d | %s | %s | %s | %d | ₹%.2f",memberId,name,phone,plan,months,fee);}
}
