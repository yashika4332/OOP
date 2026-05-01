package Inheritance_project;

import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PhysicalProduct[] p1 = new PhysicalProduct[2];
        for(int i=0;i<2;i++){
            System.out.println("Enter Name");
            String Name = sc.next();
            System.out.println("Enter Price");
            double price = sc.nextDouble();
            System.out.println("Enter quantity");
            int quantity = sc.nextInt();
            System.out.println("Enter Weight");
            double weight = sc.nextDouble();
            p1[i] = new PhysicalProduct(Name,price,quantity,weight);
        }
        System.out.println("Enter name");
        String Name = sc.next();
        System.out.println("Enter Price");
        double Price = sc.nextDouble();
        System.out.println("Enter File Size");
        double fileSize = sc.nextDouble();
        System.out.println("Enter download link");
        String downloadLink = sc.next();
        DigitalProduct d = new DigitalProduct(Name,Price,fileSize,downloadLink);
        int choice =0;
        int amount;
        int index;
        do{
            System.out.println("----Menu----");
            System.out.println("1.Sell Physical Product");
            System.out.println("2.Restock Physical Product ");
            System.out.println("3. Download digital Product");
            System.out.println("4. Display all products");
            System.out.println("5. Exit");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the quantity you want to sell");
                    amount = sc.nextInt();
                    System.out.println("Enter the index of object");
                    index = sc.nextInt();
                    p1[index].sell(amount);
                    break;
                case 2:
                    System.out.println("Enter the amount to restock");
                    amount = sc.nextInt();
                    System.out.println("Enter the index of object");
                    index = sc.nextInt();
                    p1[index].restock(amount);
                    break;
                case 3:
                    d.download();
                    break;
                case 4:
                   p1[0].displayInfo();
                   p1[1].displayInfo();
                   d.displayInfo();
                   break;
                case 5:
                    System.out.println("Thankyou For Visiting !");
                    break;
                default:
                    System.out.println("Not Valid!");

            }

        }while(choice != 5);

    }
}
