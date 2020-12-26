import java.util.Date;

/**
 * The class that creates the blocks that keep record of all trasactions in the blockchain.
 * This data in this class is immutable
 */
public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    /**
     * The constructor for a new Block.
     * @param transactionData the transaction data stored in the block and used to generate a unique hash
     * @param previousBlockHash the SHA-256 hash string of the previous block in the chain
     */
    public Block(String transactionData, String previousBlockHash) {
        this.data = transactionData;
        this.previousHash = previousBlockHash;
        this.timeStamp = new Date().getTime(); //sets the timeStamp to the current time
        this.hash = calculateBlockHash();
    }

    /**
     * Accesses the SHA-256 hash value of the current block.
     * @return Unique 32 character string
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Accesses the SHA-256 hash value of the previous block in the chain.
     * @return Unique 32 character string
     */
    public String getPreviousHash() {
        return this.previousHash;
    }

    /**
     * Accesses the transaction data.
     * @return a String storing the block transaction data that was used in the hash function
     */
    public String getData() {
        return this.data;
    }

    /**
     * Accesses the time the block was created.
     * @return a long representing the unique timeStamp of the Block
     */
    public long getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Accesses the number used once for mining the block.
     * @return a long representing the number used once.
     */
    public int getNonce() {
        return this.nonce;
    }

    /**
     * Calculates the blocks unique hash value using the SHA-256 hashing algorithm.
     * @return a unique 32 character string that should be equal to this.hash
     */
    public String calculateBlockHash() {
        return ""; //this is where the block hash will be calculated
    }
}
