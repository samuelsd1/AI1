package main.graph.searchers;

import main.graph.Graph;
import main.graph.GraphSearcher;
import main.graph.Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDSSearcher<T> implements GraphSearcher<T> {
    private int maxDepth;

    public IDSSearcher(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    private List<T> DLSVisit(Graph<T> graph, T src, T target, int maxDepth) {
        if (maxDepth == 0 && src.equals(target)) {
            List<T> path = new ArrayList<>();
            path.add(target);
            return path;
        }
        if (maxDepth <= 0) {
            return null;
        }
        List<T> neighbors = graph.getNeighbors(src);
        for (T v : neighbors) {
            List<T> path = DLSVisit(graph, v, target, maxDepth - 1);
            if (path != null) {
                path.add(src);
                return path;
            }
        }
        return null;
    }

    public Solution<T> search(Graph<T> graph) {
        for (int depth = 1; depth < maxDepth; ++depth) {
            List<T> path = DLSVisit(graph, graph.getStart(), graph.getGoal(), depth);
            if (path != null) {
                Map<T, T> parent = new HashMap<>();
                for (int i = 1; i < path.size(); ++i) {
                    parent.put(path.get(i - 1), path.get(i));
                }
                return new Solution<>(parent);
            }
        }
        return new Solution<T>(new HashMap<>());
    }
}
