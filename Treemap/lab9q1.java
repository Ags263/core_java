package Treemap;

import java.util.TreeMap;

public class lab9q1 {
    private static final Object Two = null;

	public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("One", 1);
        map.put("Three", 3);
        map.put("Two", 2);

     // Removing an entry 
        Integer removedValue = map.remove("Two"); 
        System.out.println("Removed value = " + removedValue); 
        
        System.out.println("Updated TreeMap: " +map);
    }
}
