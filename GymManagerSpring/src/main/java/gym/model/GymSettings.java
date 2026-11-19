package gym.model;
public class GymSettings {
    private String gymName;
    private double registrationFee;
    private double gstPercent;
    public GymSettings(String gymName, double registrationFee, double gstPercent) {
        this.gymName=gymName; this.registrationFee=registrationFee; this.gstPercent=gstPercent;
    }
    public String getGymName(){return gymName;} public double getRegistrationFee(){return registrationFee;}
    public double getGstPercent(){return gstPercent;}
}
