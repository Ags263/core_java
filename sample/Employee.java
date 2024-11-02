package variablesDemo;

class Employee {
    int id;
    String name;
    int age;
    double salary;

    // Constructor to initialize Employee details
    Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Method to display employee data
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Salary: " + salary);
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        // Creating an array of 10 Employee objects
        Employee[] employees = new Employee[10];
        
        // Initializing each employee with sample data
        employees[0] = new Employee(1, "John", 28, 50000);
        employees[1] = new Employee(2, "Emma", 32, 60000);
        employees[2] = new Employee(3, "Sophia", 29, 55000);
        employees[3] = new Employee(4, "Michael", 35, 70000);
        employees[4] = new Employee(5, "David", 41, 65000);
        employees[5] = new Employee(6, "Sarah", 26, 48000);
        employees[6] = new Employee(7, "James", 33, 62000);
        employees[7] = new Employee(8, "Linda", 27, 51000);
        employees[8] = new Employee(9, "Robert", 38, 72000);
        employees[9] = new Employee(10, "Patricia", 31, 58000);

        // Displaying all employee data
        for (Employee emp : employees) {
            emp.display();
        }
    }
}
