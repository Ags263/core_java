package sample;

public class arithimetic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 16;
        int b = 4;

        // Addition
        int add = a + b;  // 10 + 3 = 13
        System.out.println("Sum: " + add);

        // Subtraction
        int sub = a - b;  // 10 - 3 = 7
        System.out.println("Difference: " + sub);

        // Multiplication
        int multi = a * b;  // 10 * 3 = 30
        System.out.println("Product: " + multi);

        // Division
        int divide = a / b;  // 10 / 3 = 3 (integer division)
        System.out.println("integer divison: " + divide);

        double divisionFloat = 9.0 / 3.0;  // floating-point division = 3.333...
        System.out.println("Quotient (floating-point division): " + divisionFloat);

        // Modulus
        int remainder = a % b;  // 10 % 3 = 1
        System.out.println("Remainder: " + remainder);

	}

}
