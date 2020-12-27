package blockchain;

import java.util.ArrayList;

/**
 * This class will store an arrayList of blocks.
 * This class will contain a method to verify the chain stored within it is valid
 */
public class BlockChain {

    private ArrayList<Block> blockChain = new ArrayList<Block>();

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
     * Adds a block to the ArrayList stored in the BlockChain class.
     * @param block the Block instance to be added to the class
     */
    public void addBlock(Block block) {
        this.blockChain.add(block);
    }

}
