package graph;

import java.util.List;

public interface Graph<T> {
    List<T> getNeighbors(T v);

    T getStart();

    T getGoal();

    T getCost(T src, T target);
}
