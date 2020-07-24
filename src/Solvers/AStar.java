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

        Tile currentTile;
        Pair<Integer,Integer> current, next;
        int h,w;

        parent.put(start, null);
        g.put(start,0);

        openSet.add(new Tile(start, heuristic(start)));

        while (!openSet.isEmpty()) {

            currentTile = openSet.poll();
            current = currentTile.getCoordinates();

            if (isEnd(current)) {
                buildSolution();
                System.out.println("\nSolution length: "+(g.get(current)+1));
                return true;
            }

            h = current.getFirst();
            w = current.getSecond();

            visited[h].set(w, true);

            if (h < height-1 && maze[h+1].get(w)==PATH) {//S
                next = new Pair(h+1,w);
                addToOpenSet(current, next);
            }

            if (w < width-1 && maze[h].get(w+1)==PATH && !visited[h].get(w+1)) {//E
                next = new Pair(h,w+1);
                addToOpenSet(current, next);
            }

            if (w > 0 && maze[h].get(w-1)==PATH && !visited[h].get(w-1)) {//W
                next = new Pair(h,w-1);
                addToOpenSet(current, next);
            }

            if (h > 0 && maze[h-1].get(w)==PATH && !visited[h-1].get(w)) {//N
                next = new Pair(h-1,w);
                addToOpenSet(current, next);
            }

        }

        return false;

    }

    private void addToOpenSet(Pair<Integer, Integer> current, Pair<Integer, Integer> next) {
        if (g.get(current)+1 < g.getOrDefault(next, Integer.MAX_VALUE)) {// better path
            parent.put(next, current);
            g.put(next, g.get(current)+1);
            Tile t = new Tile(next, g.get(current)+1+heuristic(next));
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

    private Double heuristic (Pair<Integer, Integer> coordinates) {
        return Double.valueOf(Math.abs(end.getFirst()-coordinates.getFirst()) + Math.abs(end.getSecond()-coordinates.getSecond())); //Manhattan distance
    }

    private boolean isEnd(Pair<Integer, Integer> coordinates) {
        return coordinates.equals(end);
    }

    private boolean isStart(Pair<Integer, Integer> coordinates) {
        return coordinates.equals(start);
    }

}


