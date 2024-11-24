package sort;

import java.util.*;

public class lab7q2 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }

        System.out.println("adding first 10 elements: " + stack);

        List<Integer> elementsToRemove = Arrays.asList(2, 5, 8, 10);

        stack.removeAll(elementsToRemove);

        System.out.println("elements after removed elements are : " + stack);
    }
}
