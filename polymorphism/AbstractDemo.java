package polymorphism;

//abstract class
abstract class Animal
{
	//abstract method
	abstract void eat();
	//normal method
	void sound()
	{
		System.out.println("animal will Make a sound");
	}
}
class Tiger extends Animal
{

	@Override
	void eat() {
		
		System.out.println("Tiger will eat Flesh of animal");
	}
	
}
class Elephant extends Animal
{

	@Override
	void eat() {
		
		System.out.println("Elephant will eat Suganecane");
	}
	
}
public class AbstractDemo {

	public static void main(String[] args) {
		//Animal a=new Animal();we cant create an object of abstarct class
		Tiger t=new Tiger();
		t.eat();
		t.sound();
		 Elephant e=new  Elephant();
		 e.eat();
		 e.sound();
		
		

	}

}
