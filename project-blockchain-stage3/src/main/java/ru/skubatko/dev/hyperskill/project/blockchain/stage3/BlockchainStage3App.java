package ru.skubatko.dev.hyperskill.project.blockchain.stage3;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BlockchainStage3App {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        for (int i = 0; i < 10; i++) {
            blockchain.addBlock();
            blockchain.validate();
        }

        Blockchain.Block block = blockchain.first;
        for (int i = 0; i < 5; i++) {
            System.out.println(block);
            block = block.next;
        }

        blockchain.shutdown();
    }

    private static class Blockchain implements Serializable {
        private Block first;
        private Block last;
        private int zeros;

        private ExecutorService executor;

        public Blockchain() {
            executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }

        public void addBlock() {
            Block block = new Block();
            block.id = last != null ? last.id + 1 : 1;

            hash(block);

            block.timestamp = new Date().getTime();
            block.parentBlockHash = last != null ? last.hash : "0";

            if (blockValid(block)) {
                adjustZeros(block);
                chain(block);
            }

            System.out.println("\n>>> generated" + block);
        }

        private void adjustZeros(Block block) {
            if (block.generationTimeInSec < 1) {
                zeros++;
                block.zerosStatus = zeros;
            } else if (block.generationTimeInSec < 5) {
                block.zerosStatus = 0;
            } else {
                zeros--;
                block.zerosStatus = -1;
            }
        }

        private void chain(Block block) {
            if (last != null) {
                last.next = block;
            } else {
                first = block;
            }

            last = block;
        }

        private boolean blockValid(Block block) {
            StringBuilder patternBuilder = new StringBuilder();
            for (int i = 0; i < zeros; i++) {
                patternBuilder.append("0");
            }
            String pattern = patternBuilder.toString();
            return block.hash.substring(0, zeros).equals(pattern);
        }

        public void validate() {
            if (first == null) {
                return;
            }

            Block block = first.next;
            if (block == null) {
                return;
            }

            while (block.next != null) {
                if (!(Objects.equals(block.hash, block.next.parentBlockHash))) {
                    throw new RuntimeException(block.toString());
                }
                block = block.next;
            }
        }

        private void hash(Block block) {
            List<Future<HashInfo>> futures = new ArrayList<>(10);

            long startTime = System.currentTimeMillis();
            for (int i = 1; i < 10; i++) {
                futures.add(executor.submit(new Miner(i, block.id, zeros)));
            }

            boolean isDone = false;
            do {
                for (Future<HashInfo> future : futures) {
                    if (future.isDone()) {
                        try {
                            HashInfo hashInfo = future.get();
                            block.hash = hashInfo.hash;
                            block.magicNumber = hashInfo.magicNumber;
                            block.createdBy = hashInfo.minerId;
                            isDone = true;
                            break;
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } while (!(isDone));

            long endTime = System.currentTimeMillis();
            block.generationTimeInSec = (endTime - startTime) / 1000;

            for (Future<HashInfo> future : futures) {
                future.cancel(true);
            }
        }

        public void shutdown() {
            executor.shutdown();
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
            private int zerosStatus;

            @Override
            public String toString() {
                String zerosStatusString = "";
                if (zerosStatus < 0) {
                    zerosStatusString = "N was decreased by 1";
                } else if (zerosStatus == 0) {
                    zerosStatusString = "N stays the same";
                } else {
                    zerosStatusString = "N was increased to " + zerosStatus;
                }

                return "\n" +
                        "Block:" + '\n' +
                        "Created by miner # " + createdBy + '\n' +
                        "Id: " + id + '\n' +
                        "Timestamp: " + timestamp + '\n' +
                        "Magic number: " + magicNumber + '\n' +
                        "Hash of the previous block:\n" + parentBlockHash + '\n' +
                        "Hash of the block:\n" + hash + '\n' +
                        "Block was generating for " + generationTimeInSec + " seconds" + '\n' +
                        zerosStatusString +
                        "";
            }
        }

        private static class Miner implements Callable<HashInfo> {
            private int id;
            private int blockId;
            private int zeros;

            public Miner(int id, int blockId, int zeros) {
                this.id = id;
                this.blockId = blockId;
                this.zeros = zeros;
            }

            @Override
            public HashInfo call() {
                String base = String.valueOf(blockId);

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
                } while (!(hash.substring(0, zeros).equals(pattern))
                        && !(Thread.interrupted()));

                return new HashInfo(id, hash, magicNumber);
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
        }

        private static class HashInfo {
            private int minerId;
            private String hash;
            private int magicNumber;

            public HashInfo(int minerId, String hash, int magicNumber) {
                this.minerId = minerId;
                this.hash = hash;
                this.magicNumber = magicNumber;
            }
        }
    }
}
