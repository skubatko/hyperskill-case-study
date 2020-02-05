package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

class HashInfo {
    private int minerId;
    private String hash;
    private int magicNumber;

    public HashInfo(int minerId, String hash, int magicNumber) {
        this.minerId = minerId;
        this.hash = hash;
        this.magicNumber = magicNumber;
    }

    public int getMinerId() {
        return minerId;
    }

    public String getHash() {
        return hash;
    }

    public int getMagicNumber() {
        return magicNumber;
    }
}
