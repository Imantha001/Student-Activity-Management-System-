import java.io.*;
import java.util.*;

public class Main {
    // Maximum number of students
    private static final int Max_Student = 100;

    // Student data store in the student_data file
    private static final String Data_File = "Student_Data.txt";

    // Store student objects in array
    private static Student[] students = new Student[Max_Student];

    // Count of registered students
    private static int studentCount = 0;

    // Scanner object for user inputs
    private static Scanner scanner = new Scanner(System.in);



    // Main method
    public static void main(String[] args) {
        loadStudentData(); // Load existing data from file if there is a file

        int choice = 0;
        // Menu loop
        while (choice != 9) {
            displayMenu(); // Display menu option
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Newline character after nextInt()

            // Switch case for user choices
            switch (choice) {
                case 1:
                    checkAvailableSeats(); // Check available seats
                    break;
                case 2:
                    registerStudent(); // register a new student
                    break;
                case 3:
                    deleteStudent(); // Delete a student
                    break;
                case 4:
                    findStudentByID(); // find a student by ID
                    break;
                case 5:
                    storeStudentDataToFile(); // Store student data into a file
                    break;
                case 6:
                    loadStudentData(); // Load student data from the file
                    break;
                case 7:
                    sortStudentsByName(); // Sort students by name
                    break;
                case 8:
                    addStudentResults(); // Add student results
                    break;
                case 9:
                    System.out.println("Exiting..."); // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 9.");
            }
        }

        scanner.close(); // Close the scanner
    }




    // Display menu option to user
    private static void displayMenu() {
        System.out.println("\n----- Student Management System Menu -----");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student (with ID)");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (with student ID)");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file to the system");
        System.out.println("7. View the list of students based on their names");
        System.out.println("8. Add student results");
        System.out.println("9. Exit");
    }




    // Check available seats
    private static void checkAvailableSeats() {
        int availableSeats = Max_Student - studentCount;
        System.out.println("Available seats: " + availableSeats);
    }




    // Register a new student
    private static void registerStudent() {
        if (studentCount < Max_Student) {
            // Ask the new student ID
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();

            // Ask the new student name
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            // Create a new Student object and add to the array
            students[studentCount] = new Student(id, name, 0, 0, 0);
            studentCount++;

            System.out.println("Student registered successfully.");
        } else {
            // If 100 students registered display this message
            System.out.println("Student registration failed. Maximum capacity reached.");
        }
    }




    // Delete a student
    private static void deleteStudent() {
        // Ask student ID to delete
        System.out.print("Enter student ID to delete: ");
        String idToDelete = scanner.nextLine();

        // Find the student by ID and delete if found
        int index = -1;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID().equals(idToDelete)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Shift students array to remove the deleted student
            for (int i = index; i < studentCount - 1; i++) {
                students[i] = students[i + 1];
            }
            studentCount--;
            System.out.println("Student deleted successfully.");
        } else {
            // If the entered ID is not in the list print this message
            System.out.println("Student not found.");
        }
    }




    // Find a student by ID
    private static void findStudentByID() {
        // Ask student ID to find
        System.out.print("Enter student ID to find: ");
        String idToFind = scanner.nextLine();

        // Search for the student by ID
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID().equals(idToFind)) {
                System.out.println("Student found: " + students[i].getName());
                found = true;
                break;
            }
        }

        if (!found) {
            // If the entered ID is not in the list print this message
            System.out.println("Student not found.");
        }
    }




    // Store students data to a file
    private static void storeStudentDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Data_File))) {
            // Write each student's data to the file
            for (int i = 0; i < studentCount; i++) {
                writer.print(students[i].getStudentID() + "," + students[i].getName());
                // Append module marks
                for (Module module : students[i].getModules()) {
                    writer.print("," + module.getMarks());
                }
                writer.println();
            }
            System.out.println("Student data stored to file.");
        } catch (IOException e) {
            System.out.println("Error storing data to file: " + e.getMessage());
        }
    }





    // Load student data from the file
    private static void loadStudentData() {
        try (Scanner fileScanner = new Scanner(new File(Data_File))) {
            studentCount = 0;
            // Read each line of data from the file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                int marks1 = Integer.parseInt(parts[2]);
                int marks2 = Integer.parseInt(parts[3]);
                int marks3 = Integer.parseInt(parts[4]);
                // Create a new Student object and add to the array
                students[studentCount] = new Student(id, name, marks1, marks2, marks3);
                studentCount++;
            }
            System.out.println("Student data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found.");
        }
    }





    // Sort students by name
    private static void sortStudentsByName() {
        Arrays.sort(students, 0, studentCount, Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name.");
        displayStudents(); // Display sorted list
    }




    // Add student results
    private static void addStudentResults() {
        System.out.print("Enter student ID to add results: ");
        String id = scanner.nextLine();

        // Search for the student by ID and add results if found
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID().equals(id)) {
                System.out.print("Enter marks for Module 1: ");
                int marks1 = scanner.nextInt();
                System.out.print("Enter marks for Module 2: ");
                int marks2 = scanner.nextInt();
                System.out.print("Enter marks for Module 3: ");
                int marks3 = scanner.nextInt();

                // Update student with new results
                students[i] = new Student(students[i].getStudentID(), students[i].getName(), marks1, marks2, marks3);
                System.out.println("Results added successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }





    // Display the list of students
    private static void displayStudents() {
        System.out.println("\n----- List of Students -----");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("ID: " + students[i].getStudentID() + ", Name: " + students[i].getName());
            System.out.println("  Marks: ");
            System.out.println("    Module 1: " + students[i].getModules()[0].getMarks());
            System.out.println("    Module 2: " + students[i].getModules()[1].getMarks());
            System.out.println("    Module 3: " + students[i].getModules()[2].getMarks());
            System.out.println("  Average Marks: " + students[i].getAverageMarks() + ", Overall Grade: " + students[i].getOverallGrade());
        }
    }
}
