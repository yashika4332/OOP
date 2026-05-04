package Polymorphism_project;

public class FullTimeEmployee extends Employee{
     private double bonus;
     FullTimeEmployee(String name,int Id, double baseSalary, double bonus){
         super(name,Id,  baseSalary);
         this.bonus = bonus;

     }
    void calculateSalary(){
        System.out.println("  "+(getBaseSalary()+bonus));
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }
}
