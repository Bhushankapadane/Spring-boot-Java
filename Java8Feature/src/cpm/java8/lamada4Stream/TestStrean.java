package cpm.java8.lamada4Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestStrean {
	
	
	 public static void main(String[] args) {
		 
		 
		   List<Product> productsList = new ArrayList<Product>();  
		   
		   productsList.add(new Product(10, "laptop", 10000));
		   productsList.add(new Product(12, "mobile", 40000));
		   productsList.add(new Product(13, "mouse", 30000));
		   productsList.add(new Product(14, "headfone", 50000));
		   
		   
		   
		   List<String> productPriceList2 =productsList.stream()  
                   .filter(p -> p.price > 30000)// filtering data  
                   .map(p->p.name)        // fetching price  
                   .collect(Collectors.toList());
		   
		   productPriceList2.forEach(list->System.out.println(list));
		   
		//   System.out.println(productPriceList2);
		   
		   
		   
		   
		   
		 
		 
		 
		 
		 
		 
	 }
	

}
