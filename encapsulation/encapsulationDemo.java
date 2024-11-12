package encapsulation;

/*
 * Encapsulation:provide control to the data
 * wrapping up of data and method into a single unit
 * we can make readable or writable
 * it has private data var/members getter and setter method
 */

public class encapsulationDemo {
	//private data members
	private String empname;
	//getter method-read the data
	/*public String getEmpname()
	{
		return empname;
	}
	//setter method-write the data
	public void setEmpname(String empname)
	{
		this.empname=empname;
	}*/
	public String getEmpname() {
		return empname;
	}


	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public static void main(String[] args) {
		 encapsulationDemo ed=new  encapsulationDemo();
		 //setting the value in empname
		 ed.setEmpname("David"); 
		 //getting value using getter method
		 System.out.println(ed.getEmpname());

	}


	

}
