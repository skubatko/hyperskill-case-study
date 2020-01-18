package ru.skubatko.dev.hyperskill.case6951;

import java.util.Scanner;

public class Case6951 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        HashTable<String> hashTable = new HashTable<>(1);
        for (int i = 0; i < n; i++) {
            String command = sc.next();
            switch (command) {
                case "put":
                    hashTable.put(sc.nextInt(), sc.next());
                    break;
                case "get":
                    String value = hashTable.get(sc.nextInt());
                    if (value == null) {
                        value = "-1";
                    }
                    System.out.println(value);
                    break;
                case "remove":
                    hashTable.remove(sc.nextInt());
                    break;
                default:
                    break;
            }
        }
    }

    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    static class HashTable<T> {
        private int size;
        private TableEntry[] table;
        private int count;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
            count = 0;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        public void remove(int key) {
            if (count == 0) {
                return;
            }
            int idx = findKey(key);

            if (idx == -1) {
                return;
            }
            table[idx] = null;
            count--;
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        public boolean put(int key, T value) {
            if (count == table.length) {
                rehash();
            }

            int idx = findKey(key);
            if (idx == -1) {
                return false;
            }

            table[idx] = new TableEntry(key, value);
            count++;
            return true;
        }

        private void rehash() {
            int oldCapacity = table.length;
            TableEntry[] oldTable = new TableEntry[oldCapacity];
            System.arraycopy(table, 0, oldTable, 0, oldCapacity);

            int newCapacity = (oldCapacity << 1) + 1;
            int maxArraySize = Integer.MAX_VALUE - 8;
            if (newCapacity - maxArraySize > 0) {
                if (oldCapacity == maxArraySize) {
                    return;
                }
                newCapacity = maxArraySize;
            }

            table = new TableEntry[newCapacity];
            size = newCapacity;
            for (int i = 0; i < oldCapacity; i++) {
                TableEntry e = oldTable[i];
                int idx = findKey(e.key);
                table[idx] = new TableEntry(e.key, e.value);
            }
        }
    }
}
