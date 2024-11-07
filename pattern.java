package LoopDemo;

public class pattern {

	public static void main(String[] args) {
	       int rows = 5;  // number of rows to print

	        for (int i = rows; i > 0; i--) {  // Outer loop for each row
	            for (int j = 0; j < i; j++) {  // Inner loop for printing stars
	                System.out.print("* ");
	            }
	            System.out.println();  // Move to the next line after each row
	        }
	    }
	}         