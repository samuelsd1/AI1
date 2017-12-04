package graph;

public interface Heuristic<T> {
    double getHeuristic(T src, T target);
}
