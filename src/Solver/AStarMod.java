package Solver;

import Auxiliary.Pair;
import Auxiliary.Tile;
import Generator.Labyrinth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import static Generator.Labyrinth.PATH;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class AStarMod {

    private BitSet[] maze;
    private int height;
    private int width;
    private Pair<Integer, Integer> start;
    private Pair<Integer, Integer> end;

    private PriorityQueue<Tile> unvisitedTiles;
    private ArrayList<Tile> visitedTiles;

    private BitSet[] visited;
    private BitSet[] solution;

    public AStarMod(Labyrinth labyrinth) {
        this.maze = labyrinth.getMaze();
        this.height = labyrinth.getHeight();
        this.width = labyrinth.getWidth();
        this.start = labyrinth.getStart();
        this.end = labyrinth.getEnd();
        this.unvisitedTiles = new PriorityQueue<>();
        this.visitedTiles = new ArrayList<>();
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

        unvisitedTiles.add(new Tile(start, 0, heuristic(start), start));

        while (!unvisitedTiles.isEmpty()) {

            Tile currentTile = unvisitedTiles.poll();
            visitedTiles.add(currentTile);

            int h=currentTile.getCoordinates().getFirst(), w=currentTile.getCoordinates().getSecond(), sd=currentTile.getStartDistance();
            Pair<Integer, Integer> c = currentTile.getCoordinates();
            visited[h].set(w, true);

            if (isEnd(c)) {
                break;
            }

            if (h < height-1 && maze[h+1].get(w)==PATH && !visited[h+1].get(w)) {//S
                Pair<Integer, Integer> coordinates = new Pair(h+1,w);
                Tile t = new Tile(coordinates, sd+1, heuristic(coordinates), c);
                unvisitedTiles.add(t);
            }

            if (w < width-1 && maze[h].get(w+1)==PATH && !visited[h].get(w+1)) {//E
                Pair<Integer, Integer> coordinates = new Pair(h,w+1);
                Tile t = new Tile(coordinates, sd+1, heuristic(coordinates), c);
                unvisitedTiles.add(t);
            }

            if (w > 0 && maze[h].get(w-1)==PATH && !visited[h].get(w-1)) {//W
                Pair<Integer, Integer> coordinates = new Pair(h,w-1);
                Tile t = new Tile(coordinates, sd+1, heuristic(coordinates), c);
                unvisitedTiles.add(t);
            }

            if (h > 0 && maze[h-1].get(w)==PATH && !visited[h-1].get(w)) {//N
                Pair<Integer, Integer> coordinates = new Pair(h-1,w);
                Tile t = new Tile(coordinates, sd+1, heuristic(coordinates), c);
                unvisitedTiles.add(t);
            }

        }

        Tile currentTile = visitedTiles.get(visitedTiles.indexOf(new Tile(end)));

        while (!unvisitedTiles.isEmpty()) {

            int h=currentTile.getCoordinates().getFirst(), w=currentTile.getCoordinates().getSecond();
            Pair<Integer, Integer> c = currentTile.getCoordinates();

            solution[h].set(w, true);

            if(c.equals(start)) {
                break;
            }

            currentTile = visitedTiles.get(visitedTiles.indexOf(new Tile(currentTile.getParent())));

            visitedTiles.remove(new Tile(new Pair<>(h,w)));

        }

    }

    private int heuristic (Pair<Integer, Integer> coordinates) {
        return Math.abs(end.getFirst()-coordinates.getFirst()) + Math.abs(end.getSecond()-coordinates.getSecond()); //Manhattan distance
    }

    private boolean isEnd(Pair<Integer, Integer> coordinates) {
        return coordinates.equals(end);
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
