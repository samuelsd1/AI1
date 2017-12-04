package graph;

public interface GraphSearcher<T> {
    Solution<T> search(Graph<T> graph);
}
