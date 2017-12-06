package main;

import main.graph.AlgorithmType;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GridParser {

    public static ParseResult ParseGrid(String filename) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(filename));

        if(lines.size() < 3){
            throw new Exception("Unknown Format - Received less than 3 lines.");
        }

        AlgorithmType type;
        switch(lines.get(0)){
            case "IDS":
                type = AlgorithmType.IDS;
                break;
            case "A*":
                type = AlgorithmType.AStar;
                break;
            default:
                throw new Exception("Unknown algorithm \"" + lines.get(0) + "\"");
        }

        int n = Integer.parseInt(lines.get(1));

        Cells[][] grid = new Cells[n][n];

        lines.remove(1);
        lines.remove(0);

        for(int row=0;row<n;++row){
            String line = lines.get(row);
            for(int col=0;col<n;++col){
                switch(line.charAt(col)){
                    case 'S':
                        grid[row][col] = Cells.START;
                        break;
                    case 'G':
                        grid[row][col] = Cells.GOAL;
                        break;
                    case 'R':
                        grid[row][col] = Cells.ROAD;
                        break;
                    case 'D':
                        grid[row][col] = Cells.DIRT;
                        break;
                    case 'H':
                        grid[row][col] = Cells.HILL;
                        break;
                    case 'W':
                        grid[row][col] = Cells.WATER;
                        break;
                    default:
                        throw new Exception("Unsupported Cell Type '" + line.charAt(col) + "'");
                }
            }
        }

        return new ParseResult(type,grid);

    }
}
