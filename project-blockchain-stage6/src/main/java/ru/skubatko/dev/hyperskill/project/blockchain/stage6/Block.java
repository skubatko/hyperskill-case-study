package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Block {
    private int id;
    private long timestamp;
    private String parentBlockHash;
    private String hash;
    private Block next;
    private int magicNumber;
    private long generationTimeInSec;
    private int createdBy;
    private int zerosStatus;
    private List<Transaction> transactions = new ArrayList<>();
    private long maxTransactionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getParentBlockHash() {
        return parentBlockHash;
    }

    public void setParentBlockHash(String parentBlockHash) {
        this.parentBlockHash = parentBlockHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public long getGenerationTimeInSec() {
        return generationTimeInSec;
    }

    public void setGenerationTimeInSec(long generationTimeInSec) {
        this.generationTimeInSec = generationTimeInSec;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getZerosStatus() {
        return zerosStatus;
    }

    public void setZerosStatus(int zerosStatus) {
        this.zerosStatus = zerosStatus;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getMaxTransactionId() {
        return maxTransactionId;
    }

    public void setMaxTransactionId(long maxTransactionId) {
        this.maxTransactionId = maxTransactionId;
    }

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
        if (transactions.isEmpty()) {
            blockData = "\nNo transactions";
        } else {
            blockData = "\n" + transactions.stream().map(Transaction::toString).collect(Collectors.joining("\n"));
        }

        return "\n" +
                "Block:" + '\n' +
                "Created by miner" + createdBy + '\n' +
                "miner" + createdBy + " gets 100 VC" + '\n' +
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
