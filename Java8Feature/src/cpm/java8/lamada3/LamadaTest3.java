package cpm.java8.lamada3;

import java.util.ArrayList;
import java.util.Collections;

public class LamadaTest3 {

	public static void main(String[] args) {

		ArrayList<Product> pro = new ArrayList<>();

		pro.add(new Product(10, " zell", 20000));
		pro.add(new Product(11, " mobile", 30000));
		pro.add(new Product(12, " mouse", 10000));
		
		 Collections.sort(pro,(p1,p2)->{  
		        return p1.name.compareTo(p2.name);  
		        });
		 
		 pro.forEach(list->System.out.println(list));

	}

}
