package Auxiliary;

import java.util.Objects;

public class Tile implements Comparable{

    private Pair<Integer, Integer> coordinates;
    private int startDistance;
    private int heuristicDistance;

    public Tile(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Tile(Pair<Integer, Integer> coordinates, int startDistance, int heuristicDistance) {
        this.coordinates = coordinates;
        this.heuristicDistance = heuristicDistance;
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
        return coordinates.toString()+", "+startDistance+" / "+heuristicDistance;
    }

    @Override
    public int compareTo(Object o) {
        Tile t = null;
        t = (Tile) o;
        return (startDistance+heuristicDistance) - (t.getStartDistance()+t.getHeuristicDistance());
    }
}
