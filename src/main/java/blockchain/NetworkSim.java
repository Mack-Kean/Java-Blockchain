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
        boolean validInput = false;
        String errorMessage = "Please enter a positive integer value!\n"
            + "Keep in mind that bigger difficulty values take much more time to mine";

        System.out.println("Welcome to the proof-of-work BlockChain simulation\n"
        + "This is a proof of concept meant to immitate a cryptocurrency BlockChain network\n"
        + "Most of the insiration was taken from the Bitcoin network\n");

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter Mining Difficulty: ");
            try {
                int difficulty = sc.nextInt(); //this is what will throw an exception if one is thrown
                if (difficulty < 1) {
                    System.out.println(errorMessage);
                } else {
                    validInput = true;
                }                
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        sc.close();


    }

}
