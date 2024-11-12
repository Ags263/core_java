package ConstructorDemo;

import java.util.Scanner;

public class BankCustomer {
	
	 
		 static int acno;
		 static String atype;
		 static Double amount;
		 
		 
			 BankCustomer(int acno,String atype,Double amount){
			 
			 this.acno = acno;
			 this.atype = atype;
			 this.amount = amount;
			 
			 System.out.println("Account no is :"+acno);
			 System.out.println("Account type is :"+atype);
			 System.out.println("AMOUNT IS  is :"+amount);
			 }
	 

	public static void main(String[] args) {
		BankCustomer b1 =new BankCustomer(111, "ASHISH", 1000000.65);
		BankCustomer b2 =new BankCustomer(121, "ADARSH", 200000.65);
		BankCustomer b3 =new BankCustomer(131, "SAHIL",  424324.65);

	

		
	}
}