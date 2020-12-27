package blockchain;

import java.util.ArrayList;

/**
 * This class will store an arrayList of blocks.
 * This class will contain a method to verify the chain stored within it is valid
 */
public class BlockChain {

    private ArrayList<Block> blockChain = new ArrayList<Block>();
    private String lastBlockHash;

    /**
     * Default constructor to create the BlockChain Object.
     */
    public BlockChain() {
    }

    /**
     * Accesses the blockChain ArrayList stored within the BlockChain class.
     * @return ArrayList of Blocks representing the BlockChain
     */
    public ArrayList<Block> getChain() {
        return this.blockChain;
    }

    /**
     * Adds a block to the ArrayList stored in the BlockChain class and stores the hash of the previoud block.
     * @param block the Block instance to be added to the class
     */
    public void addBlock(Block block) {
        this.blockChain.add(block);
        this.lastBlockHash = block.getPreviousHash();
    }

    /**
     * Accesses the Hash of the second most recent block added to the chain.
     * A String of "0" is returned for a BlockChain of size 1
     * @return the SHA-256 hash value of the second-last block in the chain.
     */
    public String getLastBlockHash() {
        return this.lastBlockHash;
    }

}
