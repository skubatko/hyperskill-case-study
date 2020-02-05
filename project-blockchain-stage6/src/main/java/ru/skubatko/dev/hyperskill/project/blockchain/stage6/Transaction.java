package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

import java.security.PublicKey;

public class Transaction {
    private long id;
    private String text;
    private String from;
    private String to;
    private int amount;
    private byte[] signature;
    private PublicKey publicKey;

    public Transaction(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return from + " sent " + amount + " VC to " + to;
    }
}
