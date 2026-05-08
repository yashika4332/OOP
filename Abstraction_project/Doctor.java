package Abstraction_project;

public class Doctor extends Person{
    private String specialization;
    private double salary;
  Doctor(String name,int age,int Id,String specialization,double salary){
      super(name,age,Id);
      this.specialization = specialization;
      this.salary = salary;
  }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getSalary() {
        return salary;
    }

    void displayInfo(){
      System.out.println(getName());
      System.out.println(getId());
      System.out.println(getAge());
      System.out.println(getSpecialization());
      System.out.println(getSalary());

  }
    void prescribe(){
        System.out.println("prescribing medicine");
    }

    void prescribe(String medicine){
        System.out.println("Medicine prescribes is "+medicine);
    }

    void prescribe(String medicine,int days){
        System.out.println("Medicine prescribed is "+medicine+" for "+days+" days ");
    }

    void prescribe(String medicine,int days,String notes){
        System.out.println("Medicine prescribed is "+medicine+" for "+days+" days and the notes to follow "+notes);
    }

    void calculateSalary(){
      double bonus = getSalary() + (0.20 * getSalary());
        System.out.println(bonus);
    }

}
