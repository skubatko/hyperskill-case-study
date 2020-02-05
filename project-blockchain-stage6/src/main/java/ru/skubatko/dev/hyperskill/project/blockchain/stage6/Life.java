package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Life implements Runnable {
    private Blockchain blockchain;
    private ExecutorService executor;
    private Random random = new Random();
    private volatile boolean running = true;

    public Life(Blockchain blockchain) {
        this.blockchain = blockchain;
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void run() {
        while (running) {
            executor.submit(this::doTransaction);
        }
        executor.shutdownNow();
    }

    private void doTransaction() {
        try {
            List<Account> accounts = blockchain.getAccounts();
            Account from = accounts.get(random.nextInt(accounts.size()));
            Account to;
            do {
                to = accounts.get(random.nextInt(accounts.size()));
            } while (Objects.equals(to, from));
            int amount = 10 + random.nextInt(90);
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            blockchain.addTransaction(from.getName(), to.getName(), amount);
            Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
            // empty
        }
    }

    public void shutdown() {
        running = false;
    }
}
