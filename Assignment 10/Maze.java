/**
 * Abdul-Malik Marikar
 * 101042166
 */

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Maze {
    private static final byte OPEN = 0;
    private static final byte WALL = 1;
    private static final byte VISITED = 2;

    private int rows, columns;
    private byte[][] grid;

    // A constructor that makes a maze of the given size
    public Maze(int r, int c) {
        rows = r;
        columns = c;
        grid = new byte[r][c];
        for (r = 0; r < rows; r++) {
            for (c = 0; c < columns; c++) {
                grid[r][c] = WALL;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // Return true if a wall is at the given location, otherwise false
    public boolean wallAt(int r, int c) {
        return grid[r][c] == WALL;
    }

    // Return true if this location has been visited, otherwise false
    public boolean visitedAt(int r, int c) {
        return grid[r][c] == VISITED;
    }

    // Put a visit marker at the given location
    public void placeVisitAt(int r, int c) {
        grid[r][c] = VISITED;
    }

    // Remove a visit marker from the given location
    public void removeVisitAt(int r, int c) {
        grid[r][c] = OPEN;
    }

    // Put a wall at the given location
    public void placeWallAt(int r, int c) {
        grid[r][c] = WALL;
    }

    // Remove a wall from the given location
    public void removeWallAt(int r, int c) {
        grid[r][c] = 0;
    }

    // Carve out a maze
    public void carve() {
        int startRow = (int) (Math.random() * (rows - 2)) + 1;
        int startCol = (int) (Math.random() * (columns - 2)) + 1;
        carve(startRow, startCol);
    }

    // Directly recursive method to carve out the maze
    public void carve(int r, int c) {
        ArrayList<Integer> rowOffsets = new ArrayList<Integer>(Arrays.asList(-1, 1, 0, 0));
        ArrayList<Integer> colOffsets = new ArrayList<Integer>(Arrays.asList(0, 0, -1, 1));
        ArrayList<Integer> randomNumbers = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));
        if (r <= 0 || c <= 0 || r >= (getRows() - 1) || c >= (getColumns() - 1))
            return; //do nothing
        else if (grid[r][c] == OPEN) {
            return; //do nothing
        } else {
            byte up = grid[r - 1][c];
            byte down = grid[r + 1][c];
            byte left = grid[r][c - 1];
            byte right = grid[r][c + 1];
            if ((up == WALL && down == WALL && left == WALL && right == WALL) || (down == WALL && left == WALL && right == WALL) || (up == WALL && down == WALL && right == WALL) || (up == WALL && down == WALL && left == WALL) || up == WALL && left == WALL && right == WALL) {
                removeWallAt(r, c);
                Collections.shuffle(randomNumbers);//shuffles the ways list to randomize order
                carve(r + rowOffsets.get(randomNumbers.get(0)), c + colOffsets.get(randomNumbers.get(0)));
                carve(r + rowOffsets.get(randomNumbers.get(1)), c + colOffsets.get(randomNumbers.get(1)));
                carve(r + rowOffsets.get(randomNumbers.get(2)), c + colOffsets.get(randomNumbers.get(2)));
                carve(r + rowOffsets.get(randomNumbers.get(3)), c + colOffsets.get(randomNumbers.get(3)));
                Collections.shuffle(randomNumbers);//shuffles again
            }
            return;
        }
    }

    // Determine the longest path in the maze from the given start location
    public ArrayList<Point2D> longestPath() {
        ArrayList<Point2D> longestpath = new ArrayList<Point2D>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (longestPathFrom(i, j).size() > longestpath.size())
                    longestpath = longestPathFrom(i, j);
            }
        }
        return longestpath;
    }

    // Determine the longest path in the maze from the given start location
    public ArrayList<Point2D> longestPathFrom(int r, int c) {
        ArrayList<Point2D> path = new ArrayList<Point2D>();

        // Write your code here
        //Checks if there is an open space
        if(!wallAt(r,c)){
            //Checks if the space is unvisited
            if(!visitedAt(r,c)) {
                //Shows we have visited that space
                placeVisitAt(r,c);
                //Creates array list holding path for all directions
                ArrayList<Point2D> downpath = longestPathFrom(r + 1, c);
                ArrayList<Point2D> rightpath = longestPathFrom(r, c + 1);
                ArrayList<Point2D> uppath = longestPathFrom(r - 1, c);
                ArrayList<Point2D> leftpath = longestPathFrom(r, c - 1);

                //Assigns the largest path to the variable path
                if (rightpath.size() >= downpath.size() && rightpath.size() >= leftpath.size() && rightpath.size() >= uppath.size()) {
                    path = rightpath;
                }
                else if(leftpath.size()>=downpath.size() && leftpath.size()>=rightpath.size() && leftpath.size()>=uppath.size()){
                    path=leftpath;
                }
                else if(downpath.size()>=rightpath.size() && downpath.size()>=leftpath.size() && downpath.size()>=uppath.size()){
                    path = downpath;
                }
                else if(uppath.size() >= downpath.size() && uppath.size() >= leftpath.size() && uppath.size() >= rightpath.size()){
                    path=uppath;
                }


                //Now it adds the point to the path and removes the visit
                removeVisitAt(r,c);
                path.add(new Point2D(r,c));

            }
        }
        return path;
    }
}

