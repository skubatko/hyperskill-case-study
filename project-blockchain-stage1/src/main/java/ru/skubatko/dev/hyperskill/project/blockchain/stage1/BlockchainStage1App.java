package ru.skubatko.dev.hyperskill.project.blockchain.stage1;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Objects;

public class BlockchainStage1App {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        for (int i = 0; i < 10; i++) {
            blockchain.addBlock();
        }

        blockchain.validate();

        Block block = blockchain.first;
        for (int i = 0; i < 5; i++) {
            System.out.println(block);
            block = block.next;
        }
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
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class Blockchain {
        private Block first;
        private Block last;

        public Blockchain() {
            first = new Block();
            first.id = 1;
            first.timestamp = new Date().getTime();
            first.parentBlockHash = "0";
            first.hash = applySha256(String.valueOf(first.id));
            last = first;
        }

        public void addBlock() {
            Block block = new Block();
            block.id = last.id + 1;
            block.timestamp = new Date().getTime();
            block.parentBlockHash = last.hash;
            block.hash = applySha256(String.valueOf(block.id));
            last.next = block;
            last = block;
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
    }

    private static class Block {
        private int id;
        private long timestamp;
        private String parentBlockHash;
        private String hash;
        private Block next;

        @Override
        public String toString() {
            return "" +
                    "Block:" + '\n' +
                    "Id: " + id + '\n' +
                    "Timestamp: " + timestamp + '\n' +
                    "Hash of the previous block:\n" + parentBlockHash + '\n' +
                    "Hash of the block:\n" + hash + '\n' +
                    "";
        }
    }
}
