import java.util.BitSet;

public class Eller extends Labyrinth {


    Eller(int height, int width) {
        super(height, width);
        build();
    }

    Eller(int height, int width, Long seed) {
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
        int w = (width-1)/2;

        int[] currentRow = new int[w];
        int[] nextRow = new int[w];

        for (int i=0; i<w; i++) {   //1
            currentRow[i] = i;
        }

        for (int row=1; row<w-2; row+=2){

            //2 join not members

            for (int i=1; i<w; i++) {   //3  lateral walls
                if (currentRow[i]==currentRow[i-1]) {   //same set, build wall
                    maze[row].set(i*2,Labyrinth.WALL);
                } else {
                    if (random.nextBoolean()) { //join sets
                        currentRow[i] = currentRow[i-1];
                    } else {    //build wall
                        maze[row].set(i*2,Labyrinth.WALL);
                    }
                }
            }

            boolean safe = false;
            int firstOfSet = 0;
            for (int i=0; i<w; i++) {   //4 bottom walls
                if (random.nextBoolean()) {  //add wall
                    maze[row+1].set(i*2+1,Labyrinth.WALL);
                } else {
                    safe = true;
                }
                if (i!=w-1 && currentRow[i]!=currentRow[i+1]) {   //last of set and not last cell
                    if (!safe){
                        maze[row+1].set(randomInRange(firstOfSet,i)*2+1,Labyrinth.PATH);
                    }
                    safe = false;
                    firstOfSet = i+1;
                }
            }

            //5 new row
        }

        //6 last row

    }

}
