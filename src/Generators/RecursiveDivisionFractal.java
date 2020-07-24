package Generators;

public class RecursiveDivisionFractal extends Labyrinth {

    public RecursiveDivisionFractal(int height, int width) {
        super(height, width);
        build();
    }

    RecursiveDivisionFractal(int height, int width, Long seed) {
        super(height, width, seed);
        build();
    }

    private void build() {
        buildEmpty();
        buildBorder();
        buildStartEnd();
        generator();
    }

    private void generator() {
        generator(0, this.height-1, 0, this.width-1);
    }

    private void generator(int minH, int maxH, int minW, int maxW) {
        int h = (maxH-minH)+1;
        int w = (maxW-minW)+1;
        if (h>w) {
            horizontal(minH, maxH, minW, maxW);
        } else if (h<w) {
            vertical(minH, maxH, minW, maxW);
        } else {
            Boolean b = random.nextBoolean();
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
                this.maze[i].set(w, WALL);
            }
        }

    }

    private void buildHorizontal (int h, int minW, int maxW) {
        if (h%2 != 0) {
            System.out.println("Error at biuldHorizontal()");
        } else {
            for (int j=minW; j<=maxW; j++) {
                this.maze[h].set(j, WALL);
            }
        }
    }

    private void vertical (int minH, int maxH, int minW, int maxW) {
        int h = (maxH-minH)+1;
        int w = (maxW-minW)+1;

        if (h>3 && w>3) {
            int row, column;

            row = (maxH-minH)/2+minH+1;
            column = (maxW-minW)/2+minW+1;

            if(row%2==0) {
                row += 1;
            }
            if(column%2==1) {
                column -= 1;
            }

            buildVertical(column, minH+1, maxH-1);
            this.maze[row].set(column, PATH);

            horizontal(minH, maxH, minW, column);
            horizontal(minH, maxH, column, maxW);
        }
    }

    private void horizontal (int minH, int maxH, int minW, int maxW) {
        int h = (maxH-minH)+1;
        int w = (maxW-minW)+1;

        if (h>3 && w>3) {
            int row, column;

            row = (maxH-minH)/2+minH+1;
            column = (maxW-minW)/2+minW+1;

            if(row%2==1) {
                row -= 1;
            }
            if(column%2==0) {
                column += 1;
            }

            buildHorizontal(row, minW+1, maxW-1);
            this.maze[row].set(column, PATH);

            vertical(minH, row, minW, maxW);
            vertical(row, maxH, minW, maxW);

        }

    }

}
