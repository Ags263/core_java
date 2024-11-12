package ConstructorDemo;

  class ParameterisedConstructor {
	
	String Name;
	
	ParameterisedConstructor(String Name) {
	
		this.Name= Name;
		
		System.out.println(Name+" "+ "is the best");
	}
	
	

	public static void main(String[] args) {
		ParameterisedConstructor L1 =new ParameterisedConstructor("Ashish");
		ParameterisedConstructor L2 =new ParameterisedConstructor("Aryan");
		ParameterisedConstructor L3 =new ParameterisedConstructor("adarsh");

	}
}
