import java.io.Serializable;
import java.util.Random;

public class Labyrinth implements Serializable {

    protected int[][] maze;
    protected Random r = new Random();
    protected long seed;

    Labyrinth (int height, int width) {
        this.seed = r.nextLong();
        this.r.setSeed(seed);
        this.maze = new int[height*2+1][width*2+1];
        buildInterior();
        buildBorder();
    }

    Labyrinth (int height, int width, Long seed) {
        this.seed = seed;
        this.r.setSeed(seed);
        this.maze = new int[height*2+1][width*2+1];
        buildInterior();
        buildBorder();
    }

    public Long getSeed() {
        return this.seed;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    private void buildInterior() {
        int h = maze.length;
        int w = maze[0].length;

        for (int i=0; i<h; i++) {
            for (int j = 0; j < w; j++) {
                maze[i][j] = 1;
            }
        }
    }

    private void buildBorder() {
        int h = maze.length;
        int w = maze[0].length;

        for (int i=0; i<h; i++) {
            this.maze[i][0] = 0;
            this.maze[i][w-1] = 0;
        }
        for (int j=0; j<w; j++) {
            this.maze[0][j] = 0;
            this.maze[h-1][j] = 0;
        }
    }

    public void randomizeInside(){
        int h = maze.length;
        int w = maze[0].length;

        for (int i=0; i<h; i++) {
            for (int j = 0; j < w; j++) {
                maze[i][j] = r.nextInt(2);
            }
        }

        buildBorder();

    }

    public String toString() {
        int h = maze.length;
        int w = maze[0].length;

        String s = "";

        for (int i=0; i<h; i++) {
            s+="\n";
            for (int j=0; j<w; j++) {

                if (maze[i][j] == 1){//path
                    s+=" ";
                } else {
                    int N, E, S, W;

                    N = (i==0) ? 1 : maze[i-1][j];
                    S = (i==h-1) ? 1 : maze[i+1][j];
                    W = (j==0) ? 1 : maze[i][j-1];
                    E = (j==w-1) ? 1 : maze[i][j+1];

                    if((N+E+S+W) == 0) {
                        s += "┼";
                    } else if((N+E+S) == 0) {
                        s+="├";
                    } else if((E+S+W) == 0) {
                        s+="┬";
                    } else if((S+W+N) == 0) {
                        s+="┤";
                    } else if((W+N+E) == 0) {
                        s+="┴";
                    } else if ((N+E) == 0) {
                        s+="└";
                    } else if ((E+S) == 0) {
                        s+="┌";
                    } else if ((S+W) == 0) {
                        s+="┐";
                    } else if ((W+N) == 0) {
                        s+="┘";
                    } else if ((N+S) == 0 || N==0 || S==0) {
                        s+="│";
                    } else if ((E+W) == 0 || E==0 || W==0) {
                        s+="─";
                    } else {
                        s+="·";
                    }
                }
            }
        }
        return s;
    }

    public String printMatrix() {
        int h = maze.length;
        int w = maze[0].length;

        String s = "";

        for (int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
                if (maze[i][j] == 0) {
                    System.out.print("▓");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        return s;
    }
}
