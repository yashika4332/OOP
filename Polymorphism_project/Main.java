package Polymorphism_project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Employee e1 = new Employee("Yashika", 20, 90000);
        Employee e2 = new FullTimeEmployee("Nansi", 80, 102000, 50000);
        Employee e3 = new PartTimeEmployee("Yash", 35, 100000, 30, 1000);
        Employee e4 = new Manager("shaikh", 99, 90999, 8000, "Manager");

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---Employee Details----");
            System.out.println("1.Display all Employee");
            System.out.println("2.Calculate all salaries");
            System.out.println("3.Give raise to Employee");
            System.out.println("4.Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("----Info of all Employee");
                    e1.displayInfo();
                    e2.displayInfo();
                    e3.displayInfo();
                    e4.displayInfo();
                    break;
                case 2:
                    System.out.println("----Salaries of all Employees-----");
                    e1.calculateSalary();
                    e2.calculateSalary();
                    e3.calculateSalary();
                    e4.calculateSalary();
                    break;
                case 3:
                    System.out.println("----Raise----");
                    e1.raiseSalary(30);
                    e2.raiseSalary(45.5);
                    e3.raiseSalary(30, "Handled the client well");
                    e4.raiseSalary(20);
                    break;
                case 4:
                    System.out.println("Thankyou For Visiting!");
                    break;
                default:
                    System.out.println("Invalid !");

            }
        } while (choice != 4);

    }
}