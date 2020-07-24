package Auxiliary;

import java.util.Objects;

public class Tile implements Comparable<Tile> {

    private Pair<Integer, Integer> coordinates;
    private Double f;

    public Tile(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Tile(Pair<Integer, Integer> coordinates, Double f) {
        this.coordinates = coordinates;
        this.f = f;
    }

    public Pair<Integer, Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Integer, Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Double getf() {
        return f;
    }

    public void setf(Double f) {
        this.f = f;
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
        return coordinates.toString()+", "+f;
    }

    @Override
    public int compareTo(Tile o) {
        return f.compareTo(o.f);
    }
}
