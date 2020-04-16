package ru.skubatko.dev.hyperskill.project.blockchain.stage4;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class BlockchainStage4App {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Chat chat = new Chat(blockchain);
        new Thread(chat).start();

        for (int i = 0; i < 7; i++) {
            blockchain.addBlock();
            blockchain.validate();
        }

        Blockchain.Block block = blockchain.first;
        for (int i = 0; i < 5; i++) {
//			System.out.println(block);
            block = block.next;
        }

        chat.shutdown();
        blockchain.shutdown();
    }

    private static class Chat implements Runnable {
        private Blockchain blockchain;
        private ExecutorService executor;
        private List<String> messages = new ArrayList<>();
        private Random random = new Random();
        private volatile boolean running = true;

        {
            messages.add("Hi! I'm here.");
            messages.add("What's up?!");
            messages.add("It's not fair!");
            messages.add("Anyway, thank you for this amazing chat.");
            messages.add("You're welcome :)");
            messages.add("Hey, nice chat!!");
        }

        public Chat(Blockchain blockchain) {
            this.blockchain = blockchain;
            executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }

        @Override
        public void run() {
            blockchain.addMessage("Tom: " + "I'm first!");
            while (running) {
                executor.submit(() -> sendMsg("Sarah"));
                executor.submit(() -> sendMsg("Nick"));
                executor.submit(() -> sendMsg("Tom"));
            }
            executor.shutdownNow();
        }

        public void shutdown() {
            running = false;
        }


        private void sendMsg(String name) {
            try {
                blockchain.addMessage(name + ": " + getRandomMsg());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                // empty
            }
        }

        private String getRandomMsg() {
            int size = messages.size();
            return messages.get(random.nextInt(size));
        }
    }

    private static class Blockchain {
        private Block first;
        private Block last;
        private int zeros;
        private List<String> messages = new CopyOnWriteArrayList<>();

        private ExecutorService executor;

        public Blockchain() {
            executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }

        public void addBlock() {
            Block block = new Block();
            boolean isNotFirst = last != null;
            block.id = isNotFirst ? last.id + 1 : 1;

            hash(block);

            if (isNotFirst) {
                synchronized (this) {
                    block.messages.addAll(messages);
                    messages.clear();
                }
            }

            block.timestamp = new Date().getTime();
            block.parentBlockHash = isNotFirst ? last.hash : "0";

            if (blockValid(block)) {
                adjustZeros(block);
                chain(block);
            }

            System.out.println("\n>>> generated" + block);
        }

        public void addMessage(String message) {
            messages.add(message);
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
            executor.shutdownNow();
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
            private List<String> messages = new ArrayList<>();

            @Override
            public String toString() {
                String zerosStatusString;
                if (zerosStatus < 0) {
                    zerosStatusString = "N was decreased by 1";
                } else if (zerosStatus == 0) {
                    zerosStatusString = "N stays the same";
                } else {
                    zerosStatusString = "N was increased to " + zerosStatus;
                }

                String blockData;
                if (messages.isEmpty()) {
                    blockData = " no messages";
                } else {
                    blockData = "\n" + messages.stream().collect(Collectors.joining("\n"));
                }

                return "\n" +
                               "Block:" + '\n' +
                               "Created by miner # " + createdBy + '\n' +
                               "Id: " + id + '\n' +
                               "Timestamp: " + timestamp + '\n' +
                               "Magic number: " + magicNumber + '\n' +
                               "Hash of the previous block:\n" + parentBlockHash + '\n' +
                               "Hash of the block:\n" + hash + '\n' +
                               "Block data:" + blockData + '\n' +
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
