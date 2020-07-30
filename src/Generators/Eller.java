package Generators;

import java.util.Set;
import java.util.TreeSet;

public class Eller extends Labyrinth {


    public Eller(int height, int width) {
        super(height, width);
        build();
    }

    Eller(int height, int width, Long seed) {
        super(height, width, seed);
        build();
    }

    private void build() {
        buildMidFilled();
        buildBorder();
        buildStartEnd();
        generator();
    }



    private void generator2() {
        int yl = (height-1)/2-1;
        int xl = (width-1)/2-1;
        for (int y = 0; y < yl; y++) {
            for (int x = 0; x < xl; x++) {
                maze[y*2+1].set(x*2+1,WALL);
            }
        }
    }

    private void generator() {

        /*
         * Circular doubly linked lists are used to store sets of connected maze
         * nodes and these arrays store the left/right links of those lists.
         */
        int[] l = new int[width];
        int[] r = new int[width];

        /* Each node in the first row starts out in its own set. */
        for (int x = 0; x < width; ++x) {
            l[x] = r[x] = x;
        }

        int yl = (height-1)/2 - 1;
        int xl = (width-1)/2 - 1;

        for (int y = 0; y < yl; ++y) {
            for (int x = 0; x < xl; ++x) {
                /* Creates horizontal passages. */
                if (r[x] != x + 1 && random.nextBoolean()) {
                    /* Unions the sets by performing a list splice. */
                    l[r[x]] = l[x + 1];
                    r[l[x + 1]] = r[x];
                    l[x + 1] = x;
                    r[x] = x + 1;

                    maze[y*2+1].set(x*2+2,PATH);
                }
                /* Creates vertical passages. */
                if (r[x] != x && random.nextBoolean()) {
                    /* Removes node from list so it has its own set. */
                    l[r[x]] = l[x];
                    r[l[x]] = r[x];
                    l[x] = r[x] = x;
                } else {
                    maze[y*2+2].set(x*2+1,PATH);
                }
            }
            /* Creates vertical passages for the last column. */
            if (r[xl] != xl && random.nextBoolean()) {
                l[r[xl]] = l[xl];
                r[l[xl]] = r[xl];
                l[xl] = r[xl] = xl;
            } else {
                maze[y*2+2].set(xl*2+1,PATH);
            }
        }

        /* Creates the last row. */
        for (int x = 0; x < xl; ++x) {
            if (r[x] != x + 1) {
                l[r[x]] = l[x + 1];
                r[l[x + 1]] = r[x];
                l[x + 1] = x;
                r[x] = x + 1;
                maze[yl*2+1].set(x*2+2,PATH);
            }
        }

    }

}
