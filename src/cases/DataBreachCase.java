package cases;

public class DataBreachCase extends CyberCase {

    private String dataType;        
    private int recordsBreached;    
    private String affectedSystem;  
    private String breachSource;    

    public DataBreachCase(String caseId, String title, String description,
                          String dateReported, String investigatorId, String victimId,
                          String dataType, int recordsBreached,
                          String affectedSystem, String breachSource) {
        super(caseId, title, description, dateReported, investigatorId, victimId);
        this.dataType        = dataType;
        this.recordsBreached = recordsBreached;
        this.affectedSystem  = affectedSystem;
        this.breachSource    = breachSource;
    }

    public String getDataType() {
        return dataType;
    }

    public int getRecordsBreached() {
        return recordsBreached;
    }

    public String getAffectedSystem() {
        return affectedSystem;
    }

    public String getBreachSource() {
        return breachSource;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setRecordsBreached(int recordsBreached) {
        this.recordsBreached = recordsBreached;
    }

    public void setBreachSource(String breachSource) {
        this.breachSource = breachSource;
    }

    @Override
    public String getCaseType() {
        return "Data Breach";
    }

    @Override
    public void generateReport() {
        super.generateReport();
        System.out.println("--- Data Breach Details ---");
        System.out.println("Data Type        : " + dataType);
        System.out.println("Records Breached : " + recordsBreached);
        System.out.println("Affected System  : " + affectedSystem);
        System.out.println("Breach Source    : " + breachSource);
        System.out.println("=================================");
    }

    @Override
    public String toFileString() {
        return "DATABREACH|" + super.toFileString() + "|" +
                dataType + "|" + recordsBreached + "|" + affectedSystem + "|" + breachSource;
    }

    public static DataBreachCase fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new DataBreachCase(
                parts[1], parts[2], parts[3], parts[4], parts[6], parts[7],
                parts[8], Integer.parseInt(parts[9]), parts[10], parts[11]
        );
    }

    @Override
    public String toString() {
        return "[Data Breach] " + getCaseId() + " - " + getTitle() + " | Status: " + getStatus();
    }
}