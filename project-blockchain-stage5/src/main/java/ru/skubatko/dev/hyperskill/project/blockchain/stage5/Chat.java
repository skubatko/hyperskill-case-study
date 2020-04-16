package ru.skubatko.dev.hyperskill.project.blockchain.stage5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chat implements Runnable {
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

    public void shutdown() {
        running = false;
    }
}
