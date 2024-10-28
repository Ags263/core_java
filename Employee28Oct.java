package ArrayDemo;


	class Employee {
	    String name;
	    int age;
	    double salary;

	    // Constructor to initialize Employee details
	    Employee(int id, String name, int age, double salary) {
	        this.name = name;
	        this.age = age;
	        this.salary = salary;
	    }

	    // Method to display employee data
	    public void display() {
	        System.out.println( " Name: " + name + ", Age: " + age + ", Salary: " + salary);
	    }
	}

	public class Employee28Oct {
	    public static void main(String[] args) {
	        // Creating an array of 10 Employee objects
	        Employee[] employees = new Employee[10];
	        
	        // Initializing each employee with sample data
	        employees[0] = new Employee(1, "Chirag", 28, 50000);
	        employees[1] = new Employee(2, "Dhruv", 32, 60000);
	        employees[2] = new Employee(3, "Adhi", 29, 55000);
	        employees[3] = new Employee(4, "Ashish", 35, 70000);
	        employees[4] = new Employee(5, "Abhisek", 41, 65000);
	
	        // Displaying all employee data
	        for (Employee emp : employees) {
	            emp.display();
	        }
	    }
	}

	