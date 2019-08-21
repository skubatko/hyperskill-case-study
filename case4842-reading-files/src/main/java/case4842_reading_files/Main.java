package case4842_reading_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/ИТ/4. Projects/3. Java/2. Java Core/1. Overall/hyperskill/case-study/case4842-reading-files/src/main/java/case4842_reading_files/dataset_91007.txt");
		Scanner sc = new Scanner(file);
		int max = Integer.MIN_VALUE;
		while (sc.hasNextInt()){
			int num = sc.nextInt();
			if (max < num){
				max = num;
			}
		}
		System.out.println(max);
	}

}
