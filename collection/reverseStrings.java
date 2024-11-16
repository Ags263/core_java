package collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class reverseStrings {
    public static void main(String[] args) {

    	List<String> stringList = new ArrayList<>();
        stringList.add("Akash");
        stringList.add("Bhavesh");
        stringList.add("Chirag");
        stringList.add("Devin");
        stringList.add("Esha");


        System.out.println("Original : " + stringList);

        Collections.reverse(stringList);

        System.out.println("Reversed : " + stringList);
    }
}
