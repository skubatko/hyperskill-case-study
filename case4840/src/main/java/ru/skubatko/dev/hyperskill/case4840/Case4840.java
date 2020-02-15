package ru.skubatko.dev.hyperskill.case4840;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Case4840 {

	public static void main(String[] args) {
		File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/5. Профессия/3. Software Development/2. Projects/3. Java/2. Java Core/1. Overall/hyperskill/hyperskill-case-study/case4840/src/main/resources/dataset_91033.txt");
		int sum = 0;
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextInt()) {
				sum += sc.nextInt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(sum);
	}
}
