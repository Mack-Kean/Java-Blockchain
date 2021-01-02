package blockchain;

import java.util.Random;
import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {

    private static int difficulty = 1; //default difficulty unless otherwise specified
    private ArrayList<Miner> miners = new ArrayList<Miner>();
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private BlockChain networkChain = new BlockChain();

    /**
     * Default constructor.
     */
    public Network() {
    }

    /**
     * Modifier used to change mining difficulty across the network.
     * @param difficultyValue the number of leading zeros a valid hash must have
     */
    public static void setDifficulty(int difficultyValue) {
        Network.difficulty = difficultyValue;
    }

    /**
     * Accessor used to view the mining difficulty of the network.
     * @return the current mining difficult of the blockchain network
     */
    public static int getDifficulty() {
        return Network.difficulty;
    }

    /**
     * Creates a new miner object and adds it to the network's list of miners
     */
    public void addMiner() {
        Miner newMiner = new Miner();
        this.miners.add(newMiner);
    }

    /**
     * Creates a new node object and adds it to the network's list of nodes
     */
    public void addNode() {
        Node newNode = new Node();
        this.nodes.add(newNode);
    }

    private Miner successfulMiner() {
        /* This method picks a random miner from the list of miners to mine the newest block
            This is done because this simulation can't simulate the true competition of miners
            on a single peice of hardware */
        Random rand = new Random();
        int minerIndex = rand.nextInt(this.miners.size());
        return this.miners.get(minerIndex);
    }

    /**
     * Simulates a new transaction on the network.
     * @param transactionData the String representing transaction data being input into the network
     */
    public void newTransaction(String transactionData) {
        Miner transactionMiner = this.successfulMiner();
        if (this.networkChain.getChain().size() == 0) {
            //this is the case for the genesis block only
            transactionMiner.newTransaction(transactionData, "0");
        } else {
            transactionMiner.newTransaction(transactionData, this.networkChain.getLastBlockHash());
        }
    }
}
