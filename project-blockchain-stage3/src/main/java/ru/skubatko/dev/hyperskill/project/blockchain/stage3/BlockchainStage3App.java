package ru.skubatko.dev.hyperskill.project.blockchain.stage3;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockchainStage3App {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 1; i < 10; i++) {
            executor.submit(new Miner(i, blockchain));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Blockchain.Block block = blockchain.first;
        for (int i = 0; i < 5; i++) {
            System.out.println(block);
            block = block.next;
        }
    }

    private static class Miner implements Runnable {
        private int id;
        private Blockchain blockchain;

        public Miner(int id, Blockchain blockchain) {
            this.id = id;
            this.blockchain = blockchain;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                blockchain.addBlock(id);
                blockchain.validate();
            }
        }
    }

    private static class Blockchain implements Serializable {
        private volatile Block first;
        private volatile Block last;
        private volatile int zeros;

        public synchronized void setZeros(int zeros) {
            this.zeros = zeros;
        }

        public synchronized void addBlock(int createdBy) {
            Block block = new Block();

            hash(block, zeros);

            block.id = last != null ? last.id + 1 : 1;
            block.timestamp = new Date().getTime();
            block.parentBlockHash = last != null ? last.hash : "0";
            block.createdBy = createdBy;

            if (last != null) {
                last.next = block;
            } else {
                first = block;
            }

            last = block;
        }

        public synchronized void validate() {
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

        private static class Block implements Serializable {
            private int id;
            private long timestamp;
            private String parentBlockHash;
            private String hash;
            private Block next;
            private int magicNumber;
            private long generationTimeInSec;
            private int createdBy;

            @Override
            public String toString() {
                return "\n" +
                               "Block:" + '\n' +
                               "Created by miner # " + createdBy + '\n' +
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
}
