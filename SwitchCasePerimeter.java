package LoopDemo;

import java.util.Scanner;

/*Write a class with the name Perimeter using 
 * (Switch case) that computes the 
 * perimeter of a square, a rectangle and a 
 * circle.*/

class Perimeter {
    int side, length, width, radius;

    // Default constructor
    Perimeter() {}

    // Constructor with parameters
    Perimeter(int side, int length, int width, int radius) {
        this.side = side;
        this.length = length;
        this.width = width;
        this.radius = radius;
    }

     int SquarePerimeter() {
        return 4 * side;
    }

     int RectanglePerimeter() {
        return 2 * (length + width);
    }

     double CirclePerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class SwitchCasePerimeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose shape (1: Square, 2: Rectangle, 3: Circle): ");
        int choice = sc.nextInt();

        Perimeter perimeter = new Perimeter();

        switch (choice) {
            case 1:
                System.out.println("Enter side length of the square: ");
                perimeter.side = sc.nextInt();
                System.out.println("Perimeter of square: " + perimeter.SquarePerimeter());
                break;

            case 2:
                System.out.println("Enter length of the rectangle: ");
                perimeter.length = sc.nextInt();
                System.out.println("Enter width of the rectangle: ");
                perimeter.width = sc.nextInt();
                System.out.println("Perimeter of rectangle: " + perimeter.RectanglePerimeter());
                break;

            case 3:
                System.out.println("Enter radius of the circle: ");
                perimeter.radius = sc.nextInt();
                System.out.println("Perimeter of circle: " + perimeter.CirclePerimeter());
                break;

            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
