package ru.skubatko.dev.hyperskill.case4842;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/5. Профессия/3. Software Development/2. Projects/3. Java/2. Java Core/1. Overall/hyperskill/hyperskill-case-study/case4842/src/main/resources/dataset_91007.txt");
		int max = Integer.MIN_VALUE;
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextInt()) {
				int num = sc.nextInt();
				if (max < num) {
					max = num;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(max);
	}
}
