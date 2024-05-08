import java.io.*;
import java.util.*;

/**
 * A class that includes methods for reading a maze from a file,
 * solving that maze through a breadth first search, and then
 * writing the solution path to another file.
 * @author Lark Lai, L11
 * @version 4/3/2024
 */
public class MazeSolver {
    private final int[][] adjacent = {{0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}};
    private Maze maze;
    private boolean[][] traversed;

    public MazeSolver() {
    }

    public void readMaze(String s) throws InvalidMazeException, IOException {
        try {
            File f = new File(s);
            BufferedReader bfr = new BufferedReader(new FileReader(f));

            String name = bfr.readLine();

            if (name.isEmpty()) {
                throw new Exception("Name is invalid");
            }

            String input = bfr.readLine();

            if (!input.contains("Start: ")) {
                throw new Exception("Incorrect start formatting.");
            }
            String[] splitInput = input.substring(7).split("-");
            int[] start = {Integer.parseInt(splitInput[0]), Integer.parseInt(splitInput[1])};
            input = bfr.readLine();
            if (!input.contains("End: ")) {
                throw new Exception("Incorrect end formatting");
            }
            splitInput = input.substring(5).split("-");
            int[] end = {Integer.parseInt(splitInput[0]), Integer.parseInt(splitInput[1])};


            input = bfr.readLine();
            ArrayList<char[]> tempGrid = new ArrayList<char[]>();
            while (input != null) {
                input = input.replaceAll(",", "");
                if (input.matches(".*[^PW].*")) {
                    throw new Exception("Contains characters that are not 'P' or 'W'.");
                } else {
                    tempGrid.add(input.toCharArray());
                }

                input = bfr.readLine();
            }
            int width = tempGrid.get(0).length;
            char[][] grid = new char[tempGrid.size()][width];
            for (int i = 0; i < tempGrid.size(); i++) {
                if (tempGrid.get(i).length != width) {
                    throw new Exception("Not a rectangle.");
                } else {
                    grid[i] = tempGrid.get(i);
                }
            }

            char square = grid[start[0]][start[1]]; //throws exception
            // when start is not in the grid
            if (square != 'P') {
                throw new Exception("Start square is a wall.");
            }
            square = grid[end[0]][end[1]];

            if (square != 'P') {
                throw new Exception("End square is a wall.");
            }
            maze = new Maze(name, grid, start, end);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (Exception e) {
            throw new InvalidMazeException(e.getMessage());
        }

    }

    public void solveMaze() {
        try {
            //breadth first search for a path that connects start and end.
            //guaranteed that the first path that works will be (or will be tied for)
            // the shortest path to the end.
            ArrayDeque<int[][]> workingPaths = new ArrayDeque<int[][]>();

            int[][] currentPath = {maze.getStart()};
            workingPaths.addFirst(currentPath);

            char[][] grid = maze.getGrid();
            traversed = new boolean[grid.length][grid[0].length];
            while (!workingPaths.isEmpty()) {
                currentPath = workingPaths.removeLast();
                int[] currentSquare = currentPath[currentPath.length - 1];
                if (currentPath.length > grid.length * grid[0].length) {
                    return; //TODO: make this whatever behavior they answer with.
                }

                if (Arrays.equals(currentPath[currentPath.length - 1], maze.getEnd())) {
                    maze.setPath(currentPath);
                    return;
                }
                for (int[] offsets : adjacent) {
                    int nextY = currentSquare[0] + offsets[0];
                    int nextX = currentSquare[1] + offsets[1];
                    if (nextY >= 0 && nextX >= 0 && nextY < grid.length &&
                            nextX < grid[0].length && grid[nextY][nextX] == 'P' &&
                            !traversed[nextY][nextX]) {
                        //add all new valid paths to the working queue.
                        int[][] tempPath = new int[currentPath.length + 1][2];
                        System.arraycopy(currentPath, 0, tempPath, 0, currentPath.length);
                        tempPath[currentPath.length] = new int[]{nextY, nextX};
                        ;
                        traversed[nextY][nextX] = true;
                        workingPaths.addFirst(tempPath);
                    }
                }

            }
        } catch (Exception e) {
            return;
        }
    }

    public void writeSolution(String s) {
        try {
            File f = new File(s);
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
            bfw.write(maze.pathString());
            bfw.flush();
            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
