public class ParseResult {
    private AlgorithmType type;
    private Cells[][] grid;

    public ParseResult(AlgorithmType type, Cells[][] grid){
        this.type = type;
        this.grid = grid;
    }

    public AlgorithmType getType(){
        return this.type;
    }

    public Cells[][] getGrid(){
        return this.grid;
    }
}
