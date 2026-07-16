package persons;

public abstract class Person {

    private String personId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Person(String personId, String name, String email, String phone, String address) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getPersonId() { return personId; }
    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPhone()    { return phone; }
    public String getAddress()  { return address; }

    public void setName(String name)       { this.name = name; }
    public void setEmail(String email)     { this.email = email; }
    public void setPhone(String phone)     { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    public abstract String getRole();

    public void displayInfo() {
        System.out.println("====================");
        System.out.println("ID      : " + personId);
        System.out.println("Name    : " + name);
        System.out.println("Role    : " + getRole());
        System.out.println("Email   : " + email);
        System.out.println("Phone   : " + phone);
        System.out.println("Address : " + address);
        System.out.println("====================");
    }

    public String toFileString() {
        return personId + "," + name + "," + email + "," + phone + "," + address;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name + " (ID: " + personId + ")";
    }
}