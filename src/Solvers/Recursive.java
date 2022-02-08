package Solvers;

import Generators.Labyrinth;

public class Recursive extends Solver{

    public Recursive(Labyrinth labyrinth) {
        super(labyrinth);
    }

    public boolean solve() {
        return recursiveSolve(start.getFirst(), start.getSecond());
    }

    private boolean recursiveSolve(int x, int y) {

        if (x == end.getFirst() && y == end.getSecond()) {
            solution[x].set(y, true);
            return true;
        }

        if (maze[x].get(y) == Labyrinth.WALL || visited[x].get(y)) {
            return false;
        }

        visited[x].set(y, true);

        if (x != height-1) {//S
            if (recursiveSolve(x + 1, y)) {
                solution[x].set(y, true);
                return true;
            }
        }
        if (y != width-1) {//E
            if (recursiveSolve(x, y + 1)) { // Recalls method one down
                solution[x].set(y, true);
                return true;
            }
        }
        if (y != 0) {//W
            if (recursiveSolve(x, y - 1)) {
                solution[x].set(y, true);
                return true;
            }
        }
        if (x != 0) {//N
            if (recursiveSolve(x - 1, y)) {
                solution[x].set(y, true);
                return true;
            }
        }


        return false;
    }

}
