import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Labyrinth_IntArray implements Serializable {

    protected int[][] maze;
    protected Random r = new Random();
    protected long seed;
    protected Coordinates start;
    protected Coordinates end;

    Labyrinth_IntArray(int height, int width) {
        this.seed = r.nextLong();
        this.r.setSeed(seed);
        this.maze = new int[height*2+1][width*2+1];
        buildInterior();
        buildBorder();
        buildStartEnd();
    }

    Labyrinth_IntArray(int height, int width, Long seed) {
        this.seed = seed;
        this.r.setSeed(seed);
        this.maze = new int[height*2+1][width*2+1];
        buildInterior();
        buildBorder();
        buildStartEnd();
    }

    public Long getSeed() {
        return this.seed;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    public int[][] getMaze() {
        return this.maze;
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

    private void buildStartEnd() {
        this.start = new Coordinates(0, randomOdd(0,maze.length-1));
        this.end = new Coordinates(maze.length-1, randomOdd(0,maze[0].length-1));

        maze[start.getI()][start.getJ()] = 1;
        maze[end.getI()][end.getJ()] = 1;
    }

    protected int randomEven (int min, int max) {
        if (max % 2 != 0) --max;
        if (min % 2 != 0) ++min;
        return min + 2 * (r.nextInt((max-min)/2+1));
    }

    protected int randomOdd (int min, int max) {
        if (max % 2 == 0) --max;
        if (min % 2 == 0) ++min;
        return min + 2 * (r.nextInt((max-min)/2+1));
    }

    public String toString() {
        int h = maze.length;
        int w = maze[0].length;

        StringBuilder s = new StringBuilder();
        s.append("\n");

        for (int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {

                if (maze[i][j] == 1){//path
                    s.append(" ");
                } else {
                    int N, E, S, W;

                    N = (i==0) ? 1 : maze[i-1][j];
                    S = (i==h-1) ? 1 : maze[i+1][j];
                    W = (j==0) ? 1 : maze[i][j-1];
                    E = (j==w-1) ? 1 : maze[i][j+1];

                    if((N+E+S+W) == 0) {
                        s.append("┼");
                    } else if((N+E+S) == 0) {
                        s.append("├");
                    } else if((E+S+W) == 0) {
                        s.append("┬");
                    } else if((S+W+N) == 0) {
                        s.append("┤");
                    } else if((W+N+E) == 0) {
                        s.append("┴");
                    } else if ((N+E) == 0) {
                        s.append("└");
                    } else if ((E+S) == 0) {
                        s.append("┌");
                    } else if ((S+W) == 0) {
                        s.append("┐");
                    } else if ((W+N) == 0) {
                        s.append("┘");
                    } else if ((N+S) == 0 || N==0 || S==0) {
                        s.append("│");
                    } else if ((E+W) == 0 || E==0 || W==0) {
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

    public String toString2() {
        int h = maze.length;
        int w = maze[0].length;

        StringBuilder s = new StringBuilder();

        for (int i=0; i<h; i++) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            StringBuilder s3 = new StringBuilder();
            for (int j=0; j<w; j++) {

                if (maze[i][j] == 1){//path
                    s1.append("   ");
                    s2.append("   ");
                    s3.append("   ");
                } else {
                    int N, E, S, W;

                    N = (i==0) ? 1 : maze[i-1][j];
                    S = (i==h-1) ? 1 : maze[i+1][j];
                    W = (j==0) ? 1 : maze[i][j-1];
                    E = (j==w-1) ? 1 : maze[i][j+1];

                    if((N+E+S+W) == 0) {
                        s1.append(" │ ");
                        s2.append("─┼─");
                        s3.append(" │ ");
                    } else if((N+E+S) == 0) {
                        s1.append(" │ ");
                        s2.append(" ├─");
                        s3.append(" │ ");
                    } else if((E+S+W) == 0) {
                        s1.append("   ");
                        s2.append("─┬─");
                        s3.append(" │ ");
                    } else if((S+W+N) == 0) {
                        s1.append(" │ ");
                        s2.append("─┤ ");
                        s3.append(" │ ");
                    } else if((W+N+E) == 0) {
                        s1.append(" │ ");
                        s2.append("─┴─");
                        s3.append("   ");
                    } else if ((N+E) == 0) {
                        s1.append(" │ ");
                        s2.append(" └─");
                        s3.append("   ");
                    } else if ((E+S) == 0) {
                        s1.append("   ");
                        s2.append(" ┌─");
                        s3.append(" │ ");
                    } else if ((S+W) == 0) {
                        s1.append("   ");
                        s2.append("─┐ ");
                        s3.append(" │ ");
                    } else if ((W+N) == 0) {
                        s1.append(" │ ");
                        s2.append("─┘ ");
                        s3.append("   ");
                    } else if ((N+S) == 0 || N==0 || S==0) {
                        s1.append(" │ ");
                        s2.append(" │ ");
                        s3.append(" │ ");
                    } else if ((E+W) == 0 || E==0 || W==0) {
                        s1.append("   ");
                        s2.append("───");
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

    public void PNG(String name) {
        int h = maze.length;
        int w = maze[0].length;

        BufferedImage image = new BufferedImage(w,h,TYPE_INT_RGB);

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        Color unknown = new Color(128,128,128);
        Color visited = new Color(200,200,200);
        Color solution = new Color(128,0,128);

        for(int i=0; i<maze.length; i++) {
            for(int j=0; j<maze[0].length; j++) {

                int p = maze[i][j];

                if (p == 0) {
                    image.setRGB(j,i,black.getRGB());
                } else if (p == 1) {
                    image.setRGB(j,i,white.getRGB());
                } else if (p == 2) {
                    image.setRGB(j,i,visited.getRGB());
                } else if (p == 3) {
                    image.setRGB(j,i,solution.getRGB());
                }  else {
                    image.setRGB(j,i,unknown.getRGB());
                }

            }
        }

        File output = new File(name+".png");

        try {
            ImageIO.write(image, "png", output);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void minPath() {
        minPath(this.start, this.end);
    }

    private boolean minPath(Coordinates actual, Coordinates end) {
        boolean pathToEnd = false;

        int i = actual.getI();
        int j = actual.getJ();

        if (!valid(actual)) {
            return false;
        }

        if (actual.equals(end)) {
            maze[i][j] = 3;         //path to end
            return true;
        }

        //actual visited
        maze[i][j] = 2;

        pathToEnd = pathToEnd || minPath(new Coordinates(i+1, j), end);  //N
        pathToEnd = pathToEnd || minPath(new Coordinates(i, j+1), end);  //E
        pathToEnd = pathToEnd || minPath(new Coordinates(i-1, j), end);  //S
        pathToEnd = pathToEnd || minPath(new Coordinates(i, j-1), end);  //W

        if (pathToEnd) {
            maze[i][j] = 3;     //path to end
        }

        return pathToEnd;
    }

    private boolean valid(Coordinates c) {
        int i = c.getI();
        int j = c.getJ();

        if (i<0 || j<0 || i>=maze.length || j>= maze[0].length || maze[i][j]!=1) {
            return false;
        }

        return true;
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
