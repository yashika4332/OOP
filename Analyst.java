package core;

public class Evidence {

    private String evidenceId;
    private String caseId;
    private String evidenceType;   
    private String description;
    private String collectedBy;    
    private String collectedDate;
    private String filePath;       

    public Evidence(String evidenceId, String caseId, String evidenceType,
                    String description, String collectedBy,
                    String collectedDate, String filePath) {
        this.evidenceId    = evidenceId;
        this.caseId        = caseId;
        this.evidenceType  = evidenceType;
        this.description   = description;
        this.collectedBy   = collectedBy;
        this.collectedDate = collectedDate;
        this.filePath      = filePath;
    }

    public String getEvidenceId()    { return evidenceId; }
    public String getCaseId()        { return caseId; }
    public String getEvidenceType()  { return evidenceType; }
    public String getDescription()   { return description; }
    public String getCollectedBy()   { return collectedBy; }
    public String getCollectedDate() { return collectedDate; }
    public String getFilePath()      { return filePath; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void displayEvidence() {
        System.out.println("====================");
        System.out.println("Evidence ID    : " + evidenceId);
        System.out.println("Case ID        : " + caseId);
        System.out.println("Type           : " + evidenceType);
        System.out.println("Description    : " + description);
        System.out.println("Collected By   : " + collectedBy);
        System.out.println("Collected Date : " + collectedDate);
        System.out.println("File Path      : " + filePath);
        System.out.println("====================");
    }

    public String toFileString() {
        return evidenceId + "|" + caseId + "|" + evidenceType + "|" +
                description + "|" + collectedBy + "|" + collectedDate + "|" + filePath;
    }

    public static Evidence fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Evidence(
                parts[0], parts[1], parts[2],
                parts[3], parts[4], parts[5], parts[6]
        );
    }

    @Override
    public String toString() {
        return "[" + evidenceType + "] " + evidenceId + " - " + description + " | Case: " + caseId;
    }
}