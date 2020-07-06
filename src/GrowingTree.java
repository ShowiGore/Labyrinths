import java.util.LinkedList;
import java.util.List;

public class GrowingTree extends Labyrinth {

    GrowingTree (int height, int width) {
        super(height, width);
        build();
    }

    GrowingTree (int height, int width, Long seed) {
        super(height, width, seed);
        build();
    }

    private void build () {
        buildFilled();
        generator();
        buildStartEnd();
    }

    private void generator () {
        LinkedList<Pair<Integer,Integer>> cells = new LinkedList<>();
        LinkedList<Pair<Integer,Integer>> visited = new LinkedList<>();
        Pair<Integer,Integer> cell;
        char[] direction = {'N','E','S','W'};
        //String s = "";

        cell = new Pair<>(randomOdd(1,height-2),randomOdd(1,width-2));

        cells.add(cell);
        visited.add(cell);

        while (!cells.isEmpty()) {

            for (int i=0; i<4; i++) {
                int index1=randomInRange(0,3), index2=randomInRange(0,3);
                char aux = direction[index1];
                direction[index1] = direction[index2];
                direction[index2] = aux;
            }

            cell = cells.get(randomInRange(0, cells.size()-1));
            //cell = cells.getFirst();
            //cell = cells.getLast();

            int i = cell.getFirst();
            int j = cell.getSecond();

            //s = "("+i+","+j+"): ";

            Pair<Integer,Integer> N, E, S, W;
            N = new Pair<>(i-2,j);//System.out.println(i>1);
            E = new Pair<>(i,j+2);//System.out.println(j<width-2);
            S = new Pair<>(i+2,j);//System.out.println(i<width-2);
            W = new Pair<>(i,j-2);//System.out.println(j>1);

            boolean exit = false;
            int index = 0;

            while (!exit) {
                if (index == 4) {
                    cells.remove(cell);
                    exit = true;
                    //s += "∅";
                } else if (direction[index]=='N' && i>1 && !visited.contains(N)) {
                    cells.addLast(N);
                    visited.add(N);
                    maze[i-1].set(j, Labyrinth.PATH);
                    exit = true;
                    //s += "↑";
                } else if (direction[index]=='E' && j<width-2 && !visited.contains(E)) {
                    cells.addLast(E);
                    visited.add(E);
                    maze[i].set(j+1, Labyrinth.PATH);
                    exit = true;
                    //s += "→";
                } else if (direction[index]=='S' && i<height-2 && !visited.contains(S)) {
                    cells.addLast(S);
                    visited.add(S);
                    maze[i+1].set(j, Labyrinth.PATH);
                    exit = true;
                    //s += "↓";
                } else if (direction[index]=='W' && j>1 && !visited.contains(W)) {
                    cells.addLast(W);
                    visited.add(W);
                    maze[i].set(j-1, Labyrinth.PATH);
                    exit = true;
                    //s += "←";
                }
                index ++;
            }

        }


    }

}
