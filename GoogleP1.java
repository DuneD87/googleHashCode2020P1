
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @brief Given a group of participants and a pizza store, we want to order the
 * maxium number of pizza possible, without passing a specified max.
 * @author Xavier Avivar, Roser Brugues, Joan Salo, Buenaventura Martinez
 */
public class GoogleP1 {
    
    private BestKnownSolution _actual;
    private BestKnownSolution _optim;
    
    public static void backtrackingAlgo(int maxSlices, int nPizzas, List<Integer> pizzas, Integer candidate, BestKnownSolution bestSolution, Integer nSolutions) {
        candidate = 0;
        while (candidate < nPizzas) {
            if (bestSolution.isAcceptable(candidate)) {
                bestSolution.addCandidate(candidate);
                if (bestSolution.isComplete()) {
                    nSolutions = nSolutions + 1;
                    candidate = nPizzas;
                } else {
                    backtrackingAlgo(maxSlices, nPizzas, pizzas, candidate, bestSolution, nSolutions);
                }
                bestSolution.removeCandidate(candidate);
            }
            candidate++;
        }
        Stack<Integer> solution = bestSolution.getSolution();
        //for (Integer p : solution) System.out.print(p + " ");
        //System.out.println();
    }

    public static void main(String[] args) {
        // Open the file, read its contents, and save it to a data structure
        String filename;
        Scanner keyb = new Scanner(System.in);
        filename = keyb.nextLine();
        List<Integer> pizzas = new ArrayList<>();
        int maxSlices = 0, nPizzas = 0;
        try {
            Scanner reader = new Scanner(new File(filename));
            maxSlices = reader.nextInt();
            nPizzas = reader.nextInt();
            for (int i = 0; i < nPizzas; i++) {
                pizzas.add(i, reader.nextInt());
            }
            reader.close();
            System.out.println("Max number of slices: " + maxSlices + "\nTypes of pizza: " + nPizzas);
            for (int i = 0; i < nPizzas; i++) {
                int nSlices = pizzas.get(i);
                System.out.println("[" + i + "]" + nSlices);
            }
            System.out.println();

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
        }
        Integer candidate = 0; //Index to our candidate list ( pizzas )
        Integer nSolutions = 0;
        BestKnownSolution bestSolution = new BestKnownSolution(nPizzas, maxSlices, pizzas);
        backtrackingAlgo(maxSlices, nPizzas, pizzas, candidate, bestSolution, nSolutions);
        System.out.println("Number of solutions found: " + nSolutions);
        List<Integer> solution = bestSolution.getSolution();
        for (Integer i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
//Aixo es una proba feta per en ventura
//Aixo es una segona proba feta per en ventura
//Això és una prova feta per la Roser.
