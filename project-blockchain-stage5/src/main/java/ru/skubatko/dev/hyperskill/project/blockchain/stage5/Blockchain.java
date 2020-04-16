package ru.skubatko.dev.hyperskill.project.blockchain.stage5;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class Blockchain {
    private Block first;
    private Block last;
    private int zeros;
    private List<Message> messages = new CopyOnWriteArrayList<>();
    private AtomicLong counter = new AtomicLong(1L);
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private ExecutorService executor;

    public Block getFirst() {
        return first;
    }

    public Blockchain() {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            // empty
        }
    }

    public void addBlock() {
        boolean isNotFirst = last != null;

        Block block = new Block();

        int id = isNotFirst ? last.getId() + 1 : 1;
        block.setId(id);

        if (isNotFirst) {
            synchronized (this) {
                block.getMessages().addAll(messages);
                messages.clear();
            }
        }

        long messageId = isNotFirst
                ? block.getMessages().stream().mapToLong(Message::getId).max().orElse(last.getMaxMessageId())
                : 0L;
        block.setMaxMessageId(messageId);

        hash(block);

        block.setTimestamp(new Date().getTime());

        String parentBlockHash = isNotFirst ? last.getHash() : "0";
        block.setParentBlockHash(parentBlockHash);

        if (blockValid(block)) {
            adjustZeros(block);
            chain(block);
        }

        System.out.println("\n>>> generated" + block);
    }

    public void addMessage(String text) {
        Message message = new Message();
        message.setId(counter.getAndIncrement());
        message.setText(text);
        message.setPublicKey(publicKey);
        sign(message);
        messages.add(message);
    }

    private void sign(Message message) {
        try {
            Signature rsa = Signature.getInstance("SHA1withRSA");
            rsa.initSign(privateKey);
            String subjectToSign = message.getId() + message.getText();
            rsa.update(subjectToSign.getBytes());
            message.setSignature(rsa.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }

    private void adjustZeros(Block block) {
        long generationTimeInSec = block.getGenerationTimeInSec();
        if (generationTimeInSec < 1) {
            zeros++;
            block.setZerosStatus(zeros);
        } else if (generationTimeInSec < 5) {
            block.setZerosStatus(0);
        } else {
            zeros--;
            block.setZerosStatus(-1);
        }
    }

    private void chain(Block block) {
        if (last != null) {
            last.setNext(block);
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
        return block.getHash().substring(0, zeros).equals(pattern);
    }

    public void validate() {
        if (first == null) {
            return;
        }

        Block block = first.getNext();
        if (block == null) {
            return;
        }

        long maxId = 0L;
        while (block.getNext() != null) {
            if (!(Objects.equals(block.getHash(), block.getNext().getParentBlockHash()))) {
                throw new RuntimeException(block.toString());
            }

            validateMessageSignature(block);

            validateMessageId(block, maxId);

            maxId = block.getMaxMessageId();
            block = block.getNext();
        }
    }

    private void validateMessageSignature(Block block) {
        List<Message> messages = block.getMessages();

        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            boolean isVerified = messages.stream()
                    .peek(m -> {
                        try {
                            signature.initVerify(m.getPublicKey());
                        } catch (InvalidKeyException e) {
                            // empty
                        }
                    })
                    .peek(m -> {
                        try {
                            signature.update((m.getId() + m.getText()).getBytes());
                        } catch (SignatureException e) {
                            // empty
                        }
                    })
                    .map(m -> {
                        try {
                            return signature.verify(m.getSignature());
                        } catch (SignatureException e) {
                            // empty
                        }
                        return true;
                    })
                    .reduce(true, (acc, elem) -> acc && elem);
            if (!isVerified) {
                throw new RuntimeException(block.toString());
            }
        } catch (NoSuchAlgorithmException e) {
            // empty
        }
    }

    private void validateMessageId(Block block, long maxId) {
        long wrongId = block.getMessages().stream()
                .mapToLong(Message::getId)
                .filter(id -> id <= maxId)
                .count();
        boolean isVerified = wrongId == 0L;
        if (!isVerified) {
            throw new RuntimeException(block.toString());
        }
    }

    private void hash(Block block) {
        List<Future<HashInfo>> futures = new ArrayList<>(10);

        long startTime = System.currentTimeMillis();
        for (int i = 1; i < 10; i++) {
            futures.add(executor.submit(new Miner(i, block.getId(), zeros)));
        }

        boolean isDone = false;
        do {
            for (Future<HashInfo> future : futures) {
                if (future.isDone()) {
                    try {
                        HashInfo hashInfo = future.get();
                        block.setHash(hashInfo.getHash());
                        block.setMagicNumber(hashInfo.getMagicNumber());
                        block.setCreatedBy(hashInfo.getMinerId());
                        isDone = true;
                        break;
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        } while (!(isDone));

        long endTime = System.currentTimeMillis();
        block.setGenerationTimeInSec((endTime - startTime) / 1000);

        for (Future<HashInfo> future : futures) {
            future.cancel(true);
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
