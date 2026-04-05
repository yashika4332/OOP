public class GradeCalculator {
    public String calculateGrade(Student s) {
        int marks = s.getMarks();

        if (marks >= 80) {
            return "A";
        } else if (marks >= 60) {
            return "B";
        } else if (marks >= 40) {
            return "C";
        } else {
            return "D";
        }
    }

    public Student updateStudent(Student s, int newMarks) {
        s.setMarks(newMarks);
        return s;
    }
}
