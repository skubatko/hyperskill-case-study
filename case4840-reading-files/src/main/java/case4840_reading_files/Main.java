package case4840_reading_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/ИТ/4. Projects/3. Java/2. Java Core/1. Overall/hyperskill/case-study/case4840-reading-files/src/main/java/case4840_reading_files/dataset_91033.txt");
		Scanner sc = new Scanner(file);
		int sum = 0;
		while (sc.hasNextInt()) {
			sum += sc.nextInt();
		}
		System.out.println(sum);
	}

}
