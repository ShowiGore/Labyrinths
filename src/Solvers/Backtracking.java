package Solvers;

import Generators.Labyrinth;

public class Backtracking extends Solver{

    int[] nextx = {1,-1,0,0};
    int[] nexty = {0,0,1,-1};

    public Backtracking(Labyrinth labyrinth) {
        super(labyrinth);
    }

    public boolean solve() {
        System.out.println(this.height);
        int x = this.start.getFirst(), y = this.start.getSecond();
        visited[x].set(y);
        if (solveRec(x, y)) {
            solution[x].set(y);
            return true;
        }
        return false;
    }

    public boolean solveRec(int x, int y) {
        int lx = x, ly = y; // last x and y

            for (int i=0; i<nextx.length; i++) { // 4 = nextx.length = nexty.length
                x = lx + nextx[i];
                y = ly + nexty[i];

                if (0<=x && x<this.height && 0<=y && x<this.width && maze[x].get(y) == Labyrinth.PATH && !visited[x].get(y)) {
                    visited[x].set(y);

                    if (x==this.end.getFirst() && y==this.end.getSecond()) { // end
                        solution[x].set(y);
                        return true;
                    } else {
                        if (solveRec(x, y)) {
                            visited[x].set(y);
                            solution[x].set(y);
                            return true;
                        }
                    }

                }

            }

        return false;
    }

}
