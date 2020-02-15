package ru.skubatko.dev.hyperskill.case4839;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Case4839 {

	public static void main(String[] args) {
		File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/5. Профессия/3. Software Development/2. Projects/3. Java/2. Java Core/1. Overall/hyperskill/hyperskill-case-study/case4839/src/main/resources/dataset_91065.txt");
		int counter = 0;
		try (Scanner sc = new Scanner(file)) {
			int num;
			while (sc.hasNextInt() && (num = sc.nextInt()) != 0) {
				if (num % 2 == 0) {
					counter++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(counter);
	}
}
