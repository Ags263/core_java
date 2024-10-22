package ArrayDemo;

public class StringDemo {

	public static void main(String[] args) {

		// declare String in literal way
				String str1=" Lipi And";
				String str2="Ashish";
				String str3="Family";
				String str4=" To";
				String str5=" Mine";
				System.out.println(str1);

				// declare String using new keyword
				String str6=new String("welcome");
				System.out.println(str1);
				System.out.println(str6);
				
				//concatenation(+,concat,=,equals)
				System.out.println(str6+str1+str2+str3+str4+str5);
				System.out.println(str1.concat(str2).concat(str5));
				System.out.println(str1.equals(str5));//check value only
				System.out.println(str1==str5);//value as well as memory address location
				System.out.println(str1==str6);
				System.out.println(str1==str2);
				
				//remove unwanted space->trim
				//System.out.println(str1.trim());
				System.out.println(str1.toUpperCase());
				System.out.println(str2.toLowerCase());
				System.out.println(str1.valueOf(str2));
				System.out.println(str1.charAt(2));
				System.out.println(str1.length());
				System.out.println(str1.compareToIgnoreCase(str2));
				System.out.println(str1.compareTo(str2));
				System.out.println(str1.indexOf('i'));
				System.out.println(str1.substring(0,3));
				
				
				String s="Java is an object oriented program in coding";
				
				//split
				String result[]=s.split(" ");
				System.out.println("Result is:");
				for(String str:result)
				{
					System.out.println(str+",");
				}
				
				//Strat with
				System.out.println(str2.startsWith("As"));
				//endwith
				System.out.println(str1.endsWith("pd"));

				
				//for getting Single character from String
				char ch[]=str2.toCharArray();
				System.out.println("Char array elements are:");
				
				for(int i=0;i<str2.length();i++)
				{
					System.out.println(ch[i]);
				}

				//char array to String
				char[]letter= {'b','h','a','i'};
				String ChToS=new String(letter);
				System.out.println(ChToS);
				
				
			}

		}
