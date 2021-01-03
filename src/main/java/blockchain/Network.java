package blockchain;

import java.util.Random;
import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {

    private static int difficulty = 1; //default difficulty unless otherwise specified
    private static int transactionNumber = 0;
    private ArrayList<Miner> miners = new ArrayList<Miner>();
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private BlockChain networkChain = new BlockChain();
    private int minerNum = -1; //stores the integer value of the miner that mined the last block

    /**
     * Default constructor.
     * Adds 1 miner and 1 node by default
     */
    public Network() {
        this.addMiner();
        this.addNode();
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
        this.minerNum = minerIndex + 1; //stores the index of the 'winning' miner
        return this.miners.get(minerIndex);
    }

    /**
     * Simulates a new transaction on the network.
     * @param transactionData the String representing transaction data being input into the network
     */
    public void newTransaction(String transactionData) {
        Network.transactionNumber++;
        System.out.println("-----------------------------------------------------------------------------\n" 
        + "TRANSACTION #" + Network.transactionNumber + "\n"
        + "-----------------------------------------------------------------------------");
        Miner transactionMiner = this.successfulMiner();

        System.out.println("Sending Transaction data to all miners on the network...\n");
        Network.delay(2000);
        if (this.networkChain.getChain().size() == 0) {
            //this is the case for the genesis block only
            transactionMiner.newTransaction(transactionData, "0");
        } else {
            transactionMiner.newTransaction(transactionData, this.networkChain.getLastBlockHash());
        }

        Block newlyMinedBlock = transactionMiner.getNewestBlock();
        System.out.println("Block mined successfuly by Miner " + this.minerNum);

        if (nodeConsensus(newlyMinedBlock)) {
            //assumes there is at least 1 node in the network
            this.networkChain = this.nodes.get(0).getProposedBlockChain();
            System.out.println("Consensus was reached among all network nodes: Network chain updated\n"
            + "-----------------------------------------------------------------------------\n\n");
        } else {
            System.out.println("Consensus was not reached: Block has been rejected\n"
            + "-----------------------------------------------------------------------------\n\n");
        }
    }

    //private void displayBlockInfo(Block displayBlock) {
        
    //}

    private void initNodes(Block newlyMinedBlock) {
        int nodeNum = 1;
        System.out.println("\nInitilizing Nodes with new block...");
        Network.delay(1500);
        for (Node n : this.nodes) {
            System.out.print("Node " + nodeNum + " : ");
            n.updateNode(this.networkChain, newlyMinedBlock);
            nodeNum++;
            Network.delay(1500);
        }
        System.out.println("\n");
    }

    private boolean nodeConsensus(Block newlyMinedBlock) {
        //simulates the consensus of the ArrayList of nodes
        boolean result = true;
        this.initNodes(newlyMinedBlock);
        BlockChain previousChain = this.nodes.get(0).getProposedBlockChain(); //assumes there is at least 1 node in the network
        System.out.println("Starting Node Consensus protocol...");

        for (int i = 0; i < this.nodes.size(); i++) {
            Network.delay(600);
            if (!(this.nodes.get(i).isChainValidated())) {
                result = false; //if any node rejects the block
                break;
            } else {
                System.out.println("Node " + (i + 1) + " proposed chain is valid");
            }

            if (!(this.nodes.get(i).getProposedBlockChain().equals(previousChain))) {
                result = false;
                break;
            } else {
                System.out.println("Node " + (i + 1) + " has reached consensus wih previous Nodes");
            }
            System.out.print("\n");
            previousChain = this.nodes.get(i).getProposedBlockChain();
        }
        Network.delay(600);
        return result;
    }

    protected static void delay(int milliseconds) {
        // this method wraps around the try/catch seen below to add delay to the simulator
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
