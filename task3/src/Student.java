public class Student {
    // Private fields to store student ID, name, and an array of Module objects
    private String studentID;
    private String name;
    private Module[] modules;

    // Constructor to initialize the student with ID, name, and marks for three modules
    public Student(String studentID, String name, int marks1, int marks2, int marks3) {
        this.studentID = studentID;
        this.name = name;
        this.modules = new Module[3];
        this.modules[0] = new Module(marks1);
        this.modules[1] = new Module(marks2);
        this.modules[2] = new Module(marks3);
    }

    // Getter  to retrieve the student ID
    public String getStudentID() {
        return studentID;
    }

    // Getter  to retrieve the student name
    public String getName() {
        return name;
    }

    // Getter  to retrieve the array of modules
    public Module[] getModules() {
        return modules;
    }


    // Calculate and return the average marks of the student
    public double getAverageMarks() {
        int total = 0;
        for (Module module : modules) {
            total += module.getMarks();
        }
        return total / 3.0;
    }


    // Overall grade based on average marks
    public String getOverallGrade() {
        double average = getAverageMarks();
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}