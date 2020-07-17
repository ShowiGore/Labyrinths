package Generator;

import Auxiliary.Pair;

import java.util.LinkedList;

public class GrowingTree extends Labyrinth {

    public GrowingTree(int height, int width) {
        super(height, width);
        build();
    }

    public GrowingTree(int height, int width, Long seed) {
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
                } else if (direction[index]=='N' && i>1 && isWall(N.getFirst(),N.getSecond())) {
                    cells.addLast(N);
                    maze[N.getFirst()].set(N.getSecond(), Labyrinth.PATH);
                    maze[i-1].set(j, Labyrinth.PATH);
                    exit = true;
                    //s += "↑";
                } else if (direction[index]=='E' && j<width-2 && isWall(E.getFirst(),E.getSecond())) {
                    cells.addLast(E);
                    maze[E.getFirst()].set(E.getSecond(), Labyrinth.PATH);
                    maze[i].set(j+1, Labyrinth.PATH);
                    exit = true;
                    //s += "→";
                } else if (direction[index]=='S' && i<height-2 && isWall(S.getFirst(),S.getSecond())) {
                    cells.addLast(S);
                    maze[S.getFirst()].set(S.getSecond(), Labyrinth.PATH);
                    maze[i+1].set(j, Labyrinth.PATH);
                    exit = true;
                    //s += "↓";
                } else if (direction[index]=='W' && j>1 && isWall(W.getFirst(),W.getSecond())) {
                    cells.addLast(W);
                    maze[W.getFirst()].set(W.getSecond(), Labyrinth.PATH);
                    maze[i].set(j-1, Labyrinth.PATH);
                    exit = true;
                    //s += "←";
                }
                index ++;
            }

        }

    }

}