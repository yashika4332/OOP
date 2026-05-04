package Polymorphism_project;

public class Employee {
    private String name;
    private int Id;
    private double baseSalary;
    Employee(String name,int Id, double baseSalary){
        this.name = name;
        this.Id = Id;
        this.baseSalary = baseSalary;
    }

    public void setId(int id) {
        if(id <= 0){
            System.out.println("Id cannot be negative");
        }else{
        Id = id;
    }
    }

    public void setName(String name) {
        if(name.isEmpty()){
            System.out.println("name cannot be empty ");
        }else{
        this.name = name;
    }
    }

    public void setBaseSalary(double baseSalary) {
        if(baseSalary > 0){
        this.baseSalary = baseSalary;
    }else{
            System.out.println("The salary cannot be less than 0");
        }}

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    void calculateSalary(){
        System.out.println("  "+baseSalary);
    }
    void displayInfo(){
        System.out.println(getId());
        System.out.println(getName());
        System.out.println(getBaseSalary());
    }

    void raiseSalary(int percent){
        double raise = getBaseSalary() * percent / 100.0;
        System.out.println(getBaseSalary() + raise);

    }

    void raiseSalary(int percent , String reason){
        double raise = getBaseSalary() * percent / 100.0;
        System.out.println(" "+getBaseSalary() + raise+" "+reason);
    }

    void raiseSalary(double percent){
        double raise = getBaseSalary() * percent / 100.0;
        System.out.println(getBaseSalary() + raise);
    }

}
