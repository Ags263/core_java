package encapsulation;

import java.util.Scanner;

public class CourseList extends studentlist {
	private int cid;
	private String cname;
	

	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}
	


	@Override
	public String toString() {
		return "CourseList [cid=" + cid + ", cname=" + cname + "]";
	}


	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		CourseList A[]=new CourseList[1];
		for(int i=0;i<A.length;i++)
		{
			A[i]=new CourseList();
			//set the data
			System.out.println("Enter your course ID");
			A[i].setCid(sc.nextInt());
			System.out.println("Enter your course name");
			A[i].setCname(sc.next());
			System.out.println("Enter your Student name");
			A[i].setName(sc.next());
			System.out.println("Enter your Student id");
			A[i].setSid(sc.nextInt());
			System.out.println("Enter your Department");
			A[i].setDept(sc.next());
			//getdata
			
			System.out.println(A[i]+" ");
			
			
		}
		
		

	}

}
