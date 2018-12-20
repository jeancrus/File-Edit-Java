package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws IOException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the folder path to read the file: ");
		String pathFile = sc.nextLine();
		System.out.print("Enter the subdir you want to create: ");
		String subDir = sc.nextLine();
		System.out.print("Enter the name of the file you want to create with .type (name.txt, name.csv): ");
		String nameFile = sc.nextLine();
		File strPath = new File(pathFile);
		String parentPath = strPath.getParent();
		boolean success = new File(parentPath + "\\" + subDir).mkdir();
		String targetFile = parentPath + "\\" + subDir + "\\" + nameFile;
		List<Product> prod = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(";");
				String name = vect[0];
				double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				prod.add(new Product(name, price, quantity));
				line = br.readLine();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
				for (Product p : prod) {
					bw.write(p.getName() + "," + "total = " + String.format("%.2f", p.sum()));
					bw.newLine();
				}
				if (success) {
				System.out.println("Summary.csv created successfully!!");
				}
			}
		}catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		

		sc.close();
	}

}
