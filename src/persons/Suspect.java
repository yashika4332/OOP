package persons;

public class Suspect extends Person {

    private String caseId;
    private String ipAddress;
    private String deviceInfo;
    private String threatLevel; 
    private String status;      

    public Suspect(String personId, String name, String email, String phone,
                   String address, String caseId, String ipAddress,
                   String deviceInfo, String threatLevel, String status) {
        super(personId, name, email, phone, address);
        this.caseId     = caseId;
        this.ipAddress  = ipAddress;
        this.deviceInfo = deviceInfo;
        this.threatLevel = threatLevel;
        this.status     = status;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public String getThreatLevel() {
        return threatLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String getRole() {
        return "Suspect";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Case ID      : " + caseId);
        System.out.println("IP Address   : " + ipAddress);
        System.out.println("Device Info  : " + deviceInfo);
        System.out.println("Threat Level : " + threatLevel);
        System.out.println("Status       : " + status);
        System.out.println("====================");
    }

    @Override
    public String toFileString() {
        return "SUSPECT|" + super.toFileString() + "|" +
                caseId + "|" + ipAddress + "|" + deviceInfo + "|" + threatLevel + "|" + status;
    }

    public static Suspect fromFileString(String line) {
        String[] parts = line.split("\\|");
        String[] base  = parts[1].split(",");
        return new Suspect(
                base[0], base[1], base[2], base[3], base[4],
                parts[2], parts[3], parts[4], parts[5], parts[6]
        );
    }

    @Override
    public String toString() {
        return "[Suspect] " + getName() + " | IP: " + ipAddress +
                " | Threat: " + threatLevel + " | Status: " + status;
    }
}