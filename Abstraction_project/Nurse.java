package Abstraction_project;

public class Nurse extends Person{
   private String department;
   private double salary;

   Nurse(String name,int age,int Id,String department,double salary){
       super(name,age,Id);
       this.department = department;
       this.salary = salary;
   }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    void displayInfo(){
        System.out.println(getName());
        System.out.println(getAge());
        System.out.println(getId());
        System.out.println(getDepartment());
        System.out.println(getSalary());
    }

    void calculateSalary(){
       double totalSalary = getSalary() + (getSalary()*0.10);
        System.out.println(totalSalary);
    }
}
