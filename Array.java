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
	
	 // Step 1: Create a number array using the 'new' keyword
    int num[] = new int[5];  // Array of size 5
    
    // Step 2: Initialize the array with values
    num[0] = 10;
    num[1] = 20;
    num[2] = 30;
    num[3] = 40;
    num[4] = 50;

    // Step 3: Display the array elements
    System.out.println("Array elements are: ");
    for (int i = 0; i < 5; i++) {
        System.out.println(num[i]);
    }
    
    // Step 4: Find the largest element
    int largest = num[0];  // Initialize the largest variable with the first element

    for (int i = 1; i < num.length; i++) {
        if (num[i] > largest) {
            largest = num[i];  // Update largest if current element is larger
        }
    }

    // Step 5: Display the largest element
    System.out.println("The largest number is: " + largest);
}


	}
