package blockchain;

import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {

    private static int difficulty = 3;
    private ArrayList<Miner> miners = new ArrayList<Miner>();
    private ArrayList<Node> nodes = new ArrayList<Node>();

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
}
