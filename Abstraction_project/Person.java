package Abstraction_project;

abstract class Person {
    private String name;
    private int age;
    private int Id;

    Person(String name,int age,int Id){
        this.name = name;
        this.age = age;
        this.Id = Id;
    }

    public void setName(String name) {
        if(name.isEmpty()){
            System.out.println("Name can't be empty");
        }
        else{
        this.name = name;
    }
    }

    public void setAge(int age) {
        if(age <= 0){
            System .out.println("Age cannot be negative");

    }
        else{
            this.age = age;
        }
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return Id;
    }
    abstract void displayInfo();
    void breathe(){
        System.out.println("breathing");
    }
}

