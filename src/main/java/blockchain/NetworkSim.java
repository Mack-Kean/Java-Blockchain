package blockchain;

import java.util.ArrayList;
/**
 * A wrapper class used to create a command line demonstration of the proof-of-work blockchain.
 */
public class NetworkSim {

    /**
     * The main class for the blockchain proof of concept.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Network blockChainNetwork = new Network();
        blockChainNetwork.addNode();
        blockChainNetwork.addMiner();
        blockChainNetwork.newTransaction("Genesis transaction");
        blockChainNetwork.addMiner();
        blockChainNetwork.addMiner();
        blockChainNetwork.addNode();
        blockChainNetwork.newTransaction("Second transaction");

    }

}
