package encapsulation;

import java.util.Scanner;

public class HardwarePurchase  extends Hardware{
	public double v;
	

	public double getV() {
		return v;
	}


	public void setV(double v) {
		this.v = v;
	}


	public static void main(String[] args) {
		int ch;
		System.out.println("!!!!!Welcome to Hardware shop!!!!!");
		System.out.println("----------------------------");
		System.out.println("Hardware we have:");
		
		System.out.println("1.CPU");
		System.out.println("2.Monitor");
		System.out.println("3.Speaker");
		System.out.println("4.Keyboard");
		System.out.println("5.Scanner");
		System.out.println("Which product you want to purchase???");
		Scanner sc=new Scanner(System.in);
		ch=sc.nextInt();
		HardwarePurchase HP=new HardwarePurchase();
		switch(ch)
		{
		case 1:
		{
			HP.setName("CPU");
			System.out.println(HP.getName());
			HP.setDesc("A central processing unit (CPU) is the main component of a computer that carries out instructions and controls other parts of the machine:");
			System.out.println(HP.getDesc());
			HP.setPrice(20000);
			System.out.println(HP.getPrice());
			System.out.println("How many quantity you want");
			HP.setQty(sc.nextInt());
			
			HP.setV(HP.getPrice()*HP.getQty());
			System.out.println("Total amount you have to pay:"+HP.getV());
			break;
		}
		case 2:
		{
			HP.setName("Monitor");
			System.out.println(HP.getName());
			HP.setDesc("A A monitor is a display that shows all the working and files of the system is a parts of the machine:");
			System.out.println(HP.getDesc());
			HP.setPrice(10000);
			System.out.println(HP.getPrice());
			System.out.println("How many quantity you want");
			HP.setQty(sc.nextInt());
			
			HP.setV(HP.getPrice()*HP.getQty());
			System.out.println("Total amount you have to pay:"+HP.getV());
			break;
		}
		
		default:
		{
			System.out.println("Sorry the product is not available");
			break;
		}
		
		}
		System.out.println("Thank you for Purchasing");

	}

}
