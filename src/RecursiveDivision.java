import java.util.Random;

public class RecursiveDivision extends Labyrinth{

    RecursiveDivision(int height, int width) {
        super(height, width);
        generator();
        maze[0][randomOdd(0,maze.length-1)] = 1;
        maze[maze.length-1][randomOdd(0,maze[0].length-1)] = 1;
    }

    RecursiveDivision(int height, int width, Long seed) {
        super(height, width, seed);
        generator();
        maze[0][randomOdd(0,maze.length-1)] = 1;
        maze[maze.length-1][randomOdd(0,maze[0].length-1)] = 1;
    }

    private void generator() {
        generator(0, maze.length-1, 0, maze[0].length-1);
    }

    private void generator(int minH, int maxH, int minW, int maxW) {
        int h = (maxH-minH)+1;
        int w = (maxW-minW)+1;
        if (h>w) {
            horizontal(minH, maxH, minW, maxW);
        } else if (h<w) {
            vertical(minH, maxH, minW, maxW);
        } else {
            Boolean b = r.nextBoolean();
            if (b) {
                vertical(minH, maxH, minW, maxW);
            } else {
                horizontal(minH, maxH, minW, maxW);
            }
        }
    }

    private void buildVertical (int w, int minH, int maxH) {
        if (w%2 != 0) {
            System.out.println("Error at biuldVertical()");
        } else {
            for (int i=minH; i<=maxH; i++) {
                maze[i][w] = 0;
            }
        }

    }

    private void buildHorizontal (int h, int minW, int maxW) {
        if (h%2 != 0) {
            System.out.println("Error at biuldHorizontal()");
        } else {
            for (int j=minW; j<=maxW; j++) {
                maze[h][j] = 0;
            }
        }
    }

    private void vertical (int minH, int maxH, int minW, int maxW) {
        int h = (maxH-minH)+1;
        int w = (maxW-minW)+1;

        if (h>3 && w>3) {

            int row = randomOdd(minH+1, maxH-1);
            int column = randomEven(minW+1, maxW-1);

            buildVertical(column, minH+1, maxH-1);
            maze[row][column] = 1;

            generator(minH, maxH, minW, column);
            generator(minH, maxH, column, maxW);
        }
    }

    private void horizontal (int minH, int maxH, int minW, int maxW) {
        int h = (maxH-minH)+1;
        int w = (maxW-minW)+1;

        if (h>3 && w>3) {
            int row = randomEven(minH+1, maxH-1);
            int column = randomOdd(minW+1, maxW-1);

            buildHorizontal(row, minW+1, maxW-1);
            maze[row][column] = 1;

            generator(minH, row, minW, maxW);
            generator(row, maxH, minW, maxW);

        }

    }

    private int randomInRange (int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }

    private int randomEven (int min, int max) {
        while (true) {
            int r = randomInRange (min, max);
            if (r%2 == 0) {
                return r;
            }
        }
    }

    private int randomOdd (int min, int max) {
        while (true) {
            int r = randomInRange (min, max);
            if (r%2 != 0) {
                return r;
            }
        }
    }

}
