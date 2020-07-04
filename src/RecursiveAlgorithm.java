import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Parameter;
import java.util.BitSet;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class RecursiveAlgorithm {

    private BitSet[] maze;
    private int height;
    private int width;
    private Pair<Integer, Integer> start;
    private Pair<Integer, Integer> end;

    private BitSet[] visited;
    private BitSet[] solution;

    RecursiveAlgorithm(Labyrinth labyrinth) {
        this.maze = labyrinth.maze;
        this.height = labyrinth.height;
        this.width = labyrinth.width;
        this.start = labyrinth.start;
        this.end = labyrinth.end;
        buildVisitedSolution();
    }

    private void buildVisitedSolution() {
        visited = new BitSet[height];
        solution = new BitSet[height];
        for (int i=0; i<height; i++ ) {
            visited[i] = new BitSet(width);
            solution[i] = new BitSet(width);
        }
    }

    public void solve() {
        boolean b = recursiveSolve(start.getFirst(), start.getSecond());
    }

    private boolean recursiveSolve(int x, int y) {

        if (x == end.getFirst() && y == end.getSecond()){// If you reached the end
            solution[x].set(y,true);
            return true;
        }

        if (maze[x].get(y) == Labyrinth.WALL || visited[x].get(y)) return false; // If you are on a wall or already were here
        visited[x].set(y, true);

        if (x != 0) // Checks if not on left edge
            if (recursiveSolve(x-1, y)) { // Recalls method one to the left
                solution[x].set(y,true); // Sets that path value to true;
                return true;
            }
        if (x != width - 1) // Checks if not on right edge
            if (recursiveSolve(x+1, y)) { // Recalls method one to the right
                solution[x].set(y,true);
                return true;
            }
        if (y != 0)  // Checks if not on top edge
            if (recursiveSolve(x, y-1)) { // Recalls method one up
                solution[x].set(y,true);
                return true;
            }
        if (y != height - 1) // Checks if not on bottom edge
            if (recursiveSolve(x, y+1)) { // Recalls method one down
                solution[x].set(y,true);
                return true;
            }
        return false;
    }

    public void exportPNG() {
        BufferedImage image = new BufferedImage(this.width,this.height,TYPE_INT_RGB);

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color red = new Color(128,0,0);
        Color green = new Color(0,255,0);
        Color unknown = new Color(128,128,128);

        for(int i=0; i<this.height; i++) {
            for(int j=0; j<this.width; j++) {

                Boolean m = maze[i].get(j), s = solution[i].get(j), v = visited[i].get(j);

                if (m == Labyrinth.WALL) {
                    image.setRGB(j,i,black.getRGB());
                } else if (s) {
                    image.setRGB(j,i,green.getRGB());
                } else if (v) {
                    image.setRGB(j,i,red.getRGB());
                } else if (m == Labyrinth.PATH) {
                    image.setRGB(j, i, white.getRGB());
                }  else {
                    image.setRGB(j,i,unknown.getRGB());
                }

            }
        }

        File output = new File("solution_"+this.height+"x"+this.width+".png");

        try {
            ImageIO.write(image, "png", output);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

}
