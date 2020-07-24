package Solvers;

import Auxiliary.Pair;
import Generators.Labyrinth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.BitSet;

import static Generators.Labyrinth.PATH;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Solver {

    protected BitSet[] maze;
    protected int height;
    protected int width;
    protected Pair<Integer, Integer> start;
    protected Pair<Integer, Integer> end;

    protected BitSet[] visited;
    protected BitSet[] solution;

    public Solver(Labyrinth labyrinth) {
        this.maze = labyrinth.getMaze();
        this.height = labyrinth.getHeight();
        this.width = labyrinth.getWidth();
        this.start = labyrinth.getStart();
        this.end = labyrinth.getEnd();

        visited = new BitSet[height];
        solution = new BitSet[height];
        for (int i=0; i<height; i++ ) {
            visited[i] = new BitSet(width);
            solution[i] = new BitSet(width);
        }
    }

    public boolean solve() {
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
                } else if (m == PATH) {
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
