package Polymorphism_project;
//3. PartTimeEmployee extends Employee
//   → private: hoursWorked, hourlyRate
//   → constructor using super()
//        → override calculateSalary()
//      → prints baseSalary + (hoursWorked * hourlyRate)
public class PartTimeEmployee extends Employee{

    private double hoursWorked;
    private double hourlyRate;

    PartTimeEmployee(String name,int Id, double baseSalary,double hoursWorked,double hourlyRate){
        super(name,Id,baseSalary);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    void calculateSalary(){
        System.out.println(" "+(getBaseSalary()+(hoursWorked * hourlyRate)));
    }


}
