public enum Cells {
    START   (1),
    GOAL    (1),
    ROAD    (1),
    DIRT    (3),
    HILL    (10),
    WATER   (Integer.MAX_VALUE)
    ;

    private final int cost;
    Cells(int cost){
        this.cost = cost;
    }
}
