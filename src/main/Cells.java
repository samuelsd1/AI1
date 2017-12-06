package main;

public enum Cells {
    START   (1),
    GOAL    (1),
    ROAD    (1),
    DIRT    (3),
    HILL    (10),
    WATER   (Integer.MAX_VALUE)
    ;

    private final double cost;
    Cells(double cost){
        this.cost = cost;
    }
    public double cost(){
        return this.cost;
    }
}
