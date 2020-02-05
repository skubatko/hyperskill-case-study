package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

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
    private AtomicLong counter = new AtomicLong(1L);
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private ExecutorService executor;
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Miner> miners = new ArrayList<>();

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

        accounts.add(new Account("Nick"));
        accounts.add(new Account("Bob"));
        accounts.add(new Account("Alice"));
        accounts.add(new Account("ShoesShop"));
        accounts.add(new Account("FastFood"));
        accounts.add(new Account("Director1"));
        accounts.add(new Account("Director2"));
        accounts.add(new Account("CarPartsShop"));
        accounts.add(new Account("GamingShop"));
        accounts.add(new Account("BeautyShop"));

        for (int i = 0; i < 10; i++) {
            miners.add(new Miner(i + 1));
        }
        accounts.addAll(miners);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addBlock() {
        boolean isNotFirst = last != null;

        Block block = new Block();

        int id = isNotFirst ? last.getId() + 1 : 1;
        block.setId(id);

        if (isNotFirst) {
            synchronized (this) {
                block.getTransactions().addAll(transactions);
                transactions.clear();
            }
        }

        long transactionId = isNotFirst
                             ? block.getTransactions().stream()
                                       .mapToLong(Transaction::getId)
                                       .max()
                                       .orElse(last.getMaxTransactionId())
                             : 0L;
        block.setMaxTransactionId(transactionId);

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

    public void addTransaction(String from, String to, int amount) {
        Transaction transaction = new Transaction(from, to, amount);
        transaction.setId(counter.getAndIncrement());
        transaction.setPublicKey(publicKey);
        sign(transaction);
        transactions.add(transaction);
    }

    private void sign(Transaction transaction) {
        try {
            Signature rsa = Signature.getInstance("SHA1withRSA");
            rsa.initSign(privateKey);
            String subjectToSign = transaction.getId() + transaction.toString();
            rsa.update(subjectToSign.getBytes());
            transaction.setSignature(rsa.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }

    private void adjustZeros(Block block) {
        if (zeros > 3) {
            zeros--;
            block.setZerosStatus(-1);
            return;
        }

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

            maxId = block.getMaxTransactionId();
            block = block.getNext();
        }
    }

    private void validateMessageSignature(Block block) {
        List<Transaction> transactions = block.getTransactions();

        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            boolean isVerified = transactions.stream()
                                         .peek(m -> {
                                             try {
                                                 signature.initVerify(m.getPublicKey());
                                             } catch (InvalidKeyException e) {
                                                 // empty
                                             }
                                         })
                                         .peek(m -> {
                                             try {
                                                 signature.update((m.getId() + m.toString()).getBytes());
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
        long wrongId = block.getTransactions().stream()
                               .mapToLong(Transaction::getId)
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
            Miner miner = miners.get(i);
            miner.setBlockId(block.getId());
            miner.setZeros(zeros);
            futures.add(executor.submit(miner));
        }

        boolean isDone = false;
        do {
            for (Future<HashInfo> future : futures) {
                if (future.isDone()) {
                    try {
                        HashInfo hashInfo = future.get();
                        block.setHash(hashInfo.getHash());
                        block.setMagicNumber(hashInfo.getMagicNumber());
                        int minerId = hashInfo.getMinerId();
                        block.setCreatedBy(minerId);
                        Miner miner = miners.get(minerId - 1);
                        miner.setBalance(miner.getBalance() + 100);
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
