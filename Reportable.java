package cases;

public class RansomwareCase extends CyberCase {

    private String ransomAmount;    
    private String paymentMethod;   
    private String encryptedFiles;  
    private boolean ransomPaid;     

    public RansomwareCase(String caseId, String title, String description,
                          String dateReported, String investigatorId, String victimId,
                          String ransomAmount, String paymentMethod,
                          String encryptedFiles, boolean ransomPaid) {
        super(caseId, title, description, dateReported, investigatorId, victimId);
        this.ransomAmount    = ransomAmount;
        this.paymentMethod   = paymentMethod;
        this.encryptedFiles  = encryptedFiles;
        this.ransomPaid      = ransomPaid;
    }

    public String getRansomAmount() {
        return ransomAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getEncryptedFiles() {
        return encryptedFiles;
    }

    public boolean isRansomPaid() {
        return ransomPaid;
    }

    public void setRansomAmount(String ransomAmount) {
        this.ransomAmount = ransomAmount;
    }

    public void setRansomPaid(boolean ransomPaid) {
        this.ransomPaid = ransomPaid;
    }

    @Override
    public String getCaseType() {
        return "Ransomware";
    }

    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("--- Ransomware Details ---");
        System.out.println("Ransom Amount   : " + ransomAmount);
        System.out.println("Payment Method  : " + paymentMethod);
        System.out.println("Encrypted Files : " + encryptedFiles);
        System.out.println("Ransom Paid     : " + (ransomPaid ? "Yes" : "No"));
        System.out.println("=================================");
    }

    @Override
    public String toFileString() {
        return "RANSOMWARE|" + super.toFileString() + "|" +
                ransomAmount + "|" + paymentMethod + "|" + encryptedFiles + "|" + ransomPaid;
    }

    public static RansomwareCase fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new RansomwareCase(
                parts[1], parts[2], parts[3], parts[4], parts[6], parts[7],
                parts[8], parts[9], parts[10], Boolean.parseBoolean(parts[11])
        );
    }

    @Override
    public String toString() {
        return "[Ransomware] " + getCaseId() + " - " + getTitle() + " | Status: " + getStatus();
    }
}