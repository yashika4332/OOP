package persons;

public class Victim extends Person {

    private String caseId;
    private String attackType;
    private String incidentDate;
    private String lossDescription;

    public Victim(String personId, String name, String email, String phone,
                  String address, String caseId, String attackType,
                  String incidentDate, String lossDescription) {
        super(personId, name, email, phone, address);
        this.caseId          = caseId;
        this.attackType      = attackType;
        this.incidentDate    = incidentDate;
        this.lossDescription = lossDescription;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getAttackType() {
        return attackType;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public String getLossDescription() {
        return lossDescription;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public void setLossDescription(String lossDescription) {
        this.lossDescription = lossDescription;
    }

    @Override
    public String getRole() {
        return "Victim";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Case ID          : " + caseId);
        System.out.println("Attack Type      : " + attackType);
        System.out.println("Incident Date    : " + incidentDate);
        System.out.println("Loss Description : " + lossDescription);
        System.out.println("====================");
    }

    @Override
    public String toFileString() {
        return "VICTIM|" + super.toFileString() + "|" +
                caseId + "|" + attackType + "|" + incidentDate + "|" + lossDescription;
    }

    public static Victim fromFileString(String line) {
        String[] parts = line.split("\\|");
        String[] base  = parts[1].split(",");
        return new Victim(
                base[0], base[1], base[2], base[3], base[4],
                parts[2], parts[3], parts[4], parts[5]
        );
    }

    @Override
    public String toString() {
        return "[Victim] " + getName() + " | Attack: " + attackType + " | Date: " + incidentDate;
    }
}