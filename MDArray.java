package ArrayDemo;

import java.util.Scanner;

public class MDArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        
        // Prompting user for dimensions of the multi-dimensional array
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        // Declaring the multi-dimensional array
        int[][] marks = new int[rows][cols];

        // Taking user input to fill the array
        System.out.println("Enter the marks for each student:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter mark for student " + (i + 1) + ", subject " + (j + 1) + ": ");
                marks[i][j] = scanner.nextInt();
            }
        }

        // Displaying the multi-dimensional array
        System.out.println("\nThe marks entered are:");
        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                System.out.print(marks[i][j] + " ");
            }
            System.out.println();
        }

        // Closing the scanner
        scanner.close();
    }

	}
