package cases;

public class MalwareCase extends CyberCase {

    private String malwareType;    
    private String affectedSystem;
    private String malwareHash;    

    public MalwareCase(String caseId, String title, String description,
                       String dateReported, String investigatorId, String victimId,
                       String malwareType, String affectedSystem, String malwareHash) {
        super(caseId, title, description, dateReported, investigatorId, victimId);
        this.malwareType    = malwareType;
        this.affectedSystem = affectedSystem;
        this.malwareHash    = malwareHash;
    }

    public String getMalwareType() {
        return malwareType;
    }

    public String getAffectedSystem() {
        return affectedSystem;
    }

    public String getMalwareHash() {
        return malwareHash;
    }

    public void setMalwareType(String malwareType) {
        this.malwareType = malwareType;
    }

    public void setAffectedSystem(String affectedSystem) {
        this.affectedSystem = affectedSystem;
    }

    @Override
    public String getCaseType() {
        return "Malware";
    }

    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("--- Malware Details ---");
        System.out.println("Malware Type    : " + malwareType);
        System.out.println("Affected System : " + affectedSystem);
        System.out.println("Malware Hash    : " + malwareHash);
        System.out.println("=================================");
    }

    @Override
    public String toFileString() {
        return "MALWARE|" + super.toFileString() + "|" +
                malwareType + "|" + affectedSystem + "|" + malwareHash;
    }

    public static MalwareCase fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new MalwareCase(
                parts[1], parts[2], parts[3], parts[4], parts[6], parts[7],
                parts[8], parts[9], parts[10]
        );
    }

    @Override
    public String toString() {
        return "[Malware] " + getCaseId() + " - " + getTitle() + " | Status: " + getStatus();
    }
}