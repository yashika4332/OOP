package Polymorphism_project;

public class Manager extends FullTimeEmployee{
    private String department;
    Manager(String name,int Id, double baseSalary, double bonus,String department){
        super(name,Id,baseSalary,bonus);
        this.department = department;
    }
    void calculateSalary(){
        double extra = getBaseSalary() * 20 / 100.0;
        System.out.println(getBaseSalary() + getBonus() + extra);
    }
    void displayInfo(){
       super.displayInfo();
        System.out.println(" "+department);
    }



}
