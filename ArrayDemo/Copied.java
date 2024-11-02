package ArrayDemo;

import java.util.Arrays;

public class Copied {
    public static void main(String[] args) {

        int[][] originalArray = {
            {11, 22, 33},
            {41, 52, 63},
            {71, 82, 93}
        };

        int[][] copiedArray = new int[originalArray.length][];
        for (int i = 0; i < originalArray.length; i++) {
            copiedArray[i] = originalArray[i].clone();
        }

        System.out.println("Original Array:");
        printArray(originalArray);

        System.out.println("Copied Array:");
        printArray(copiedArray);
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }
}
