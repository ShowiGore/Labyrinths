package Auxiliary;

import Generators.Labyrinth;
import Solvers.Solver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.BitSet;

import static Generators.Labyrinth.PATH;
import static Generators.Labyrinth.WALL;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Image {

    //https://memorynotfound.com/generate-gif-image-java-delay-infinite-loop-example/

    public void mazeToPNG(Labyrinth labyrinth, String name) {
        int h=labyrinth.getHeight(), w=labyrinth.getWidth();
        BitSet[] maze = labyrinth.getMaze();

        BufferedImage image = new BufferedImage(w, h,TYPE_INT_RGB);

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color unknown = new Color(128,128,128);

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {

                Boolean p = maze[i].get(j);

                if (p == WALL) {
                    image.setRGB(j,i,black.getRGB());
                } else if (p == PATH) {
                    image.setRGB(j,i,white.getRGB());
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

    public void mazeToPNG(Labyrinth labyrinth) {
        mazeToPNG(labyrinth, "Maze");
    }

    public void solutionToPNG(Solver solver, String name) {
        int h=solver.getHeight(), w=solver.getWidth();
        BitSet[] maze = solver.getMaze(), solution=solver.getSolution(), visited=solver.getVisited();

        BufferedImage image = new BufferedImage(w,h,TYPE_INT_RGB);

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color red = new Color(128,0,0);
        Color green = new Color(0,255,0);
        Color unknown = new Color(128,128,128);

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {

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

    public void solutionToPNG(Solver solver) {
        solutionToPNG(solver,"Solution");
    }



}
