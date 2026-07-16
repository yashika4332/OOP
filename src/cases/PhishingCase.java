package cases;

public class PhishingCase extends CyberCase {

    private String fakeUrl;
    private String targetedPlatform; 
    private int victimsAffected;

    public PhishingCase(String caseId, String title, String description,
                        String dateReported, String investigatorId, String victimId,
                        String fakeUrl, String targetedPlatform, int victimsAffected) {
        super(caseId, title, description, dateReported, investigatorId, victimId);
        this.fakeUrl          = fakeUrl;
        this.targetedPlatform = targetedPlatform;
        this.victimsAffected  = victimsAffected;
    }

    public String getFakeUrl() {
        return fakeUrl;
    }

    public String getTargetedPlatform() {
        return targetedPlatform;
    }

    public int getVictimsAffected() {
        return victimsAffected;
    }

    public void setFakeUrl(String fakeUrl) {
        this.fakeUrl = fakeUrl;
    }

    public void setVictimsAffected(int victimsAffected) {
        this.victimsAffected = victimsAffected;
    }

    @Override
    public String getCaseType() {
        return "Phishing";
    }

    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("--- Phishing Details ---");
        System.out.println("Fake URL          : " + fakeUrl);
        System.out.println("Targeted Platform : " + targetedPlatform);
        System.out.println("Victims Affected  : " + victimsAffected);
        System.out.println("=================================");
    }

    @Override
    public String toFileString() {
        return "PHISHING|" + super.toFileString() + "|" +
                fakeUrl + "|" + targetedPlatform + "|" + victimsAffected;
    }

    public static PhishingCase fromFileString(String line) {
        String[] parts = line.split("\\|");

        return new PhishingCase(
                parts[1], parts[2], parts[3], parts[4], parts[6], parts[7],
                parts[8], parts[9], Integer.parseInt(parts[10])
        );
    }

    @Override
    public String toString() {
        return "[Phishing] " + getCaseId() + " - " + getTitle() + " | Status: " + getStatus();
    }
}