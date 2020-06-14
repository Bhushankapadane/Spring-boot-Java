package cpm.java8.lamada4filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProductTest4 {

	public static void main(String[] args) {
		List<Product1> list = new ArrayList<Product1>();
		list.add(new Product1(1, "Samsung A5", 17000f));
		list.add(new Product1(3, "Iphone 6S", 65000f));
		list.add(new Product1(2, "Sony Xperia", 25000f));
		list.add(new Product1(4, "Nokia Lumia", 15000f));
		list.add(new Product1(5, "Redmi4 ", 26000f));
		list.add(new Product1(6, "Lenevo Vibe", 19000f));

		Stream<Product1> productlist = list.stream().filter(p -> p.price > 20000);
		
		
		productlist.forEach(  
	                product -> System.out.println(product)  
	        );  

	}

}
