package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

import java.security.MessageDigest;
import java.util.Random;
import java.util.concurrent.Callable;

class Miner extends Account implements Callable<HashInfo> {
    private int id;
    private int blockId;
    private int zeros;

    public Miner(int id) {
        super("miner" + id);
        this.id = id;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public void setZeros(int zeros) {
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
