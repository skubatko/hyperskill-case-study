package ru.skubatko.dev.hyperskill.project.blockchain.stage6;

public class BlockchainStage6App {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Life life = new Life(blockchain);
        new Thread(life).start();

        for (int i = 0; i < 15; i++) {
            blockchain.addBlock();
            blockchain.validate();
        }

        Block block = blockchain.getFirst();
        for (int i = 0; i < 15; i++) {
//			System.out.println(block);
            block = block.getNext();
        }

        life.shutdown();
        blockchain.shutdown();
    }

}
