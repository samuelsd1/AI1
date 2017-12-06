package main;

import main.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class GridGraph implements Graph<Position> {

    private Cells[][] map;
    private Position start;
    private Position goal;

    public GridGraph(Cells[][] map) throws Exception {
        this.map = map;
        this.start = null;
        this.goal = null;
        for (int row = 0; row < map.length; ++row) {
            for (int col = 0; col < map[0].length; ++col) {
                switch (map[row][col]) {
                    case START:
                        if (this.start != null) {
                            throw new Exception("Found two start symbols.");
                        }
                        this.start = new Position(row, col);
                        break;
                    case GOAL:
                        if (this.goal != null) {
                            throw new Exception("Found two goal symbols.");
                        }
                        this.goal = new Position(row, col);
                        break;
                    default:
                        break;
                }
            }
        }
    }


    private boolean isLegal(int row, int col) {
        return (row >= 0) && (row < this.map.length) && (col >= 0) && (col < this.map[0].length);

    }

    private boolean isLegal(Position p) {
        return isLegal(p.getRow(), p.getCol());
    }

    @Override
    public List<Position> getNeighbors(Position v) {
        boolean tag[][] = new boolean[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                tag[i][j] = false;
            }
        }

        for (int di = 0; di < 3; ++di) {
            for (int dj = 0; dj < 3; ++dj) {
                int row = v.getRow() + di - 1;
                int col = v.getCol() + dj - 1;

                if (!isLegal(row, col)) {
                    continue;
                }

                if (this.map[row][col] == Cells.WATER) {
                    continue;
                }

                tag[di][dj] = true;
            }
        }

        // stay where you are - not an option
        tag[1][1] = false;

        // Now - disable diagonal movement if I see water on the sides
        // up
        if (!tag[0][1]) {
            tag[0][0] = tag[0][2] = false;
        }
        // down
        if (!tag[2][1]) {
            tag[2][0] = tag[2][2] = false;
        }
        // left
        if (!tag[1][0]) {
            tag[0][0] = tag[2][0] = false;
        }
        // right
        if (!tag[1][2]) {
            tag[0][2] = tag[2][2] = false;
        }

        int[][] indices_to_iterate = {
                {1, 2}, // R
                {2, 2}, // RD
                {2, 1}, // D
                {2, 0}, // LD
                {1, 0}, // L
                {0, 0}, // LU
                {0, 1}, // U
                {0, 2}  // RU
        };

        List<Position> neighbors = new ArrayList<>();

        for (int[] index : indices_to_iterate) {
            int di = index[0];
            int dj = index[1];

            if (!tag[di][dj]) {
                continue;
            }

            int row = v.getRow() + di - 1;
            int col = v.getCol() + dj - 1;

            neighbors.add(new Position(row, col));
        }

        return neighbors;
    }

    @Override
    public Position getStart() {
        return this.start;
    }

    @Override
    public Position getGoal() {
        return this.goal;
    }

    @Override
    public double getCost(Position src, Position target) {
        int drow = src.getRow() - target.getRow();
        int dcol = src.getCol() - target.getCol();
        if (Math.abs(drow) + Math.abs(dcol) > 2) {
            return Integer.MAX_VALUE;
        }
        return map[src.getRow()][src.getCol()].cost();
    }
}
