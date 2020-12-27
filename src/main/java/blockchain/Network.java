package blockchain;

import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {

    public static final int DIFFICULTY = 5;

    /**
     * The main class for the blockchain proof of concept.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        BlockChain bc = new BlockChain();

        Miner blockMiner = new Miner("Initial Transaction", "0");
        bc.addBlock(blockMiner.getNewestBlock()); //this is where the actual block is mined
        blockMiner.newTransaction("Second Transaction", bc.getLastBlockHash());
        bc.addBlock(blockMiner.getNewestBlock());
        blockMiner.newTransaction("Third Transaction", bc.getLastBlockHash());
        bc.addBlock(blockMiner.getNewestBlock());

        ArrayList<Block> chainTester = bc.getChain();

        for (Block b : chainTester) {
            System.out.println("Hash Value: " + b.getHash());
            System.out.println("Nonce Value: " + b.getNonce()); //shows how much work was needed to mine the block
        }

    }
}
