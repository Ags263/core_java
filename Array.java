package ArrayDemo;

public class Array {

	public static void main(String[] args) {
		
		// Static way
		String animal[]= {"lion","sher","chitah","Monkey","hatti","rhino","dinmo","penguin","bear","owl"};
		System.out.println(animal[1]);
		
	System.out.println(animal);
	
	for(int  i=0;i<animal.length;i++)
	{
		System.out.println(animal[i]);
	}
	
	 int num[] = new int[500];
     
     num[0] = 100;
     num[1] = 200;
     num[2] = 300;
     num[3] = 400;
     num[4] = 500;

     // Display the elements of the array
     System.out.println("Array elements:");
     for (int i = 0; i < 500; i++) {
         System.out.println(num[i]);
     }

     // Find and display the largest element
     int largest = findLargest(num);
     System.out.println("The largest element in the array is: " + largest);
 }

 // Method to find the largest element in an array
 public static int findLargest(int[] arr) {
     int max = arr[0]; // Assume the first element is the largest

     // Iterate through the array to find the maximum value
     for (int i = 1; i < arr.length; i++) {
         if (arr[i] > max) {
             max = arr[i]; // Update max if current element is larger
         }
     }
     return max; // Return the largest element
 }


	}