package case4838_reading_files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/ИТ/4. Projects/3. Java/2. Java Core/1. Overall/hyperskill/case-study/case4838-reading-files/src/main/java/case4838_reading_files/dataset_91069.txt");

		String foundYear = "";
		try (Scanner sc = new Scanner(file)) {
			sc.next();
			sc.next();
			sc.next();

			long maxIncrease = 0;
			long lastPopulation = Long.parseLong(sc.next().replaceAll(",", ""));
			while (sc.hasNext()) {
				String currentYear = sc.next();
				long currentPopulation = Long.parseLong(sc.next().replaceAll(",", ""));
				long currentIncrease = currentPopulation - lastPopulation;
				if (maxIncrease < currentIncrease) {
					maxIncrease = currentIncrease;
					foundYear = currentYear;
				}
				lastPopulation = currentPopulation;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(foundYear);
	}

}
