package blockchain;

import java.util.ArrayList;
import java.util.Scanner;
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
        boolean validInput = false, inputInvalid;

        System.out.println("Welcome to the proof-of-work BlockChain simulation\n"
        + "This is a proof of concept meant to immitate a cryptocurrency BlockChain network\n"
        + "Most of the insiration was taken from the Bitcoin network\n");

        Scanner sc = new Scanner(System.in);

        do {
            inputInvalid = false;
            int nodeNum = 0;
            System.out.print("\nHow many Nodes would you like on the Network? ");
            try {
                nodeNum = Integer.parseInt(sc.nextLine()); //this is what will throw an exception if one is thrown
            } catch (Exception e) {
                inputInvalid = true;
                nodeNum = -1; //default value used in finally block
            } finally {
                if (nodeNum < 1 || inputInvalid) {
                    System.out.println("\nPlease enter a positive integer value!\n"
                    + "The network needs nodes in order to work");
                } else {
                    validInput = true;
                    for (int i = 1; i < nodeNum; i++) {
                        blockChainNetwork.addNode();
                    }
                }   
            }
        } while (!validInput);

        do {
            inputInvalid = false;
            int minerNum = 0;
            System.out.print("\nHow many Miners would you like on the Network? ");
            try {
                minerNum = Integer.parseInt(sc.nextLine()); //this is what will throw an exception if one is thrown
            } catch (Exception e) {
                inputInvalid = true;
                minerNum = -1; //default value used in finally block
            } finally {
                if (minerNum < 1 || inputInvalid) {
                    System.out.println("\nPlease enter a positive integer value!\n"
                    + "The network needs miners in order to work");
                } else {
                    validInput = true;
                    for (int i = 1; i < minerNum; i++) {
                        blockChainNetwork.addMiner();
                    }
                }   
            }
        } while (!validInput);

        validInput = false;

        do {
            inputInvalid = false;
            int difficulty = 0;
            System.out.print("\nEnter Mining Difficulty for the Network: ");
            try {
                difficulty = Integer.parseInt(sc.nextLine()); //this is what will throw an exception if one is thrown            
            } catch (Exception e) {
                inputInvalid = true;
                difficulty = -1; //default value used in finally block
            } finally {
                if (difficulty < 1 || inputInvalid) {
                    System.out.println("\nPlease enter a positive integer value!\n"
                    + "Keep in mind that bigger difficulty values take much more time to mine");
                } else {
                    validInput = true;
                    Network.setDifficulty(difficulty);
                }   
            }
        } while (!validInput);

        while (true) {
            System.out.println("\nEnter any type of data you want used to create a new transaction block\n"
            + "or enter 'quit' to end the program\n");
            String inputString = sc.nextLine();
            if (inputString.equalsIgnoreCase("quit")) {
                System.out.println("\nSimulator terminated");
                break;
            } else {
                blockChainNetwork.newTransaction(inputString);
            }
        }

        sc.close();

    }

}
