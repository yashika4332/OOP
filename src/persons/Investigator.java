package persons;

public class Investigator extends Person {

    private String badgeNumber;
    private String department;
    private String specialization;
    private int activeCases;

    public Investigator(String personId, String name, String email, String phone,
                        String address, String badgeNumber, String department, String specialization) {
        super(personId, name, email, phone, address);
        this.badgeNumber     = badgeNumber;
        this.department      = department;
        this.specialization  = specialization;
        this.activeCases     = 0;
    }

    public String getBadgeNumber()    { return badgeNumber; }
    public String getDepartment()     { return department; }
    public String getSpecialization() { return specialization; }
    public int getActiveCases()       { return activeCases; }

    public void setDepartment(String department)         { this.department = department; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public void assignCase()  { activeCases++; }
    public void closeCase()   { if (activeCases > 0) activeCases--; }

    @Override
    public String getRole() {
        return "Investigator";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Badge No      : " + badgeNumber);
        System.out.println("Department    : " + department);
        System.out.println("Specialization: " + specialization);
        System.out.println("Active Cases  : " + activeCases);
        System.out.println("====================");
    }

    @Override
    public String toFileString() {
        return "INVESTIGATOR|" + super.toFileString() + "|" +
                badgeNumber + "|" + department + "|" + specialization + "|" + activeCases;
    }

    public static Investigator fromFileString(String line) {
        String[] parts = line.split("\\|");

        String[] base = parts[1].split(",");
        return new Investigator(
                base[0], base[1], base[2], base[3], base[4],
                parts[2], parts[3], parts[4]
        );
    }

    @Override
    public String toString() {
        return "[Investigator] " + getName() + " | Badge: " + badgeNumber + " | Active Cases: " + activeCases;
    }
}