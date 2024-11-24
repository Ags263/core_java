package Treemap;

import java.util.TreeMap;
import java.util.Map;

public class lab9q2 {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("One", 1);
        map.put("Three", 3);
        map.put("Two", 2);

        System.out.println("Using entrySet():");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("\nUsing keySet():");
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }

        System.out.println("\nUsing values():");
        for (Integer value : map.values()) {
            System.out.println(value);
        }

        System.out.println("\nUsing forEach():");
        map.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }
}
