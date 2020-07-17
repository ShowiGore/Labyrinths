package Generator;

import java.util.Set;
import java.util.TreeSet;

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
        Set<Integer> used = new TreeSet<>();

        System.out.println("w: "+w);

        int[] currentRow = new int[w];
        int[] nextRow = new int[w];

        for (int i=0; i<w; i++) {   //1
            currentRow[i] = i;
        }

        for (int row=1; row<width-2; row+=2){
            String s = "";

            System.out.println("Row: "+row);
            s="";
            for (int i=0; i<w; i++) {   //1
                s = s+" "+Integer.toString(currentRow[i]);
            }
            System.out.println("2: "+s);

            for (int i=0; i<w; i++) {       //2 Join any cells not members of a set to their own unique set
                if (currentRow[i] == -1) {
                    int j = 0;
                    while (used.contains(j)) {
                        j++;
                    }
                    currentRow[i] = j;
                    used.add(j);
                }
            }

            used.clear();

            s="";
            for (int i=0; i<w; i++) {
                s = s+" "+Integer.toString(currentRow[i]);
            }
            System.out.println("3:"+s);

            for (int i=0; i<w-1; i++) {   //3  lateral walls
                if (currentRow[i]==currentRow[i+1]) {   //same set, build wall
                    maze[row].set(i*2+2,Labyrinth.WALL);
                    System.out.println("right wall");
                } else {
                    if (random.nextBoolean()) { //join sets
                        currentRow[i+1] = currentRow[i];
                        System.out.println("join sets");
                    } else {    //build wall
                        maze[row].set(i*2+2,Labyrinth.WALL);
                        System.out.println("right wall");
                    }
                }
            }

            s="";
            for (int i=0; i<w; i++) {   //1
                s = s+" "+Integer.toString(currentRow[i]);
            }
            System.out.println("4:"+s);

            boolean safe = false;
            for (int i=0; i<w; i++) {   //4 bottom walls
                maze[row+1].set(i*2,Labyrinth.WALL);

                if (i!=w-1 && currentRow[i]!=currentRow[i+1]) {   //last of set and not last cell
                    if (!safe){
                        maze[row+1].set(i*2+1,Labyrinth.PATH);
                        nextRow[i] = currentRow[i];
                        used.add(currentRow[i]);
                    }
                    safe = false;
                } else if (i==w-1) { // last of set
                    if (!safe){
                        maze[row+1].set(i*2+1,Labyrinth.PATH);
                        nextRow[i] = currentRow[i];
                        used.add(currentRow[i]);
                    }
                    safe = false;
                } else {
                    if (random.nextBoolean()) {  //add wall
                        maze[row+1].set(i*2+1,Labyrinth.WALL);
                        nextRow[i] = -1;//2
                    } else {
                        safe = true;
                        nextRow[i] = currentRow[i];//2
                        used.add(currentRow[i]);
                    }
                }
            }

            s="";
            for (int i=0; i<w; i++) {   //1
                s = s+" "+Integer.toString(currentRow[i]);
            }
            System.out.println("5:"+s);

            currentRow = nextRow;//5 new row

        }

        for (int i=1; i<w; i++) {   //3  lateral walls
                maze[width-2] = maze[width-4];
            if (currentRow[i]!=currentRow[i-1]) {   //different sets remove wall
                maze[width-2].set(i*2,Labyrinth.PATH);
            }
        }

    }

}
