package LabProgram;


/*. Create one superclass HillStations and three subclasses Manali, Mussoorie, Gulmarg. 
 * Subclasses extend the superclass and override its location() and famousFor() method. 
 * i.call the location() and famousFor() method by the Parent class’, 
 * i.e. Hillstations class. As it refers to the base class object and the base class method overrides the 
 * superclass method; the base class method is invoked at runtime. 
 * ii.call the location() and famousFor() method by the all subclass’,and print accordingly.
 * 
 */
class HillStations {
    String name;

    void location() {
        System.out.println("I am in India");
    }

    void famousFor() {
        System.out.println("Famous for natural beauty");
    }

    void display() {
        location();
        famousFor();
    }
}
class Manali extends HillStations
//override
{
	void location()
	{
		

		System.out.println("I am in Manali");
	}
	void famousfor()
	{
		name="Winter";
		System.out.println("I am:"+name);
	}
}
class Mussoorie extends HillStations
{
	void location()
	{
	
		System.out.println("I am in Massoori");
	}
	void famousfor()
	{
		name="Green foresat ";
		
		System.out.println("famous for tea:"+name);
	}
}
class Gulmarg extends HillStations

{
	void location()
	{
		

		System.out.println("I am in gukmarf");
	}
	
	void famousfor()

	{
		name="Mountain";
		System.out.println("Famouds for :"+name);
	}
}
public class hillStation {

	public static void main(String[] args) {
	Manali l=new Manali();
	l.display();
	Mussoorie m=new Mussoorie();
	m.display();
	Gulmarg g=new Gulmarg();
	g.display();
	}
}