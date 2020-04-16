package ru.skubatko.dev.hyperskill.case4838;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Case4838 {

    public static void main(String[] args) {
        File file = new File("/Users/TeSSerA/Documents/3. Personal/2. Projects/5. Профессия/3. Software Development/2. Projects/3. Java/2. Java Core/1. Overall/hyperskill/hyperskill-case-study/case4838/src/main/resources/dataset_91069.txt");

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
