package ru.skubatko.dev.hyperskill.project.blockchain.stage2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class BlockchainStage2App {

    static final String BLOCKCHAIN_FILE_NAME = "blockchain.bcn";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must starts with: ");
        int zeros = sc.nextInt();

        Blockchain blockchain;
        if (new File(BLOCKCHAIN_FILE_NAME).exists()) {
            try (FileInputStream fis = new FileInputStream(BLOCKCHAIN_FILE_NAME);
                 BufferedInputStream bis = new BufferedInputStream(fis);
                 ObjectInputStream ois = new ObjectInputStream(bis)) {

                blockchain = (Blockchain) ois.readObject();
                blockchain.validate();
                blockchain.setZeros(zeros);
            } catch (Exception e) {
                e.printStackTrace();
                blockchain = new Blockchain(zeros);
            }
        } else {
            blockchain = new Blockchain(zeros);
        }

        for (int i = 0; i < 5; i++) {
            blockchain.addBlock();
        }

        blockchain.validate();

        Block block = blockchain.first;
        for (int i = 0; i < 5; i++) {
            System.out.println(block);
            block = block.next;
        }
    }

    private static class Blockchain implements Serializable {
        private Block first;
        private Block last;
        private int zeros;

        public Blockchain(int zeros) {
            this.zeros = zeros;

            first = new Block();
            first.id = 1;
            first.timestamp = new Date().getTime();
            first.parentBlockHash = "0";
            hash(first, zeros);

            last = first;
        }

        public void setZeros(int zeros) {
            this.zeros = zeros;
        }

        public void addBlock() {
            Block block = new Block();
            block.id = last.id + 1;
            block.timestamp = new Date().getTime();
            block.parentBlockHash = last.hash;
            hash(block, zeros);

            last.next = block;
            last = block;

            save();
        }

        public void validate() {
            Block block = first.next;
            while (block.next != null) {
                if (!(Objects.equals(block.hash, block.next.parentBlockHash))) {
                    throw new RuntimeException(block.toString());
                }
                block = block.next;
            }
        }

        private static void hash(Block block, int zeros) {
            long startTime = System.currentTimeMillis();

            String base = String.valueOf(block.id);

            StringBuilder patternBuilder = new StringBuilder();
            for (int i = 0; i < zeros; i++) {
                patternBuilder.append("0");
            }
            String pattern = patternBuilder.toString();

            Random random = new Random();
            int magicNumber;
            String hash;
            do {
                magicNumber = random.nextInt(Integer.MAX_VALUE);
                hash = applySha256(base + magicNumber);
            } while (!(hash.substring(0, zeros).equals(pattern)));

            block.hash = hash;
            block.magicNumber = magicNumber;

            long endTime = System.currentTimeMillis();
            block.generationTimeInSec = (endTime - startTime) / 1000;
        }

        /* Applies Sha256 to a string and returns a hash. */
        private static String applySha256(String input) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                /* Applies sha256 to our input */
                byte[] hash = digest.digest(input.getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();
                for (byte elem : hash) {
                    String hex = Integer.toHexString(0xff & elem);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void save() {
            try (FileOutputStream fos = new FileOutputStream(BLOCKCHAIN_FILE_NAME);
                 BufferedOutputStream bos = new BufferedOutputStream(fos);
                 ObjectOutputStream oos = new ObjectOutputStream(bos)) {

                oos.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Block implements Serializable {
        private int id;
        private long timestamp;
        private String parentBlockHash;
        private String hash;
        private Block next;
        private int magicNumber;
        private long generationTimeInSec;

        @Override
        public String toString() {
            return "\n" +
                           "Block:" + '\n' +
                           "Id: " + id + '\n' +
                           "Timestamp: " + timestamp + '\n' +
                           "Magic number: " + magicNumber + '\n' +
                           "Hash of the previous block:\n" + parentBlockHash + '\n' +
                           "Hash of the block:\n" + hash + '\n' +
                           "Block was generating for " + generationTimeInSec + " seconds" +
                           "";
        }
    }
}
