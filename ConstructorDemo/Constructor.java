package ConstructorDemo;

import java.util.Scanner;

public class Constructor {
	
	Constructor()
	{
		
	}
		int id;
		String name;
		int age;
		double salary;
	
	 void enterValue(){
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter your id :");
		 id=sc.nextInt();
		 System.out.println("Enter your name :");
		 name=sc.next();
		 System.out.println("Enter your age :");
		 age=sc.nextInt();
		 System.out.println("Enter ypour salary:");
		 salary = sc.nextDouble();
		 
	 }
	 
	 void display()
	 {
		 System.out.println("id:"+id);
		 System.out.println("name:"+name);
		 System.out.println("age:"+age);
		 System.out.println("salary:"+salary);


	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Constructor C1 = new Constructor();
		
		C1.enterValue();
		C1.display();

	}

}
