package blockchain;

/**
 * This class adds blocks to the block chain.
 * Multiple instances of this class are needed to confirm a block
 */
public class Node {

    private BlockChain currentValidChain;
    private BlockChain proposedChain;
    private Block newBlock;

    /**
     * Default constructor.
     */
    public Node() {

    }

    /**
     * Constructor that initializes the Node with the current valid blockchain and the
     * newly mined block that needs to be verified.
     * @param currentBlockChain The current valid blockchain, this us the same for every block on the network
     * @param minedBlock The newest mined block that needs to be verified by the Nodes on the network
     */
    public Node(BlockChain currentBlockChain, Block minedBlock) {
        this.currentValidChain = currentBlockChain;
        this.newBlock = minedBlock;
    }
}
