package blockchain;

/**
 * This class represents someone trying to mine a block and add it to the chain.
 */
public class Miner {

    private Block minedBlock;

    /**
     * Default constructor.
     */
    public Miner() {
    }

    /**
     * Constructor of a Miner object takes in the data it needs to create a new block for the Network.
     * Allows for the block to be mined to be set upon object instatiation
     * @param transactionData the transaction data String needed to create a new block
     * @param previousBlockHash the hash of the previous block in the chain
     */
    public Miner(String transactionData, String previousBlockHash) {
        this.newTransaction(transactionData, previousBlockHash);
    }

    /**
     * Creates a Block object that has yet to be mined.
     * @param transactionData the transaction data String needed to create a new block
     * @param previousBlockHash the hash of the previous block in the chain
     */
    public void newTransaction(String transactionData, String previousBlockHash) {
        this.minedBlock = new Block(transactionData, previousBlockHash);
    }

    /**
     * This function returns the newly created block ONLY after the mineBlock() method is executed.
     * This will acts as an extremely rudimentary 'proof of work' because the block will not
     * be returned until the mining method stops executing
     * @return The newly mined Block to be added to the BlockChain
     */
    public Block getNewestBlock() {
        this.minedBlock.mineBlock(Network.getDifficulty());
        return this.minedBlock;
    }

}
