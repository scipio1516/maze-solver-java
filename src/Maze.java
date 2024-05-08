/**
 * A class that includes a maze and a path through it.
 * @author Lark Lai
 * @version 4/3/2024
 */
public class Maze {
    private final int[] start;
    private final int[] end;
    private final char[][] grid;
    private final String name;

    private int[][] path;

    public Maze(String name, char[][] grid, int[] start, int[] end) {
        this.name = name;
        this.grid = grid;
        this.start = start;
        this.end = end;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int[][] getPath() {
        return path;
    }

    public String pathString() {
        StringBuilder output = new StringBuilder(name + "\n");
        output.append("Moves: ").append(path.length).append("\n");
        output.append("Start\n");
        for (int[] ints : path) {
            output.append(String.format("%d-%d\n", ints[0], ints[1]));
        }
        return output.append("End").toString();
    }

    public void setPath(int[][] path) {
        this.path = path;
    }

    public String toString() {
        StringBuilder output = new StringBuilder(name + "\n");
        output.append(String.format("Start: %d-%d\n", start[0], start[1]));
        output.append(String.format("End: %d-%d\n", end[0], end[1]));
        for (char[] gridValue : grid) {
            for (int i = 0; i < gridValue.length; i++) {
                output.append(gridValue[i]);
                if (i != gridValue.length - 1) {
                    output.append(",");
                }
            }
            output.append("\n");
        }
        return output.toString();
    }
}
