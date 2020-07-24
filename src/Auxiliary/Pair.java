package Auxiliary;

import java.util.Objects;

public class Pair<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<Pair<F, S>> {
    private F first; //first member of pair
    private S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(this.getFirst(), pair.getFirst()) && Objects.equals(this.getSecond(), pair.getSecond());
    }

    public String toString() {
        return "("+first+", "+second+")";
    }

    @Override
    public int compareTo(Pair<F, S> o) {
        if (first.compareTo(o.first) == 0) {
            return second.compareTo(o.second);
        } else {
            return first.compareTo(o.first);
        }
    }
}
