package CoreJava;

import java.util.Scanner;

class Student {
    String name;
    int sub1, sub2, sub3, sub4, sub5;

    // Default constructor
    Student() {
    }

    // Parameterized constructor
    Student(String name, int sub1, int sub2, int sub3, int sub4, int sub5) {
        this.name = name;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
        this.sub5 = sub5;
    }

    // Method to calculate total marks
    int calculateTotal() {
        return sub1 + sub2 + sub3 + sub4 + sub5;
    }

    // Method to calculate average marks
    double calculateAverage() {
        return calculateTotal() / 5.0; // Using 5.0 to ensure double division
    }

    // Method to determine grade based on average
    String determineGrade() {
        double average = calculateAverage();
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
}

public class StudentmarksAssignment {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for student name
        System.out.println("Enter student name:");
        String name = sc.nextLine();

        // Input for subject marks
        System.out.println("Enter marks for 5 subjects:");
        int[] marks = new int[5];
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter mark for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        // Create a Student object
        Student student = new Student(name, marks[0], marks[1], marks[2], marks[3], marks[4]);

        // Calculate total and average
        int total = student.calculateTotal();
        double average = student.calculateAverage();
        String grade = student.determineGrade();

        // Display results
        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + average);
        System.out.println("Grade: " + grade);
        
        sc.close(); // Close the scanner to avoid resource leaks
    }
}