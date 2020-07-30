package Generators;

import Auxiliary.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Random;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Labyrinth implements Serializable {

    public static final Boolean PATH = false;
    public static final Boolean WALL = true;

    protected BitSet[] maze;
    protected int height;
    protected int width;
    protected Random random = new Random();
    protected long seed;
    protected Pair<Integer, Integer> start;
    protected Pair<Integer, Integer> end;

    Labyrinth(int height, int width) {
        this.seed = random.nextLong();
        this.random.setSeed(seed);
        this.height = height*2+1;
        this.width = width*2+1;
        System.out.println("\n"+height+"x"+width+": "+getSeed());
    }

    Labyrinth(int height, int width, Long seed) {
        this.seed = seed;
        this.random.setSeed(seed);
        this.height = height*2+1;
        this.width = width*2+1;
        System.out.println("\n"+height+"x"+width+": "+getSeed());
    }

    protected void buildEmpty() {
        maze = new BitSet[height];
        for (int i=0; i<this.height; i++) {
            this.maze[i] = new BitSet(width);
        }
    }

    protected void buildFilled() {
        this.maze = new BitSet[height];

        for (int i=0; i<height; i++) {
            maze[i] = new BitSet(width);
            maze[i].set(0,width, WALL);
        }
    }

    protected void buildMidFilled() {
        this.maze = new BitSet[height];

        for (int i=0; i<height; i++) {
            maze[i] = new BitSet(width);
            for (int j = 0; j < width; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    maze[i].set(j,PATH);
                } else {
                    maze[i].set(j,WALL);
                }
            }
        }
    }

    protected void buildBorder() {
        for (int i=0; i<this.height; i++) {
            this.maze[i].set(0, WALL);
            this.maze[i].set(this.width-1, WALL);
        }
        for (int j=0; j<this.width; j++) {
            this.maze[0].set(j, WALL);
            this.maze[this.height-1].set(j, WALL);
        }
    }

    protected void buildStartEnd() {
        this.start = new Pair(0,randomOdd(0,this.width-1));
        this.end = new Pair(this.height-1,randomOdd(0,this.width-1));

        this.maze[this.start.getFirst()].set(this.start.getSecond(), PATH);
        this.maze[this.end.getFirst()].set(this.end.getSecond(), PATH);
    }

    public Long getSeed() {
        return this.seed;
    }

    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("\n");

        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {

                if (maze[i].get(j) == PATH){
                    s.append(" ");
                } else {
                    Boolean N, E, S, W;

                    N = (i==0) ? PATH : maze[i-1].get(j);
                    S = (i==this.height-1) ? PATH : maze[i+1].get(j);
                    W = (j==0) ? PATH : maze[i].get(j-1);
                    E = (j==this.width-1) ? PATH : maze[i].get(j+1);

                    if((N && E && S && W && WALL) || (!N && !E && !S && !W && !WALL)) {
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
            s.append("\n");
        }

        return s.toString();

    }

    public String toStringAccurate() {

        StringBuilder s = new StringBuilder();
        s.append("\n");

        StringBuilder s1;
        StringBuilder s2;
        StringBuilder s3;

        for (int i=0; i<this.height; i++) {

            s1 = new StringBuilder();
            s2 = new StringBuilder();
            s3 = new StringBuilder();

            for (int j=0; j<this.width; j++) {

                if (maze[i].get(j) == PATH){
                    s1.append("   ");
                    s2.append("   ");
                    s3.append("   ");
                } else {
                    Boolean N, E, S, W;

                    N = (i==0) ? PATH : maze[i-1].get(j);
                    S = (i==this.height-1) ? PATH : maze[i+1].get(j);
                    W = (j==0) ? PATH : maze[i].get(j-1);
                    E = (j==this.width-1) ? PATH : maze[i].get(j+1);

                    if((N && E && S && W && WALL) || (!N && !E && !S && !W && !WALL)) {
                        s1.append(" │ ");
                        s2.append("─┼─");
                        s3.append(" │ ");
                    } else if((N && E && S && WALL) || (!N && !E && !S && !WALL)) {
                        s1.append(" │ ");
                        s2.append(" ├─");
                        s3.append(" │ ");
                    } else if((E && S && W && WALL) || (!E && !S && !W && !WALL)) {
                        s1.append("   ");
                        s2.append("─┬─");
                        s3.append(" │ ");
                    } else if((S && W && N && WALL) || (!S && !W && !N && !WALL)) {
                        s1.append(" │ ");
                        s2.append("─┤ ");
                        s3.append(" │ ");
                    } else if((W && N && E && WALL) || (!W && !N && !E && !WALL)) {
                        s1.append(" │ ");
                        s2.append("─┴─");
                        s3.append("   ");
                    } else if ((N && E && WALL) || (!N && !E && !WALL)) {
                        s1.append(" │ ");
                        s2.append(" └─");
                        s3.append("   ");
                    } else if ((E && S && WALL) || (!E && !S && !WALL)){
                        s1.append("   ");
                        s2.append(" ┌─");
                        s3.append(" │ ");
                    } else if ((S && W && WALL) || (!S && !W && !WALL)) {
                        s1.append("   ");
                        s2.append("─┐ ");
                        s3.append(" │ ");
                    } else if ((W && N && WALL) || (!W && !N && !WALL)) {
                        s1.append(" │ ");
                        s2.append("─┘ ");
                        s3.append("   ");
                    } else if ((N && S && WALL) || (!N && !S && !WALL)) {
                        s1.append(" │ ");
                        s2.append(" │ ");
                        s3.append(" │ ");
                    } else if ((E && W && WALL) || (!E && !W && !WALL)) {
                        s1.append("   ");
                        s2.append("───");
                        s3.append("   ");
                    } else if (N==WALL) {
                        s1.append(" │ ");
                        s2.append(" │ ");
                        s3.append("   ");
                    } else if (E==WALL) {
                        s1.append("   ");
                        s2.append(" ──");
                        s3.append("   ");
                    } else if (S==WALL) {
                        s1.append("   ");
                        s2.append(" │ ");
                        s3.append(" │ ");
                    } else if (W==WALL) {
                        s1.append("   ");
                        s2.append("── ");
                        s3.append("   ");
                    } else {
                        s1.append("┌─┐");
                        s2.append("│·│");
                        s3.append("└─┘");
                    }
                }

            }
            s.append(s1.toString()).append("\n");
            s.append(s2.toString()).append("\n");
            s.append(s3.toString()).append("\n");
        }

        return s.toString();

    }

    protected int randomEven (int min, int max) {
        if (max % 2 != 0) --max;
        if (min % 2 != 0) ++min;
        return min + 2 * (random.nextInt((max-min)/2+1));
    }

    protected int randomOdd (int min, int max) {
        if (max % 2 == 0) --max;
        if (min % 2 == 0) ++min;
        return min + 2 * (random.nextInt((max-min)/2+1));
    }

    protected int randomInRange (int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public void randomize () {
        for (int i=0; i<this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.maze[i].set(j, this.random.nextBoolean());
            }
        }
    }

    public boolean isPath (int i, int j) {
        if (maze[i].get(j) == PATH) {
            return true;
        }
        return false;
    }

    public boolean isWall (int i, int j) {
        if (maze[i].get(j) == WALL) {
            return true;
        }
        return false;
    }

    public BitSet[] getMaze() {
        return maze;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Pair<Integer, Integer> getStart() {
        return start;
    }

    public Pair<Integer, Integer> getEnd() {
        return end;
    }
}
