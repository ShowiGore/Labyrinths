package Solvers;

import Auxiliary.Pair;
import Generators.Labyrinth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.BitSet;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Recursive extends Solver{

    public Recursive(Labyrinth labyrinth) {
        super(labyrinth);
    }

    public boolean solve() {
        return recursiveSolve(start.getFirst(), start.getSecond());
    }

    private boolean recursiveSolve(int x, int y) {

        if (x == end.getFirst() && y == end.getSecond()) {
            solution[x].set(y, true);
            return true;
        }

        if (maze[x].get(y) == Labyrinth.WALL || visited[x].get(y)) {
            return false;
        }

        visited[x].set(y, true);

        if (x != height-1) {//S
            if (recursiveSolve(x + 1, y)) {
                solution[x].set(y, true);
                return true;
            }
        }
        if (y != width-1) {//E
            if (recursiveSolve(x, y + 1)) { // Recalls method one down
                solution[x].set(y, true);
                return true;
            }
        }
        if (y != 0) {//W
            if (recursiveSolve(x, y - 1)) {
                solution[x].set(y, true);
                return true;
            }
        }
        if (x != 0) {//N
            if (recursiveSolve(x - 1, y)) {
                solution[x].set(y, true);
                return true;
            }
        }


        return false;
    }

    public void exportPNG(String name) {
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

        File output = new File(name+".png");

        try {
            ImageIO.write(image, "png", output);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void exportPNG() {
        exportPNG("Solution");
    }

}
