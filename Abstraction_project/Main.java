package Abstraction_project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Doctor[] d = new Doctor[2];
        for(int i=0;i<2;i++){
            System.out.println("==DOCTORS==");
            System.out.println("Enter your Name");
            String name= sc.next();
            System.out.println("Enter Age");
            int age = sc.nextInt();
            System.out.println("Enter id");
            int id = sc.nextInt();
            System.out.println("Enter specialization");
            String specialization = sc.next();
            System.out.println("Enter salary");
            double salary = sc.nextDouble();
            d[i] = new Doctor(name,age,id,specialization,salary);

        }
        Patient[] p = new Patient[2];
        for(int i=0 ; i<2;i++){
            System.out.println("===Patients===");
            System.out.println("Enter your Name");
            String name= sc.next();
            System.out.println("Enter Age");
            int age = sc.nextInt();
            System.out.println("Enter id");
            int id = sc.nextInt();
            System.out.println("Enter disease");
            String disease = sc.next();
            System.out.println("Enter the Room Number");
            int roomNo = sc.nextInt();
            p[i] = new Patient(name,age,id,disease,roomNo);
        }
        System.out.println("===Nurse===");
        System.out.println("Enter your Name");
        String name= sc.next();
        System.out.println("Enter Age");
        int age = sc.nextInt();
        System.out.println("Enter id");
        int id = sc.nextInt();
        System.out.println("Enter the department");
        String department = sc.next();
        System.out.println("Enter the Salary");
        double Salary = sc.nextDouble();
        Nurse n = new Nurse(name,age,id,department,Salary);
        int choice;
        do{
            System.out.println("===---MENU---===");
            System.out.println("1. Display all staff");
            System.out.println("2. Display all patients");
            System.out.println("3. Doctor prescribe medicine");
            System.out.println("4. Calculate salaries");
            System.out.println("5. Exit");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    for(int i=0;i<2;i++){
                        System.out.println("==Doctore==");
                        d[i].displayInfo();
                    }
                    System.out.println("==Nurse==");
                    n.displayInfo();
                    break;
                case 2:
                    for(int i=0;i<2;i++){
                        p[i].displayInfo();
                    }
                    break;
                case 3:
                        d[0].prescribe("Panadol");
                        d[1].prescribe("Rizek",4,"Avoid acidy foods");
                        break;
                case 4:
                    for(int i =0 ;i<2;i++){
                        d[i].calculateSalary();
                    }
                    n.calculateSalary();
                    break;
                case 5:
                    System.out.println("Thankyou ! For Visiting");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(choice != 5);

    }
}
