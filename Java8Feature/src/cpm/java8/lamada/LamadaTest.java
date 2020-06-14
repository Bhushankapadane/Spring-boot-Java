package cpm.java8.lamada;

public class LamadaTest {
	
	
	public static void main(String[] args) {  
		
		 Sayable s1=(name)->{            //add hare parameter
	            return "Hello, "+name;  
	        };  
		
		
		System.out.println(s1.say("bhushan"));
	}

}
