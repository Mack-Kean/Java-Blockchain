package blockchain;

import java.util.ArrayList;
/**
 * This class stores the entire blockchain network.
 * This includes multiple nodes which mine and verify blocks
 */
public class Network {

    public static final int DIFFICULTY = 4;
    private ArrayList<Miner> miners = new ArrayList<Miner>();
    private ArrayList<Node> nodes = new ArrayList<Node>();

    /**
     * Default constructor.
     */
    public Network() {
    }

}
