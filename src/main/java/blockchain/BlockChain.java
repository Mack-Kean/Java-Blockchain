package blockchain;

import java.util.ArrayList;

/**
 * This class will store an arrayList of blocks.
 * This class will contain a method to verify the chain stored within it is valid
 */
public class BlockChain {

    private ArrayList<Block> blockChain = new ArrayList<Block>();
    private Block lastBlock;

    /**
     * Default constructor to create the BlockChain Object.
     */
    public BlockChain() {
    }

    /**
     * Constructor that takes an ArrayList of blocks to create the BlockChain object.
     * @param blockChainList the ArrayList member object that contains all blocks in the
     * newly created chain
     */
    public BlockChain(ArrayList<Block> blockChainList) {
        this.blockChain = blockChainList;
    }

    /**
     * Accesses the blockChain ArrayList stored within the BlockChain class.
     * @return ArrayList of Blocks representing the BlockChain
     */
    public ArrayList<Block> getChain() {
        return this.blockChain;
    }

    /**
     * Adds a block to the ArrayList stored in the BlockChain class and stores last block in the chain.
     * @param block the Block instance to be added to the class
     */
    public void addBlock(Block block) {
        this.blockChain.add(block);
        this.lastBlock = block;
    }

    /**
     * Returns the hash value of the last block in the chain.
     * @return The SHA-256 hash value of the previous block stored in the last block in
     * the chain
     */
    public String getLastBlockHash() {
        return this.lastBlock.getHash();
    }

    /**
     * If the object is a BlockChain, it's ArrayList member is tested
     * against the current BlockChain ArrayList member.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BlockChain)) {
            return false;
        }

        BlockChain otherChain = (BlockChain) o;
        ArrayList<Block> otherChainList = otherChain.getChain();
        //the ArrayList equals() method is used to check that the chains are equal
        return otherChainList.equals(this.getChain());
    }

}
