package ArrayDemo;

public class StringBulderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// StringBuilder,StringBuffer
		        StringBuilder str = new StringBuilder("Welcome to my World");
		        System.out.println(str);
				str.insert(7, 's');
				System.out.println(str);
				str.append(" Ashish ");
				System.out.println(str);
				str.delete(16, 18);
				System.out.println(str);
				str.replace(9, 10, "tomy");
				System.out.println(str);
				

	}

}
