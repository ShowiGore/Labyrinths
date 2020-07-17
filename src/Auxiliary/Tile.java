package Auxiliary;

import java.util.Objects;

public class Tile implements Comparable{

    private Pair<Integer, Integer> coordinates;
    private int startDistance;
    private int heuristicDistance;

    private Pair<Integer, Integer> parent;

    public Tile(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Tile(Pair<Integer, Integer> coordinates, int startDistance, int heuristicDistance, Pair<Integer,Integer> parent) {
        this.coordinates = coordinates;
        this.startDistance = startDistance;
        this.heuristicDistance = heuristicDistance;
        this.parent = parent;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public int getStartDistance() {
        return startDistance;
    }

    public void setStartDistance(int startDistance) {
        this.startDistance = startDistance;
    }

    public int getHeuristicDistance() {
        return heuristicDistance;
    }

    public void setHeuristicDistance(int heuristicDistance) {
        this.heuristicDistance = heuristicDistance;
    }

    public Pair<Integer,Integer> getParent() {
        return parent;
    }

    public void setParent(Pair<Integer,Integer> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return Objects.equals(coordinates, tile.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return coordinates.toString()+", "+startDistance+" / "+heuristicDistance+", "+parent;
    }

    @Override
    public int compareTo(Object o) {
        Tile t = null;
        t = (Tile) o;
        return (startDistance+heuristicDistance) - (t.getStartDistance()+t.getHeuristicDistance());
    }
}
