package blockchain;

import java.util.ArrayList;

/**
 * This class adds blocks to the block chain.
 * Multiple instances of this class are needed to confirm a block
 */
public class Node {

    private BlockChain currentValidChain;
    private BlockChain proposedChain;
    private Block newBlock;
    private boolean chainValidated = false; //initialized to false when object is created

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
        this.createProposedChain();
        this.chainValidated = Node.validateBlockChain(this.proposedChain);
    }

    /**
     * Accesses the current valid chain stored in the node.
     * @return the current valid blockchain accepted by the network
     */
    public BlockChain getCurrentValidChain() {
        return this.currentValidChain;
    }

    /**
     * Accesses the updated blockchain currently proposed by the node.
     * @return the current blockchain proposed by the node
     */
    public BlockChain getProposedBlockChain() {
        return this.proposedChain;
    }

    /**
     * Accesses the current new block contained in the node.
     * @return The new block to be validated by the node
     */
    public Block getBlock() {
        return this.newBlock;
    }

    /**
     * Accesses the boolean indicating whether the node has created a valid chain.
     * @return a boolean indicating whether or not the proposed chain is valid
     */
    public boolean isChainValidated() {
        return this.chainValidated;
    }

    /**
     * Updates the currently accepted blockchain and the newly mined block so the Node instance
     * can be reused.
     * @param currentBlockChain The current valid blockchain, this us the same for every block on the network
     * @param minedBlock The newest mined block that needs to be verified by the Nodes on the network
     */
    public void updateNode(BlockChain currentBlockChain, Block minedBlock) {
        //currentValidChain is cloned here to avoid nodes changing each others BlockChains
        this.currentValidChain = new BlockChain(new ArrayList<Block>(currentBlockChain.getChain()));
        this.newBlock = minedBlock;
        this.createProposedChain();
        this.chainValidated = Node.validateBlockChain(this.proposedChain);
    }

    private void createProposedChain() {
        this.proposedChain = new BlockChain(this.currentValidChain.getChain());
        this.proposedChain.addBlock(this.newBlock);
    }

    /**
     * Method that ensures a BlockChain object is valid by checking and calculating the hash values of
     * each block.  This ensures the the chain and the blocks within it haven't been tampered with.
     * @param proposedChain A BlockChain object that has yet to be validated
     * @return a boolean value indicating validity of the Blockchain argument
     */
    public static boolean validateBlockChain(BlockChain proposedChain) {
        System.out.print("Validating proposed BlockChain...\r");
        ArrayList<Block> blockList = proposedChain.getChain();
        String lastHash = "0"; //this will always be the previous hash of the genesis block
        boolean valid = true; //chain assumed to be valid until proved otherwise
        for (Block currentBlock : blockList) {
            String calculatedBlockHash = currentBlock.calculateBlockHash();
            if (!(lastHash.equals(currentBlock.getPreviousHash())
                && currentBlock.getHash().equals(calculatedBlockHash))) {
                valid = false;
                break;
            }
            lastHash = calculatedBlockHash; //updates lastHash value for next loop iteration
        }
        return valid;
    }
}
