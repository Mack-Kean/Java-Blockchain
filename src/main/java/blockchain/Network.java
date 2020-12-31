package blockchain;

import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {

    public static final int DIFFICULTY = 4;

    /**
     * The main class for the blockchain proof of concept.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        BlockChain bc = new BlockChain();
        Node node = new Node();
        Block proposedBlock;

        Miner blockMiner = new Miner("Initial Transaction", "0");
        proposedBlock = blockMiner.getNewestBlock(); //this is where the actual block is mined
        node.updateNode(bc, proposedBlock);
        if (node.isChainValidated()) {
            System.out.println("Genesis Node validated");
            bc = node.getProposedBlockChain();
        }
        
        blockMiner.newTransaction("Second Transaction", bc.getLastBlockHash());
        proposedBlock = blockMiner.getNewestBlock(); //this is where the actual block is mined
        node.updateNode(bc, proposedBlock);
        if (node.isChainValidated()) {
            System.out.println("Second Node validated");
            bc = node.getProposedBlockChain();
        }
        
        blockMiner.newTransaction("Third Transaction", bc.getLastBlockHash());
        proposedBlock = blockMiner.getNewestBlock(); //this is where the actual block is mined
        node.updateNode(bc, proposedBlock);
        if (node.isChainValidated()) {
            System.out.println("Third Node validated");
            bc = node.getProposedBlockChain();
        }

        blockMiner.newTransaction("Faulty Transaction", bc.getLastBlockHash());
        proposedBlock = blockMiner.getNewestBlock(); //this is where the actual block is mined

        proposedBlock.setData("Changed Transaction Data"); //data has been tampered with

        node.updateNode(bc, proposedBlock);
        if (node.isChainValidated()) {
            System.out.println("Third Node validated");
            bc = node.getProposedBlockChain();
        } else {
            System.out.println("Block has been rejected and not added to the chain");
        }

        ArrayList<Block> chainTester = bc.getChain();

        for (Block b : chainTester) {
            System.out.println("Hash Value: " + b.getHash());
            System.out.println("Previous Hash Value: " + b.getPreviousHash());
            System.out.println("Nonce Value: " + b.getNonce()); //shows how much work was needed to mine the block
        }

    }
}
