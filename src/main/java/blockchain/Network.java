package blockchain;

import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {
    /**
     * The main class for the blockchain proof of concept.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        BlockChain bc = new BlockChain();

        Block firstBlock = new Block("First transaction", "0");
        bc.addBlock(firstBlock);
        Block secondBlock = new Block("Second transaction", firstBlock.getHash());
        bc.addBlock(secondBlock);
        Block thirdBlock = new Block("Third transaction", secondBlock.getHash());
        bc.addBlock(thirdBlock);

        ArrayList<Block> chainTester = bc.getChain();

        for (Block b : chainTester) {
            System.out.println(b.getHash());
        }

    }
}
