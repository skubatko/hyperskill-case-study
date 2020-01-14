package ru.skubatko.dev.hyperskill.project.phone_book.stage2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PhoneBookStage2App {

    public static void main(String[] args) throws IOException {
        List<String> directory = Files.readAllLines(Paths.get("/Users/TeSSerA/Downloads/directory.txt"));
        List<String> find = Files.readAllLines(Paths.get("/Users/TeSSerA/Downloads/find.txt"));

        long startTime = System.currentTimeMillis();
        System.out.println("Start searching (linear search)...");
        int found = linearSearch(directory, find);

        long endTime = System.currentTimeMillis();
        long searchingPeriod = endTime - startTime;

        System.out.println(String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, find.size(),
                searchingPeriod / 1000 / 60, searchingPeriod / 1000 % 60, searchingPeriod % 1000
        ));
        System.out.println();

        startTime = System.currentTimeMillis();
        System.out.println("Start searching (bubble sort + jump search)...");
        boolean isSorted = bubbleSort(directory, searchingPeriod * 10);
        endTime = System.currentTimeMillis();
        long sortingPeriod = endTime - startTime;

        startTime = System.currentTimeMillis();
        found = 0;
        if (isSorted) {
            for (String name : find) {
                if (jumpSearch(directory, name) >= 0) {
                    found++;
                }
            }
        } else {
            found = linearSearch(directory, find);
        }
        endTime = System.currentTimeMillis();
        searchingPeriod = endTime - startTime;
        long stage2Period = sortingPeriod + searchingPeriod;

        System.out.println(String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, find.size(),
                stage2Period / 1000 / 60, stage2Period / 1000 % 60, stage2Period % 1000
        ));
        System.out.print(String.format("Sorting time: %d min. %d sec. %d ms.",
                sortingPeriod / 1000 / 60, sortingPeriod / 1000 % 60, sortingPeriod % 1000
        ));
        if (isSorted) {
            System.out.println();
        } else {
            System.out.println(" - STOPPED, moved to linear search");
        }
        System.out.println(String.format("Searching time: %d min. %d sec. %d ms.",
                searchingPeriod / 1000 / 60, searchingPeriod / 1000 % 60, searchingPeriod % 1000
        ));
    }

    private static int linearSearch(List<String> directory, List<String> find) {
        int found = 0;
        for (String name : find) {
            for (String record : directory) {
                if (record.contains(name)) {
                    found++;
                    break;
                }
            }
        }
        return found;
    }

    private static boolean bubbleSort(List<String> directory, long period) {
        long startTime = System.currentTimeMillis();
        long currentTime;
        long sortingPeriod;
        int size = directory.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                String current = directory.get(j);
                String currentName = current.substring(current.indexOf(' ') + 1);
                String next = directory.get(j + 1);
                String nextName = next.substring(next.indexOf(' ') + 1);
                if (currentName.compareTo(nextName) > 0) {
                    directory.set(j, next);
                    directory.set(j + 1, current);
                }

                currentTime = System.currentTimeMillis();
                sortingPeriod = currentTime - startTime;
                if (sortingPeriod > period) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int jumpSearch(List<String> directory, String value) {
        int currentRight = 0;
        int prevRight = 0;
        int size = directory.size();

        if (size == 0) {
            return -1;
        }

        if (directory.get(currentRight).contains(value)) {
            return 0;
        }

        int blockSize = (int) Math.sqrt(size);
        while (currentRight < size - 1) {

            currentRight = Math.min(size - 1, currentRight + blockSize);

            String line = directory.get(currentRight);
            String name = line.substring(line.indexOf(' ') + 1);
            if (name.compareTo(value) >= 0) {
                break;
            }

            prevRight = currentRight;
        }

        String line = directory.get(currentRight);
        String name = line.substring(line.indexOf(' ') + 1);
        if ((currentRight == size - 1) && value.compareTo(name) > 0) {
            return -1;
        }

        return backwardSearch(directory, value, prevRight, currentRight);
    }

    private static int backwardSearch(List<String> directory, String value, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (directory.get(i).contains(value)) {
                return i;
            }
        }
        return -1;
    }
}
