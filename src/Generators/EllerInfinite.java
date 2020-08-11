package Generators;

import Auxiliary.Pair;
import com.sun.source.tree.TryTree;

import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;

import static java.lang.System.in;

public class EllerInfinite extends Labyrinth{

    BitSet wallMask;
    BitSet pathMask;
    BitSet gridMask;

    BitSet top;
    BitSet middle;
    BitSet bottom;

    public EllerInfinite (int height, int width){
        super(height, width);
        build();
        generator();
    }

    public EllerInfinite (int height, int width, Long seed){
        super(height, width, seed);
        build();
        generator();
    }

    private void generator() {

        top = (BitSet) wallMask.clone();        //
        middle = (BitSet) gridMask.clone();     //*****************
        bottom = (BitSet) wallMask.clone();     //* * * * * * * * *
        buildStartOrEnd(top);
        printRow(top);

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
        int xl = (width-1)/2 - 1;

        try {

            while (System.in.available() == 0) {

                for (int x = 0; x < xl; ++x) {
                    /* Creates horizontal passages. */
                    if (r[x] != x + 1 && random.nextBoolean()) {
                        /* Unions the sets by performing a list splice. */
                        l[r[x]] = l[x + 1];
                        r[l[x + 1]] = r[x];
                        l[x + 1] = x;
                        r[x] = x + 1;

                        middle.set(x * 2 + 2, PATH);//middle
                    }
                    /* Creates vertical passages. */
                    if (r[x] != x && random.nextBoolean()) {
                        /* Removes node from list so it has its own set. */
                        l[r[x]] = l[x];
                        r[l[x]] = r[x];
                        l[x] = r[x] = x;
                    } else {
                        bottom.set(x * 2 + 1, PATH);//bottom
                    }
                }
                /* Creates vertical passages for the last column. */
                if (r[xl] != xl && random.nextBoolean()) {
                    l[r[xl]] = l[xl];
                    r[l[xl]] = r[xl];
                    l[xl] = r[xl] = xl;
                } else {
                    bottom.set(xl * 2 + 1, PATH);//bottom
                }

                printRow(middle);
                printRow(bottom);
                top = (BitSet) bottom.clone();
                middle = (BitSet) gridMask.clone();
                bottom = (BitSet) wallMask.clone();

            }

        } catch (IOException e) {
            System.out.print(e);
        }

        /* Creates the last row. */
        for (int x = 0; x < xl; ++x) {
            if (r[x] != x + 1) {
                l[r[x]] = l[x + 1];
                r[l[x + 1]] = r[x];
                l[x + 1] = x;
                r[x] = x + 1;
                middle.set(x*2+2,PATH);//middle
                bottom = (BitSet) wallMask.clone();//bottom + build end
                buildStartOrEnd(bottom);
            }
        }

        printRow(middle);
        printRow(bottom);

    }

    private void build() {

        wallMask = new BitSet(width);
        wallMask.set(0, width, WALL);

        pathMask = new BitSet(width);
        pathMask.set(0, width, PATH);

        gridMask = new BitSet(width);
        for (int i=0; i<width; i++) {
            if (i%2==0) {
                gridMask.set(i,WALL);
            } else {
                gridMask.set(i,PATH);
            }
        }

    }

    private void printRow (BitSet r){
        for (int i=0; i<width; i++) {
            if (r.get(i)==WALL) {
                System.out.print("█");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("");
    }

    private void buildStartOrEnd(BitSet r) {
        start = new Pair(0,randomOdd(0,this.width-1));
        r.set(this.start.getSecond(), PATH);
    }

    private void printLine(){

        StringBuilder s = new StringBuilder();
        s.append("\n");

        for (int j=0; j<this.width; j++) {

            if (middle.get(j) == PATH) {

                s.append(" ");

            } else {

                Boolean N, E, S, W;

                N = top.get(j);
                S = bottom.get(j);
                W = middle.get(j-1);
                E = middle.get(j+1);

                if ((N && E && S && W && WALL) || (!N && !E && !S && !W && !WALL)) {
                    s.append("┼");
                } else if((N && E && S && WALL) || (!N && !E && !S && !WALL)) {
                    s.append("├");
                } else if((E && S && W && WALL) || (!E && !S && !W && !WALL)) {
                    s.append("┬");
                } else if((S && W && N && WALL) || (!S && !W && !N && !WALL)) {
                    s.append("┤");
                } else if((W && N && E && WALL) || (!W && !N && !E && !WALL)) {
                    s.append("┴");
                } else if ((N && E && WALL) || (!N && !E && !WALL)) {
                    s.append("└");
                } else if ((E && S && WALL) || (!E && !S && !WALL)){
                    s.append("┌");
                } else if ((S && W && WALL) || (!S && !W && !WALL)) {
                    s.append("┐");
                } else if ((W && N && WALL) || (!W && !N && !WALL)) {
                    s.append("┘");
                } else if ((N && S && WALL) || (!N && !S && !WALL) || N==WALL || S==WALL) {
                    s.append("│");
                } else if ((E && W && WALL) || (!E && !W && !WALL) || E==WALL || W==WALL) {
                    s.append("─");
                } else {
                    s.append("·");
                }

            }
        }
        System.out.println(s);
    }

}
