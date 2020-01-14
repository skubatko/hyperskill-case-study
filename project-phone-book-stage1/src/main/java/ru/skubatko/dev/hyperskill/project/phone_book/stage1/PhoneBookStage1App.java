package ru.skubatko.dev.hyperskill.project.phone_book.stage1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PhoneBookStage1App {

    public static void main(String[] args) throws IOException {
        List<String> directory = Files.readAllLines(Paths.get("/Users/TeSSerA/Downloads/directory.txt"));
        List<String> find = Files.readAllLines(Paths.get("/Users/TeSSerA/Downloads/find.txt"));

        long startTime = System.currentTimeMillis();
        System.out.println("Start searching...");
        int found = 0;
        for (String name : find) {
            for (String record : directory) {
                if (record.contains(name)) {
                    found++;
                    break;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        System.out.println(String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, find.size(), period / 1000 / 60, period / 1000 % 60, period % 1000
        ));
    }
}
