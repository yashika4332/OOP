package Inheritance_project;

public class Product {
    private String Name;
    private double Price;
    Product(String Name,double Price){
        this.Name = Name;
        this.Price = Price;
    }

    public void setName(String Name) {
        if(!Name.isEmpty()){
        this.Name = Name;
        }else{
            System.out.println("Not Valid! Name cannot be Empty");
        }
    }

    public String getName() {
        return Name;
    }

    public void setPrice(double Price) {
        if(Price > 0){
        this.Price = Price;
        }else{
            System.out.println("Price cannot be less than 0");
        }
    }

    public double getPrice() {
        return Price;
    }

    void displayInfo(){
        System.out.println(getName());
        System.out.println(getPrice());
    }

}
