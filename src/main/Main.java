package main;

import javafx.geometry.Pos;
import main.graph.GraphSearcher;
import main.graph.Heuristic;
import main.graph.Solution;
import main.graph.searchers.AStarSearcher;
import main.graph.searchers.IDSSearcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static String strByDelta(int drow, int dcol) {
        if (drow == 0 && dcol == 1) {
            return "R";
        } else if (drow == 1 && dcol == 1) {
            return "RD";
        } else if (drow == 1 && dcol == 0) {
            return "D";
        } else if (drow == 1 && dcol == -1) {
            return "LD";
        } else if (drow == 0 && dcol == -1) {
            return "L";
        } else if (drow == -1 && dcol == -1) {
            return "LU";
        } else if (drow == -1 && dcol == 0) {
            return "U";
        } else if (drow == -1 && dcol == 1) {
            return "RU";
        } else {
            throw new RuntimeException("Unknown delta " + drow + "," + dcol);
        }
    }

    public static List<String> backtraceSolution(Solution<Position> sol, Position goal) {
        Map<Position, Position> parent = sol.getCameFrom();
        Position curr = goal;
        Position prev = parent.get(goal);

        List<String> backtrace = new ArrayList<>();

        while (prev != null) {
            int drow = curr.getRow() - prev.getRow();
            int dcol = curr.getCol() - prev.getCol();
            String str = strByDelta(drow, dcol);

            backtrace.add(0, str);
            //System.out.println(curr + " came from " + prev + ", which is " + strByDelta(drow, dcol));

            curr = prev;
            prev = parent.get(prev);
        }
        return backtrace;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter input file path:");
        //String path = in.next();
        String path = "data/input.txt";
        ParseResult p = null;
        try {
            p = GridParser.ParseGrid(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting...");
            return;
        }

        int n = p.getGrid().length;
        /*
        main.Cells[][] map = p.getGrid();
        for(int row = 0;row<map.length;++row){
            for(int col=0;col<map[0].length;++col){
                System.out.print(" " + map[row][col]);
            }
            System.out.println();
        }
        System.out.println(p.getType());
        System.out.println("Hello World!");
        */


        GridGraph gridGraph = null;
        try {
            gridGraph = new GridGraph(p.getGrid());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        Heuristic<Position> airDistanceHeuristic = (s, t) -> {
            int d1 = s.getRow() - t.getRow();
            int d2 = s.getCol() - t.getCol();
            return Math.sqrt(d1 * d1 + d2 * d2);
        };

        //GraphSearcher<Position> searcher = new AStarSearcher<>(airDistanceHeuristic);
        GraphSearcher<Position> searcher = new IDSSearcher<>(n * 2);
        Solution<Position> sol = searcher.search(gridGraph);
        List<String> backtrace = backtraceSolution(sol, gridGraph.getGoal());

        for (String bt : backtrace) {
            System.out.print(bt + "->");
        }
    }
}