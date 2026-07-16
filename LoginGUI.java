package cases;

public class IdentityTheftCase extends CyberCase {

    private String stolenIdType;    
    private String stolenIdNumber;
    private String misusedPlatform; 

    public IdentityTheftCase(String caseId, String title, String description,
                             String dateReported, String investigatorId, String victimId,
                             String stolenIdType, String stolenIdNumber, String misusedPlatform) {
        super(caseId, title, description, dateReported, investigatorId, victimId);
        this.stolenIdType    = stolenIdType;
        this.stolenIdNumber  = stolenIdNumber;
        this.misusedPlatform = misusedPlatform;
    }

    public String getStolenIdType() {
        return stolenIdType;
    }

    public String getStolenIdNumber() {
        return stolenIdNumber;
    }

    public String getMisusedPlatform() {
        return misusedPlatform;
    }

    public void setStolenIdType(String stolenIdType) {
        this.stolenIdType = stolenIdType;
    }

    public void setMisusedPlatform(String misusedPlatform) {
        this.misusedPlatform = misusedPlatform;
    }

    @Override
    public String getCaseType() {
        return "Identity Theft";
    }

    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("--- Identity Theft Details ---");
        System.out.println("Stolen ID Type   : " + stolenIdType);
        System.out.println("Stolen ID Number : " + stolenIdNumber);
        System.out.println("Misused Platform : " + misusedPlatform);
        System.out.println("=================================");
    }

    @Override
    public String toFileString() {
        return "IDENTITYTHEFT|" + super.toFileString() + "|" +
                stolenIdType + "|" + stolenIdNumber + "|" + misusedPlatform;
    }

    public static IdentityTheftCase fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new IdentityTheftCase(
                parts[1], parts[2], parts[3], parts[4], parts[6], parts[7],
                parts[8], parts[9], parts[10]
        );
    }

    @Override
    public String toString() {
        return "[Identity Theft] " + getCaseId() + " - " + getTitle() + " | Status: " + getStatus();
    }
}