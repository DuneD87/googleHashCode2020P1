
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @brief Backtracking algorithm to find the best solution for ordering pizzas
 * @author Xavier Avivar, Roser Brugues, Joan Salo, Buenaventura Martinez
 */
public class BestKnownSolution {
    /**DATASET**/
    private final List<Integer> _pizzas;//!< List of avaiable pizzas
    private final int _nPizzas;//!< Number of types of pizza
    private final int _maxSlices;//!< Max slices allowed
    
    /**CONTROL ATRIBUTES**/
    private int _score;//!< Current score
    private Stack<Integer> _pizzasChosen;//!< List of the pizzas chosen
    private List<Boolean> _canBeChoosen;
    //private int _recursiveLevel;//!< Current level of recursion
    
    /**
     * @brief Class constructor
     */
    public BestKnownSolution(int nPizzas, int maxSlices, List<Integer> pizzas) {
        _pizzasChosen = new Stack<>();
        _nPizzas = nPizzas;
        _maxSlices = maxSlices;
        _pizzas = pizzas;
        _score = 0;
        _canBeChoosen = new ArrayList<>(10);
        for (int i = 0; i < _nPizzas; i++) {
            _canBeChoosen.add(true);
        }
    }
    
    /**
     * @brief Copy constructor
     * @post Copies all the variables from the old solution to the new solution
     */
    /*public BestKnownSolution(BestKnownSolution oldSolution) {
        _pizzasChosen = new Stack<>();
    }*/
    
    /**
     * @brief Tells us if the candidate is acceptable
     * @return True if the number of slices of this candidate and the current number of slices is equal or lower than maxSlices, false otherwise
     */
    public boolean isAcceptable(int candidate) {
        boolean acceptable = false;
        if (_pizzas.get(candidate) + _score <= _maxSlices && _canBeChoosen.get(candidate) == true) acceptable = true;
        return acceptable;
    }
    
    /**
     * @brief Tells us if the solution is complete
     * @return True if the recursive level has tried all candidates, false otherwise
     */
    public boolean isComplete() {
        boolean complete = false;
        if (_pizzasChosen.size() == _nPizzas) complete = true;
        return complete;
    }
    
    /**
     * @brief Adds a candidate to our solution
     * @post A candidate has been added to our solution, the total score has been updated and the recursive level has been increased by 1
     */
    public void addCandidate(int candidate) {
        _pizzasChosen.push(candidate);
        _canBeChoosen.set(candidate, false);
        _score += _pizzas.get(candidate);
        //_recursiveLevel++;
    }
    
    /**
     * @brief Removes a candidate from our solution
     * @post A candidate has been remove from our solution, the total score has been updated and the recursive level has ben decreased by 1
     */
    public void removeCandidate(int candidate) {
        //_recursiveLevel--;
          _score -= _pizzas.get(candidate);
           _canBeChoosen.set(candidate, true);
        _pizzasChosen.pop();
      
    }
    
    /**
     * @brief Gives us the problem solution
     * @return A list of integers telling us the type of pizzas chosen
     */
    public Stack<Integer> getSolution() {
        return _pizzasChosen;
    }
    
    /**
     * @brief Tells us if the current solution is better than the old one
     * @return True if the solution is better, false otherwise
     */
    public boolean isBetter(BestKnownSolution old) {
        boolean isBetter = false;
        if (_score > old._score) isBetter = true;
        return isBetter;
    }
}
