package graph.searchers;

import graph.Graph;
import graph.GraphSearcher;
import graph.Heuristic;
import graph.Solution;

public class AStarSearcher<T> implements GraphSearcher<T> {
    private Heuristic<T> heuristic;

    public AStarSearcher(Heuristic<T> heuristic){
        this.heuristic = heuristic;
    }

    public Solution<T> search(Graph<T> graph){
        return null;
    }
}
