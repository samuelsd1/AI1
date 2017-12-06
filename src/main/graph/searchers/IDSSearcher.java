package main.graph.searchers;

import main.graph.Graph;
import main.graph.GraphSearcher;
import main.graph.Solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDSSearcher<T> implements GraphSearcher<T> {
    private int maxDepth;

    public IDSSearcher(int maxDepth){
        this.maxDepth = maxDepth;
    }

    public boolean DLSVisit(Graph<T> graph, Map<T,T> parent, T src, T target, int maxDepth){
        if(src.equals(target)){
            return true;
        }
        if(maxDepth <= 0){
            return false;
        }

        List<T> neighbors =graph.getNeighbors(src);
        for(T v : neighbors){
            if(!parent.containsKey(v)){
                parent.put(v,src);
                boolean found = DLSVisit(graph,parent,v,target,maxDepth-1);
                if(found){
                    return true;
                }
            }
        }
        return false;
    }

    public Solution<T> search(Graph<T> graph){
        T start = graph.getStart();
        T goal = graph.getGoal();

        for(int depth=0;depth<maxDepth;++depth){
            Map<T,T> parent = new HashMap<>();
            boolean found = DLSVisit(graph,parent,start,goal,this.maxDepth);
            if(found){
                return new Solution<>(parent);
            }
        }
        return new Solution<T>(new HashMap<>());
    }
}
