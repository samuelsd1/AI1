package main.graph;

import java.util.Map;

public class Solution<T> {
    private Map<T,T> cameFrom;
    public Solution(Map<T,T> cameFrom){
        this.cameFrom = cameFrom;
    }

    public Map<T,T> getCameFrom(){
        return this.cameFrom;
    }
}
