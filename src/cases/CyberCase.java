package cases;

import interfaces.Reportable;
import interfaces.Trackable;

public abstract class CyberCase implements Reportable, Trackable {

    private String caseId;
    private String title;
    private String description;
    private String dateReported;
    private String status;         
    private String investigatorId;
    private String victimId;

    public CyberCase(String caseId, String title, String description,
                     String dateReported, String investigatorId, String victimId) {
        this.caseId        = caseId;
        this.title         = title;
        this.description   = description;
        this.dateReported  = dateReported;
        this.status        = "Open"; 
        this.investigatorId = investigatorId;
        this.victimId      = victimId;
    }

    public String getCaseId()        { return caseId; }
    public String getTitle()         { return title; }
    public String getDescription()   { return description; }
    public String getDateReported()  { return dateReported; }
    public String getStatus()        { return status; }
    public String getInvestigatorId(){ return investigatorId; }
    public String getVictimId()      { return victimId; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setInvestigatorId(String id)       { this.investigatorId = id; }

    @Override
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Case " + caseId + " status updated to: " + newStatus);
    }

    public abstract String getCaseType();

    @Override
    public void generateReport() {
        System.out.println("========== CASE REPORT ==========");
        System.out.println("Case ID       : " + caseId);
        System.out.println("Case Type     : " + getCaseType());
        System.out.println("Title         : " + title);
        System.out.println("Description   : " + description);
        System.out.println("Date Reported : " + dateReported);
        System.out.println("Status        : " + status);
        System.out.println("Investigator  : " + investigatorId);
        System.out.println("Victim        : " + victimId);
        System.out.println("=================================");
    }

    public String toFileString() {
        return caseId + "|" + title + "|" + description + "|" +
                dateReported + "|" + status + "|" + investigatorId + "|" + victimId;
    }

    @Override
    public String toString() {
        return "[" + getCaseType() + "] " + caseId + " - " + title + " | Status: " + status;
    }
}