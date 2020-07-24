package Solvers;

import Auxiliary.Pair;
import Auxiliary.Tile;
import Generators.Labyrinth;

import java.util.*;

import static Generators.Labyrinth.PATH;

public class AStar extends Solver {

    private Queue<Tile> openSet;
    private Map<Pair<Integer,Integer>,Pair<Integer,Integer>> parent;
    private Map<Pair<Integer,Integer>,Integer> g;

    public AStar(Labyrinth labyrinth) {
        super(labyrinth);

        this.openSet = new PriorityQueue<>();
        this.parent = new TreeMap<>();
        this.g = new TreeMap<>();
    }

    public boolean solve() {

        parent.put(start, null);
        g.put(start,0);

        openSet.add(new Tile(start, 0, heuristic(start)));

        while (!openSet.isEmpty()) {

            Tile currentTile = openSet.poll();

            if (isEnd(currentTile.getCoordinates())) {
                buildSolution();
                return true;
            }

            int h=currentTile.getCoordinates().getFirst(),
            w=currentTile.getCoordinates().getSecond(),
            sd=currentTile.getStartDistance();

            visited[h].set(w, true);

            if (h < height-1 && maze[h+1].get(w)==PATH) {//S
                Pair<Integer, Integer> coordinates = new Pair(h+1,w);

                addToOpenSet(currentTile, sd, coordinates);
            }

            if (w < width-1 && maze[h].get(w+1)==PATH && !visited[h].get(w+1)) {//E
                Pair<Integer, Integer> coordinates = new Pair(h,w+1);

                addToOpenSet(currentTile, sd, coordinates);
            }

            if (w > 0 && maze[h].get(w-1)==PATH && !visited[h].get(w-1)) {//W
                Pair<Integer, Integer> coordinates = new Pair(h,w-1);

                addToOpenSet(currentTile, sd, coordinates);
            }

            if (h > 0 && maze[h-1].get(w)==PATH && !visited[h-1].get(w)) {//N
                Pair<Integer, Integer> coordinates = new Pair(h-1,w);

                addToOpenSet(currentTile, sd, coordinates);
            }

        }

        return false;

    }

    private void addToOpenSet(Tile currentTile, int sd, Pair<Integer, Integer> coordinates) {
        if (g.get(currentTile.getCoordinates())+1 < g.getOrDefault(coordinates, Integer.MAX_VALUE)) {// better path
            parent.put(coordinates, currentTile.getCoordinates());
            g.put(coordinates, g.get(currentTile.getCoordinates())+1);
            Tile t = new Tile(coordinates, sd+1, heuristic(coordinates));
            if (!openSet.contains(t)) {
                openSet.add(t);
            }
        }
    }

    private void buildSolution() {
        Pair<Integer,Integer> current = end;

        while (current != null) {
            solution[current.getFirst()].set(current.getSecond(), true);
            current = parent.get(current);
        }
        
    }

    private int heuristic (Pair<Integer, Integer> coordinates) {
        return 2*Math.abs(end.getFirst()-coordinates.getFirst()) + Math.abs(end.getSecond()-coordinates.getSecond()); //Manhattan distance
    }

    private boolean isEnd(Pair<Integer, Integer> coordinates) {
        return coordinates.equals(end);
    }

    private boolean isStart(Pair<Integer, Integer> coordinates) {
        return coordinates.equals(start);
    }

}


