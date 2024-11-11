import java.io.*;
import java.util.*;

public class Main {
    // Maximum number of students
    private static final int Max_Student = 100;

    // Student data store in the student_data file
    private static final String Data_File = "Student_Data.txt";

    // Store student names and ID in array
    private static String[] studentNames = new String[Max_Student];
    private static int[] studentIDs = new int[Max_Student];

    // Count of registered students
    private static int studentCount = 0;

    // Scanner object for user inputs
    private static Scanner scanner = new Scanner(System.in);



    // Main method
    public static void main(String[] args) {
        loadStudentData(); // Load existing data from file if there is a file

        int choice = 0;
        // Menu loop
        while (choice != 8) {
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
                    storeStudentDataToFile(); // Store student data into a filr
                    break;
                case 6:
                    loadStudentData(); // Load student data from the file
                    break;
                case 7:
                    sortStudentsByName(); // Sort students by name
                    break;
                case 8:
                    System.out.println("Exiting..."); // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 8."); // Invalid choice
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
        System.out.println("8. Exit");
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
            int id = scanner.nextInt();
            scanner.nextLine(); // Newline character

            // Ask the new student name
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            studentIDs[studentCount] = id;
            studentNames[studentCount] = name;
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
        int idToDelete = scanner.nextInt();

        // Find the student by ID and delete if found
        int index = -1;
        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i] == idToDelete) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Shift students array to remove the deleted student
            for (int i = index; i < studentCount - 1; i++) {
                studentIDs[i] = studentIDs[i + 1];
                studentNames[i] = studentNames[i + 1];
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
        int idToFind = scanner.nextInt();

        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i] == idToFind) {
                System.out.println("Student found: " + studentNames[i]);
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
                writer.println(studentIDs[i] + "," + studentNames[i]);
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
                studentIDs[studentCount] = Integer.parseInt(parts[0]);
                studentNames[studentCount] = parts[1];
                studentCount++;
            }
            System.out.println("Student data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found.");
        }
    }




    // Sort students bn name
    private static void sortStudentsByName() {
        String tempName;
        int tempID;
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = i + 1; j < studentCount; j++) {
                if (studentNames[i].compareTo(studentNames[j]) > 0) {
                    // Swap names
                    tempName = studentNames[i];
                    studentNames[i] = studentNames[j];
                    studentNames[j] = tempName;

                    // Swap IDs
                    tempID = studentIDs[i];
                    studentIDs[i] = studentIDs[j];
                    studentIDs[j] = tempID;
                }
            }
        }
        System.out.println("Students sorted by name.");
        displayStudents();
    }




    // Display the list of students
    private static void displayStudents() {
        System.out.println("\n----- List of Students -----");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("ID: " + studentIDs[i] + ", Name: " + studentNames[i]);
        }
    }
}
