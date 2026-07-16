package persons;

public class Analyst extends Person {

    private String analystId;
    private String expertiseArea;

    public Analyst(String personId, String name, String email, String phone,
                   String address, String analystId, String expertiseArea) {
        super(personId, name, email, phone, address);
        this.analystId = analystId;
        this.expertiseArea = expertiseArea;
    }

    public String getAnalystId() {
        return analystId;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    public void analyzeThreat(String caseId) {
        System.out.println("Analyst " + getName() + " is analyzing case: " + caseId);
    }

    @Override
    public String getRole() {
        return "Analyst";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Analyst ID     : " + analystId);
        System.out.println("Expertise Area : " + expertiseArea);
        System.out.println("====================");
    }

    @Override
    public String toFileString() {
        return "ANALYST|" + super.toFileString() + "|" + analystId + "|" + expertiseArea;
    }

    public static Analyst fromFileString(String line) {
        String[] parts = line.split("\\|");
        String[] base = parts[1].split(",");
        return new Analyst(
                base[0], base[1], base[2], base[3], base[4],
                parts[2], parts[3]
        );
    }

    @Override
    public String toString() {
        return "[Analyst] " + getName() + " | Expertise: " + expertiseArea;
    }
}