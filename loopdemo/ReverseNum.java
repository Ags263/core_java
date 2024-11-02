package loopdemo;

import java.util.Scanner;

public class ReverseNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int num=in.nextInt();
		while(num!=0)
		{
			int r=num%10;//567%10=7,56%10=6,5%10=5
			System.out.print(r);//7,6,5
			num=num/10;//567/10=56,56/10=5
		}

	}

}
