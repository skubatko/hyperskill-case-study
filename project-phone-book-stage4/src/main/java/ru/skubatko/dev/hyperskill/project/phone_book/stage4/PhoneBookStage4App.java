package ru.skubatko.dev.hyperskill.project.phone_book.stage4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookStage4App {

    public static void main(String[] args) throws IOException {
        List<String> directory = Files.readAllLines(Paths.get("/Users/TeSSerA/Downloads/directory.txt"));
        List<String> find = Files.readAllLines(Paths.get("/Users/TeSSerA/Downloads/find.txt"));

        long searchingPeriod = stage1(directory, find);
        System.out.println();

        stage2(directory, find, searchingPeriod);
        System.out.println();

        stage3(directory, find);
        System.out.println();

        stage4(directory, find);
    }

    private static void stage4(List<String> dir, List<String> list) {
        List<String> directory = new ArrayList<>(dir);
        List<String> find = new ArrayList<>(list);

        System.out.println("Start searching (hash table)...");

        long startTime = System.currentTimeMillis();
        HashTable<String> hashTable = createHashTable(directory);
        long endTime = System.currentTimeMillis();
        long creatingPeriod = endTime - startTime;

        startTime = System.currentTimeMillis();
        int found = 0;
        for (int i = 0; i < list.size(); i++) {
            if (hashTable.get(hash(list.get(i))) != null) {
                found++;
            }
        }
        endTime = System.currentTimeMillis();
        long searchingPeriod = endTime - startTime;

        long totalPeriod = creatingPeriod + searchingPeriod;

        System.out.println(String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, find.size(),
                totalPeriod / 1000 / 60, totalPeriod / 1000 % 60, totalPeriod % 1000
        ));

        System.out.print(String.format("Creating time: %d min. %d sec. %d ms.",
                creatingPeriod / 1000 / 60, creatingPeriod / 1000 % 60, creatingPeriod % 1000
        ));
        System.out.println();
        System.out.println(String.format("Searching time: %d min. %d sec. %d ms.",
                searchingPeriod / 1000 / 60, searchingPeriod / 1000 % 60, searchingPeriod % 1000
        ));
    }

    private static HashTable<String> createHashTable(List<String> directory) {
        int size = directory.size();
        HashTable table = new HashTable(size);
        for (int i = 0; i < size; i++) {
            String name = getDirectoryName(directory, i);
            table.put(hash(name), directory.get(i));
        }
        return table;
    }

    private static class HashTable<T> {

        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(long key, T value) {
            int idx = findKey(key);
            if (idx == -1) {
                return false;
            }

            table[idx] = new TableEntry(key, value);
            return true;
        }

        public T get(long key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        private int findKey(long key) {
            int hash = (int) (key % size);

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private static class TableEntry<T> {
            private final long key;
            private final T value;

            public TableEntry(long key, T value) {
                this.key = key;
                this.value = value;
            }

            public long getKey() {
                return key;
            }

            public T getValue() {
                return value;
            }
        }
    }

    public static long hash(String value) {
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash += value.charAt(i);
        }
        return hash;
    }

    private static void stage3(List<String> dir, List<String> list) {
        List<String> directory = new ArrayList<>(dir);
        List<String> find = new ArrayList<>(list);

        System.out.println("Start searching (quick sort + binary search)...");

        long startTime = System.currentTimeMillis();
        quickSort(directory);
        long endTime = System.currentTimeMillis();
        long sortingPeriod = endTime - startTime;

        startTime = System.currentTimeMillis();
        int found = binarySearch(directory, find);
        endTime = System.currentTimeMillis();
        long searchingPeriod = endTime - startTime;

        long totalPeriod = sortingPeriod + searchingPeriod;

        System.out.println(String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, find.size(),
                totalPeriod / 1000 / 60, totalPeriod / 1000 % 60, totalPeriod % 1000
        ));

        System.out.print(String.format("Sorting time: %d min. %d sec. %d ms.",
                sortingPeriod / 1000 / 60, sortingPeriod / 1000 % 60, sortingPeriod % 1000
        ));
        System.out.println();
        System.out.println(String.format("Searching time: %d min. %d sec. %d ms.",
                searchingPeriod / 1000 / 60, searchingPeriod / 1000 % 60, searchingPeriod % 1000
        ));
    }

    private static void quickSort(List<String> array) {
        quickSort(array, 0, array.size() - 1);
    }

    private static void quickSort(List<String> directory, int leftIncl, int rightIncl) {
        if (leftIncl < rightIncl) {
            int pivotIndex = partition(directory, leftIncl, rightIncl); // the pivot is already on its place
            quickSort(directory, leftIncl, pivotIndex - 1);  // sort the leftIncl subarray
            quickSort(directory, pivotIndex + 1, rightIncl); // sort the rightIncl subarray
        }
    }

    private static int partition(List<String> directory, int left, int right) {
        String pivot = getDirectoryName(directory, right);  // choose the rightmost element as the pivot
        int partitionIndex = left; // the first element greater than the pivot

        /* move large values into the right side of the directory */
        for (int i = left; i < right; i++) {
            if (getDirectoryName(directory, i).compareTo(pivot) <= 0) { // may be used '<' as well
                swap(directory, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(directory, partitionIndex, right); // put the pivot on a suitable position

        return partitionIndex;
    }

    private static void swap(List<String> directory, int i, int j) {
        String temp = directory.get(i);
        directory.set(i, directory.get(j));
        directory.set(j, temp);
    }

    private static int binarySearch(List<String> directory, List<String> find) {
        int counter = 0;
        for (String value : find) {
            int idx = binarySearchRecursive(directory, value, 0, directory.size() - 1);
            if (idx != -1) {
                counter++;
            }
        }
        return counter;
    }

    private static int binarySearchRecursive(List<String> directory, String value, int leftIncl, int rightIncl) {
        if (leftIncl > rightIncl) {
            return -1; // search interval is empty, the element is not found
        }

        int mid = leftIncl + (rightIncl - leftIncl) / 2; // the index of the middle element

        if (value.equals(getDirectoryName(directory, mid))) {
            return mid; // the element is found, return its index
        } else if (value.compareTo(getDirectoryName(directory, mid)) < 0) {
            return binarySearchRecursive(directory, value, leftIncl, mid - 1); // go to the leftIncl subarray
        } else {
            return binarySearchRecursive(directory, value, mid + 1, rightIncl); // go the the rightIncl subarray
        }
    }

    private static void stage2(List<String> dir, List<String> list, long linearPeriod) {
        List<String> directory = new ArrayList<>(dir);
        List<String> find = new ArrayList<>(list);

        long startTime = System.currentTimeMillis();
        System.out.println("Start searching (bubble sort + jump search)...");
        boolean isSorted = bubbleSort(directory, linearPeriod * 10);
        long endTime = System.currentTimeMillis();
        long sortingPeriod = endTime - startTime;

        startTime = System.currentTimeMillis();
        int found = 0;
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
        long searchingPeriod = endTime - startTime;
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

            String name = getDirectoryName(directory, currentRight);
            if (name.compareTo(value) >= 0) {
                break;
            }

            prevRight = currentRight;
        }

        String name = getDirectoryName(directory, currentRight);
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

    private static long stage1(List<String> dir, List<String> list) {
        List<String> directory = new ArrayList<>(dir);
        List<String> find = new ArrayList<>(list);

        long startTime = System.currentTimeMillis();
        System.out.println("Start searching (linear search)...");
        int found = linearSearch(directory, find);

        long endTime = System.currentTimeMillis();
        long searchingPeriod = endTime - startTime;

        System.out.println(String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, find.size(),
                searchingPeriod / 1000 / 60, searchingPeriod / 1000 % 60, searchingPeriod % 1000
        ));
        return searchingPeriod;
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

    private static String getDirectoryName(List<String> directory, int idx) {
        String line = directory.get(idx);
        return line.substring(line.indexOf(' ') + 1);
    }
}
