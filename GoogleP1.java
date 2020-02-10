
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * @brief Given a group of participants and a pizza store, we want to order the
 * maxium number of pizza possible, without passing a specified max.
 * @author Xavier Avivar, Roser Brugues, Joan Salo, Buenaventura Martinez
 */
public class GoogleP1 {

    private static BestKnownSolution _act;
    private static BestKnownSolution _better;
    private static boolean _found;
    private static boolean _isPerfect;
    static String outputDir = "../Output/"; 

    public static void backtrackingAlgo(int maxSlices, int nPizzas, List<Integer> pizzas, Integer candidate,  Integer nSolutions) {
        //System.out.println("caca");
        while (candidate >= 0 && !_isPerfect) {
            if (_act.isAcceptable(candidate)) {
                if(!_found) _act.addCandidate(candidate);
                else if(_act.couldBeBetter(candidate, _better)) _act.addCandidate(candidate);
                if (_act.isPerfect()) {
                    _isPerfect = true;
                    _better = new BestKnownSolution(_act); // We save the first solution found as better
                    //System.out.println("perfect solution");
                    _found = true;
                }
                if (!_act.isComplete(candidate)) {
                    backtrackingAlgo(maxSlices, nPizzas, pizzas, candidate, nSolutions);
                }
                if (_act.isComplete(candidate) && !_found) {
                    _better = new BestKnownSolution(_act); // We save the first solution found as better
                    _found = true;
                }
                if (_act.isComplete(candidate) && _found && _act.isBetter(_better)) {
                    _better = new BestKnownSolution(_act);
                }

                _act.removeCandidate();
            }
            candidate--;
        }
    }

    public static void main(String[] args) {
        // Open the file, read its contents, and save it to a data structure
        //String[] filenames={"a_example", "b_small", "c_medium", "d_quite_big", "e_also_big"};
        String fileName="e_also_big";
            //List<Integer> input = new ArrayList<>();
            //List<Integer> output = new ArrayList<>();
            List<Integer> pizzas = new ArrayList<>();
            int maxSlices = 0, nPizzas = 0;
            _found = false;
            _isPerfect = false;
            try {
                Scanner reader = new Scanner(new File(fileName + ".in"));
                maxSlices = reader.nextInt();
                nPizzas = reader.nextInt();
                for (int i = 0; i < nPizzas; i++) {
                    pizzas.add(i, reader.nextInt());
                }
                reader.close();
                //System.out.println("Max number of slices: " + maxSlices + "\nTypes of pizza: " + nPizzas);
                int total = 0;
                for (int i = 0; i < nPizzas; i++) {
                    int nSlices = pizzas.get(i);
                    //System.out.println("[" + i + "]" + nSlices);
                    total += nSlices;
                }
                //System.out.println("Total: " + total);

            } catch (Exception e) {
                System.err.format("Exception occurred trying to read '%s'.", fileName);
            }
            Integer candidate = nPizzas - 1; //Index to our candidate list ( pizzas )
            Integer nSolutions = 0;
            _act = new BestKnownSolution(nPizzas, maxSlices, pizzas);
            backtrackingAlgo(maxSlices, nPizzas, pizzas, candidate, nSolutions);

            if (_found) {
                System.out.println("Solutions found with score: " + _better.getScore());
                List<Integer> solution = _better.getSolution();
                Collections.reverse(solution);
                System.out.println(fileName);
                int j = 0;
                try( PrintWriter outputFile = new PrintWriter(fileName+".out", "UTF-8")){
                    System.out.println(solution.size());
                    outputFile.println(solution.size());
                    for (Integer i : solution) {
                        System.out.print(i + " ");
                        outputFile.print(i + " ");
                        j++;
                    }
                }catch (Exception e){
                    System.err.format("Exception occurred trying to write output");
                }

                System.out.flush();
                //System.out.println();
            }
            else{
                System.out.println("No he trobat solució");
            }

    }
}
//Aixo es una proba feta per en ventura
//Aixo es una segona proba feta per en ventura3453453