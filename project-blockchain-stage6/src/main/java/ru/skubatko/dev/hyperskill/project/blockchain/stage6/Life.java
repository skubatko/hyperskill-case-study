package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Life implements Runnable {
    private Blockchain blockchain;
    private Random random = new Random();
    private volatile boolean running = true;

    public Life(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void run() {
        while (running) {
            try {
                List<Account> accounts = blockchain.getAccounts();

                Account from;
                Account to;
                int amount;
                do {
                    from = accounts.get(random.nextInt(accounts.size()));
                    to = accounts.get(random.nextInt(accounts.size()));
                    amount = 10 + random.nextInt(90);
                } while (Objects.equals(to, from) || from.getBalance() < amount);

                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + amount);
                blockchain.addTransaction(from.getName(), to.getName(), amount);
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException e) {
                // empty
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
