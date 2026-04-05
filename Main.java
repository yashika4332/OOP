
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Yashika", 75);
        GradeCalculator gc = new GradeCalculator();

        System.out.println(" student details:");
        student1.displayDetails();
        System.out.println("Grade: " + gc.calculateGrade(student1));
        System.out.println("\n Updated Marks are");

        gc.updateStudent(student1, 95);

        student1.displayDetails();
        System.out.println("Updated Grade is:" + gc.calculateGrade(student1));
    }
}