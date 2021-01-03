package blockchain;

import java.util.Date;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * The class that creates the blocks that keep record of all trasactions in the blockchain.
 * This data in this class is immutable
 */
public class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce = 0;

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
        String dataToHash = this.previousHash
          + Long.toString(this.timeStamp)
          + Integer.toString(this.nonce)
          + this.data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    /**
     * Mines a block using a specified number of leading zeros required in the hash.
     * @param prefix the number of leading zeros required in the block hash
     * @return the unique 32 character block hash that conforms to the prefix
     */
    public String mineBlock(int prefix) {
        System.out.println("Mining Block...");
        Network.delay(100);
        System.out.println("Initial Block Hash: " + this.hash);
        System.out.println("Difficulty Level: " + Network.getDifficulty());
        System.out.println("Incrementing Nonce value:");
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            this.nonce++;
            this.hash = calculateBlockHash();
            System.out.print("Nonce = " + this.nonce + "\r");
        }
        System.out.print("\n");
        System.out.println("Mined Block Hash: " + this.hash);
        return hash;
    }

}
