package LabProgram;

import java.util.Arrays;
import java.util.Collections;

public class SecondHighestofSubArray {

	    public static void main(String[] args) {
	        Integer[] arr = {2, 4, 5, 3, 6, 7, 9, 4, 5, 6};

	        Integer[] subArr = Arrays.copyOfRange(arr, 2, 7);

	        Arrays.sort(subArr, Collections.reverseOrder());

	        if (subArr.length >= 2) {
	            int secondHighest = subArr[1];
	            System.out.println("Second highest number: " + secondHighest);
	        } else {
	            System.out.println("Subarray does not have enough elements.");
	        }
	    }
	}
