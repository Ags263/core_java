package ArrayDemo;

public class StringBulderDemo {

    public static void main(String[] args) {
        // StringBuilder, StringBuffer
        StringBuilder str = new StringBuilder("Welcome to my World");
        System.out.println("Original StringBuilder: " + str);
        
        // Insert a character at index 7
        str.insert(7, 's');
        System.out.println("After insert: " + str);
        
        // Append a string
        str.append(" Ashish ");
        System.out.println("After append: " + str);
        
        // Delete characters from index 16 to 18
        str.delete(16, 18);
        System.out.println("After delete: " + str);
        
        // Replace a substring from index 9 to 10 with "tomy"
        str.replace(9, 10, "tomy");
        System.out.println("After replace: " + str);
        
        // Create a String and perform split operation and change String to character array
        StringBuffer str1 = new StringBuffer("My Java Course");
        System.out.println("\nOriginal StringBuffer: " + str1);
        
        // Replace characters from index 3 to 7 with "anudip"
        str1.replace(3, 7, "anudip");
        System.out.println("After replace: " + str1);
        
        // Split the String into an array
        String strToSplit = "My Java Course";
        String[] words = strToSplit.split(" ");
        System.out.println("\nSplit Result:");
        for (String word : words) {
            System.out.println(word);
        }
        
        // Convert the String to a character array
        char[] charArray = strToSplit.toCharArray();
        System.out.println("\nCharacter Array:");
        for (char c : charArray) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
