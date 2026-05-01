package Inheritance_project;

public class PhysicalProduct extends Product{
    private int Quantity;
    private double Weight;

    PhysicalProduct(String Name,double Price,int Quantity,double Weight){
        super(Name, Price);
        this.Quantity = Quantity;
        this.Weight = Weight;
    }

    public void setQuantity(int quantity) {
        if(quantity > 0){
        this.Quantity = quantity;
        }else{
            System.out.println("Quantity cannot be less than 0");
        }

    }

    public int getQuantity() {
        return Quantity;
    }

    public void setWeight(double weight) {
        if(weight > 0){
       this.Weight = weight;
        }else{
            System.out.println("Weight cannot be less than 0");
        }
    }

    public double getWeight() {
        return Weight;
    }

    void sell(int amount){
        if(amount <= Quantity){
            Quantity -= amount;
            System.out.println("Sold successfully");
        }else{
            System.out.println("Out Of Stock !");
        }
    }

    void restock(int amount){
            Quantity += amount;
        System.out.println("Restocked Succesfully!");
    }

    void displayInfo(){
        super.displayInfo();
        System.out.println(getQuantity());
        System.out.println(getWeight());
    }



}
