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

    public BitSet[] getMaze() {
        return maze;
    }

    public void setMaze(BitSet[] maze) {
        this.maze = maze;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Pair<Integer, Integer> getStart() {
        return start;
    }

    public void setStart(Pair<Integer, Integer> start) {
        this.start = start;
    }

    public Pair<Integer, Integer> getEnd() {
        return end;
    }

    public void setEnd(Pair<Integer, Integer> end) {
        this.end = end;
    }

    public BitSet[] getVisited() {
        return visited;
    }

    public void setVisited(BitSet[] visited) {
        this.visited = visited;
    }

    public BitSet[] getSolution() {
        return solution;
    }

    public void setSolution(BitSet[] solution) {
        this.solution = solution;
    }

}
