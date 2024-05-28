/*
Fazer um programa para ler um conjunto de produtos a partir de um arquivo em formato .csv (suponha que exista pelo menos um produto).
Em seguida mostrar o preço médio dos produtos.
Depois, mostrar os nomes, em ordem decrescente, dos produtos que possuem preço inferior ao preço médio.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import entities.Product;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		FileReader fl = null;
		BufferedReader br = null;
		Product product;
		
		List<Product> list = new ArrayList<>();
		
		System.out.print("Path: ");
		String path = sc.nextLine();
		
		try {
			
			fl = new FileReader(path);
			br = new BufferedReader(fl);
			String line = br.readLine();
			
			Integer counter = 0;
			Double sum = 0.0;
			while (line != null) {
				
				String[] fields = line.split(",");
				String name = fields[0];
				Double price = Double.parseDouble(fields[1]);
				sum = sum + price;
				counter = counter + 1;
				product = new Product(name, price);
				list.add(product);
				line = br.readLine();
				
			}
			
			Double media = sum / counter;
			System.out.printf("%.2f%n", media);
			
			Comparator<String> comparator = (string1, string2) -> string1.toUpperCase().compareTo(string2.toUpperCase());
			
			List<String> newList = list.stream()
					.filter(x -> x.getPrice() <= media)
					.map(x -> x.getName())
					.sorted(comparator.reversed())
					.collect(Collectors.toList());
			
			newList.forEach(System.out::println);
			
		}
		
		catch (IOException message) {
			
			System.out.println("Error: " + message.getMessage());
			
		}
		
		sc.close();
		
	}

}
