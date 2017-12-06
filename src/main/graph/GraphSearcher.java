package main.graph;

public interface GraphSearcher<T> {
    Solution<T> search(Graph<T> graph);
}
