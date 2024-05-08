import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InvalidMazeException, IOException {
        char[][] grid = {{'P', 'P'},
                         {'P', 'Z'}};
        int[] start = {0,0};
        int[] end = {1,1};
        int[][] path = {{0,0}, {0,1}, {1,1}};
        Maze m = new Maze("Test", grid, start, end);
        m.setPath(path);
        System.out.println(m.getName());
        System.out.println(m.pathString());
        System.out.println(m.toString());
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.readMaze("maze.txt");
        mazeSolver.solveMaze();
    }
}
