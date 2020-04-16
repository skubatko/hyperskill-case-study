package ru.skubatko.dev.hyperskill.project.blockchain.stage5;

public class BlockchainStage5App {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Chat chat = new Chat(blockchain);
        new Thread(chat).start();

        for (int i = 0; i < 5; i++) {
            blockchain.addBlock();
            blockchain.validate();
        }

        Block block = blockchain.getFirst();
        for (int i = 0; i < 5; i++) {
//			System.out.println(block);
            block = block.getNext();
        }

        chat.shutdown();
        blockchain.shutdown();
    }
}
