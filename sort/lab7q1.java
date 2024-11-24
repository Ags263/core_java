package sort;

import java.util.*;

public class lab7q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Set<Integer> set=new LinkedHashSet<>();
set.add(1);
set.add(1);
set.add(11);
set.add(21);
set.add(31);
set.add(15);
Iterator<Integer> i=set.iterator();
System.out.println(set);
Set<Integer> set1=new LinkedHashSet<>();

set1.add(156);
System.out.println(set1);
set.addAll(set1);
System.out.println(set);

	}
	

}